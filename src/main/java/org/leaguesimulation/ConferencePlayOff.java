/* @Author: Vikram Singh */

package org.leaguesimulation;

import org.apache.log4j.Logger;
import org.leaguesimulation.abstractfactory.LeagueAbstractFactory;
import org.leaguesimulation.interfaces.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConferencePlayOff implements IScoreboard, IPlayOffScoreboard, ISeed, IConferencePlayOffScoreboard {

    public static final int TOP_PLAYERS = 3;
    private final LeagueAbstractFactory factory;
    private final IConferenceScoreboard conferenceScoreboard;
    private final List<TeamOnScoreboard> conferencePlayOffScoreboard;
    private final List<IScoreboard> divisionPlayOffScoreboards;
    private final Logger logger;
    private TeamOnScoreboard winner;
    private boolean isFinal;

    public ConferencePlayOff(IConferenceScoreboard conferenceScoreboard) {
        this.conferenceScoreboard = conferenceScoreboard;
        conferencePlayOffScoreboard = new ArrayList<>();
        divisionPlayOffScoreboards = new ArrayList<>();
        factory = LeagueAbstractFactory.instance();
        logger = Logger.getLogger(ConferencePlayOff.class.getName());
    }

    public List<TeamOnScoreboard> loadScoreboard() {
        logger.info("Loading playOff Scoreboard for Conference");
        for (IScoreboard divisionScoreboard : conferenceScoreboard.getDivisionScoreboards()) {
            IScoreboard divisionPlayOffScoreboard = factory.createDivisionPlayOff(divisionScoreboard);
            conferencePlayOffScoreboard.addAll(divisionPlayOffScoreboard.loadScoreboard());
            divisionPlayOffScoreboards.add(divisionPlayOffScoreboard);
        }
        return conferencePlayOffScoreboard;
    }

    public List<TeamOnScoreboard> getScoreboard() {
        return conferencePlayOffScoreboard;
    }

    public List<TeamOnScoreboard> sort() {
        Collections.sort(conferencePlayOffScoreboard);
        return conferencePlayOffScoreboard;
    }

    public ITeamOnScoreboard getWinner() {
        return winner;
    }

    public ITeamOnScoreboard checkWinner() {
        if (isFinal) {
            winner = sort().get(0);
            return winner;
        } else {
            for (IScoreboard divisionScoreboard : divisionPlayOffScoreboards) {
                IPlayOffScoreboard divisionPlayOffScoreboard = (IPlayOffScoreboard) divisionScoreboard;
                divisionPlayOffScoreboard.checkWinner();
            }
        }
        return null;
    }

    public boolean setFinal() {
        isFinal = true;
        return true;
    }

    public void initiateScoreToZero() {
        for (IScoreboard divisionScoreboard :
                divisionPlayOffScoreboards) {
            IPlayOffScoreboard playOffScoreboard = (IPlayOffScoreboard) divisionScoreboard;
            playOffScoreboard.initiateScoreToZero();
        }
    }

    public void seed(int numberOfWildCards) {
        sort();
        int i = 0;
        IScoreboard iScoreboard = (IScoreboard) conferenceScoreboard;
        iScoreboard.sort();
        for (IScoreboard divisionScoreboard :
                divisionPlayOffScoreboards) {
            ISeed seedDivision = (ISeed) divisionScoreboard;
            seedDivision.seed(iScoreboard.getScoreboard().get((numberOfWildCards * TOP_PLAYERS) + i));
            i++;
        }
    }

    public void seed(TeamOnScoreboard wildCard) {
        logger.error("Correct usage: call with the division");
    }

    public List<IScoreboard> getDivisionScoreboards() {
        return divisionPlayOffScoreboards;
    }
}
