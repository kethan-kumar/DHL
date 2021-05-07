/* @Author: Vikram Singh */

package org.leaguesimulation;

import org.leaguemodel.interfaces.IConference;
import org.leaguemodel.interfaces.ILeague;
import org.leaguesimulation.abstractfactory.LeagueAbstractFactory;
import org.leaguesimulation.interfaces.IConferenceScoreboard;
import org.leaguesimulation.interfaces.ILeagueScoreboard;
import org.leaguesimulation.interfaces.IScoreboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeagueScoreboard implements IScoreboard, ILeagueScoreboard {

    private final LeagueAbstractFactory factory;
    private final ILeague league;
    private final List<TeamOnScoreboard> scoreboard;
    private final List<IConferenceScoreboard> conferenceScoreboards;

    public LeagueScoreboard(ILeague league) {
        this.league = league;
        scoreboard = new ArrayList<>();
        conferenceScoreboards = new ArrayList<>();
        factory = LeagueAbstractFactory.instance();
    }

    public List<TeamOnScoreboard> getScoreboard() {
        return scoreboard;
    }

    public List<TeamOnScoreboard> loadScoreboard() {
        for (IConference conference : league.getConferences()) {
            IScoreboard conferenceScoreboard = factory.createConferenceScoreboard(conference);
            scoreboard.addAll(conferenceScoreboard.loadScoreboard());
            conferenceScoreboards.add((IConferenceScoreboard) conferenceScoreboard);
        }
        return scoreboard;
    }

    public List<TeamOnScoreboard> sort() {
        Collections.sort(scoreboard);
        return scoreboard;
    }

    public List<IConferenceScoreboard> getConferenceScoreboards() {
        return conferenceScoreboards;
    }
}
