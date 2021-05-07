/* @Author: Vikram Singh */

package org.leaguesimulation;

import org.junit.Before;
import org.junit.Test;
import org.leaguemodel.Divisions;
import org.leaguemodel.Team;
import org.leaguemodel.interfaces.IDivisions;
import org.leaguemodel.interfaces.ITeam;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DivisionScoreboardTest {

    IDivisions division;
    private ArrayList<ITeam> teams;

    @Before
    public void setUp() {
        division = new Divisions();
        ITeam teamOne = new Team();
        ITeam teamTwo = new Team();
        teamOne.setTeamName("teamOne");
        teamTwo.setTeamName("teamTwo");
        teams = new ArrayList<>();
        teams.add(teamOne);
        teams.add(teamTwo);
        division.setTeams(this.teams);
    }

    @Test
    public void loadScoreboard() {
        DivisionScoreboard divisionScoreboard = new DivisionScoreboard(division);
        List<TeamOnScoreboard> scoreboard = divisionScoreboard.loadScoreboard();
        assertEquals("teamOne", scoreboard.get(0).getTeam().getTeamName());
        assertEquals("teamTwo", scoreboard.get(1).getTeam().getTeamName());
    }

    @Test
    public void sort() {
        DivisionScoreboard divisionScoreboard = new DivisionScoreboard(division);
        List<TeamOnScoreboard> scoreboard = divisionScoreboard.loadScoreboard();
        scoreboard.get(1).setScore(10);
        assertEquals(10, divisionScoreboard.sort().get(0).getScore());
    }

    @Test
    public void getScoreboard() {
        DivisionScoreboard divisionScoreboard = new DivisionScoreboard(division);
        List<TeamOnScoreboard> scoreboard = divisionScoreboard.loadScoreboard();
        assertEquals(scoreboard, divisionScoreboard.getScoreboard());
    }
}
