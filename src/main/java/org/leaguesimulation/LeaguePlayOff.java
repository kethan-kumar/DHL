/* @Author: Vikram Singh */

package org.leaguesimulation;

import org.apache.log4j.Logger;
import org.leaguesimulation.abstractfactory.LeagueAbstractFactory;
import org.leaguesimulation.interfaces.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaguePlayOff implements IScoreboard, IPlayOffScoreboard, ISeed, ILeaguePlayOffScoreboard {

    private final LeagueAbstractFactory factory;
    private final ILeagueScoreboard leagueScoreboard;
    private final List<TeamOnScoreboard> leaguePlayOffScoreboard;
    private final List<IConferencePlayOffScoreboard> conferencePlayOffScoreboards;
    private TeamOnScoreboard winner;
    private boolean isFinal;
    private Logger logger;

    public LeaguePlayOff(IScoreboard leagueScoreboard) {
        this.leagueScoreboard = (ILeagueScoreboard) leagueScoreboard;
        this.leaguePlayOffScoreboard = new ArrayList<>();
        this.conferencePlayOffScoreboards = new ArrayList<>();
        factory = LeagueAbstractFactory.instance();
        logger = Logger.getLogger(LeaguePlayOff.class.getName());
    }

    public List<TeamOnScoreboard> loadScoreboard() {
        for (IConferenceScoreboard conferenceScoreboard : leagueScoreboard.getConferenceScoreboards()) {
            IScoreboard conferencePlayOffScoreboard = factory.createConferencePlayOffScoreboard(conferenceScoreboard);
            leaguePlayOffScoreboard.addAll(conferencePlayOffScoreboard.loadScoreboard());
            conferencePlayOffScoreboards.add((IConferencePlayOffScoreboard) conferencePlayOffScoreboard);
        }
        return leaguePlayOffScoreboard;
    }

    public List<TeamOnScoreboard> getScoreboard() {
        return leaguePlayOffScoreboard;
    }

    public List<TeamOnScoreboard> sort() {
        Collections.sort(leaguePlayOffScoreboard);
        return leaguePlayOffScoreboard;
    }

    public TeamOnScoreboard getWinner() {
        return winner;
    }

    public ITeamOnScoreboard checkWinner() {
        if (isFinal) {
            winner = sort().get(0);
            return winner;
        } else {
            for (IConferencePlayOffScoreboard conferenceScoreboard : conferencePlayOffScoreboards) {
                IPlayOffScoreboard conferencePlayOffScoreboard = (IPlayOffScoreboard) conferenceScoreboard;
                conferencePlayOffScoreboard.checkWinner();
            }
        }
        return null;
    }

    public boolean setFinal() {
        isFinal = true;
        return isFinal;
    }

    public void initiateScoreToZero() {
        for (IConferencePlayOffScoreboard conferencePlayOffScoreboard :
                conferencePlayOffScoreboards) {
            IPlayOffScoreboard playOffScoreboard = (IPlayOffScoreboard) conferencePlayOffScoreboard;
            playOffScoreboard.initiateScoreToZero();
        }
    }

    public void seed(int numberOfWildCardsPerConference) {
        for (IConferencePlayOffScoreboard conferencePlayOffScoreboard :
                conferencePlayOffScoreboards) {
            ISeed seedConference = (ISeed) conferencePlayOffScoreboard;
            seedConference.seed(numberOfWildCardsPerConference);
        }
    }

    public void seed(TeamOnScoreboard wildCard) {
        logger.error("League is not seedable. Correct usage is seed()");
    }

    public List<IConferencePlayOffScoreboard> getConferenceScoreboards() {
        return conferencePlayOffScoreboards;
    }
}
