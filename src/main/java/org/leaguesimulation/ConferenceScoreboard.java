/* @Author: Vikram Singh */

package org.leaguesimulation;

import org.leaguemodel.interfaces.IConference;
import org.leaguemodel.interfaces.IDivisions;
import org.leaguesimulation.abstractfactory.LeagueAbstractFactory;
import org.leaguesimulation.interfaces.IConferenceScoreboard;
import org.leaguesimulation.interfaces.IScoreboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConferenceScoreboard implements IScoreboard, IConferenceScoreboard {

    private final LeagueAbstractFactory factory;
    private final IConference conference;
    private final List<TeamOnScoreboard> scoreboard;
    private final List<IScoreboard> divisionScoreboards;

    public ConferenceScoreboard(IConference conference) {
        this.conference = conference;
        scoreboard = new ArrayList<>();
        divisionScoreboards = new ArrayList<>();
        factory = LeagueAbstractFactory.instance();
    }

    public List<TeamOnScoreboard> getScoreboard() {
        return scoreboard;
    }

    public List<TeamOnScoreboard> loadScoreboard() {
        for (IDivisions division : conference.getDivisions()) {
            IScoreboard divisionScoreboard = factory.createDivisionScoreboard(division);
            scoreboard.addAll(divisionScoreboard.loadScoreboard());
            divisionScoreboards.add(divisionScoreboard);
        }
        return scoreboard;
    }

    public List<TeamOnScoreboard> sort() {
        Collections.sort(scoreboard);
        return scoreboard;
    }

    public List<IScoreboard> getDivisionScoreboards() {
        return divisionScoreboards;
    }
}
