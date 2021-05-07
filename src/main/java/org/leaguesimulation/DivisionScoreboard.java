/* @Author: Vikram Singh */

package org.leaguesimulation;

import org.leaguemodel.interfaces.IDivisions;
import org.leaguemodel.interfaces.ITeam;
import org.leaguesimulation.abstractfactory.LeagueAbstractFactory;
import org.leaguesimulation.interfaces.IScoreboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DivisionScoreboard implements IScoreboard {

    private final LeagueAbstractFactory factory;
    private final IDivisions division;
    private final List<TeamOnScoreboard> scoreboard;

    public DivisionScoreboard(IDivisions division) {
        this.division = division;
        scoreboard = new ArrayList<>();
        factory = LeagueAbstractFactory.instance();
    }

    public List<TeamOnScoreboard> getScoreboard() {
        return scoreboard;
    }

    public List<TeamOnScoreboard> loadScoreboard() {
        for (ITeam team : division.getTeams()) {
            scoreboard.add(factory.createTeamOnScoreboard(team));
        }
        return scoreboard;
    }

    public List<TeamOnScoreboard> sort() {
        Collections.sort(scoreboard);
        return scoreboard;
    }
}
