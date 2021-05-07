/* @Author: Vikram Singh */

package org.leaguesimulation;

import org.junit.Before;
import org.junit.Test;
import org.leaguemodel.Conferences;
import org.leaguemodel.Divisions;
import org.leaguemodel.League;
import org.leaguemodel.Team;
import org.leaguemodel.interfaces.IConference;
import org.leaguemodel.interfaces.IDivisions;
import org.leaguemodel.interfaces.ILeague;
import org.leaguemodel.interfaces.ITeam;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LeagueScoreboardTest {

    ILeague league;
    IConference conference;
    IDivisions division;
    private ArrayList<ITeam> teams;
    private ArrayList<IDivisions> divisions;
    private ArrayList<IConference> conferences;

    @Before
    public void setUp() {
        conference = new Conferences();
        division = new Divisions();
        league = new League();
        ITeam teamOne = new Team();
        ITeam teamTwo = new Team();
        teamOne.setTeamName("teamOne");
        teamTwo.setTeamName("teamTwo");
        teams = new ArrayList<>();
        teams.add(teamOne);
        teams.add(teamTwo);
        division.setTeams(teams);
        divisions = new ArrayList<>();
        conferences = new ArrayList<>();
        divisions.add(division);
        conference.setDivisions(divisions);
        conferences.add(conference);
        league.setConferences(conferences);
    }

    @Test
    public void loadScoreboard() {
        LeagueScoreboard leagueScoreboard = new LeagueScoreboard(league);
        List<TeamOnScoreboard> scoreboard = leagueScoreboard.loadScoreboard();
        assertEquals("teamOne", scoreboard.get(0).getTeam().getTeamName());
        assertEquals("teamTwo", scoreboard.get(1).getTeam().getTeamName());
    }

    @Test
    public void sort() {
        LeagueScoreboard leagueScoreboard = new LeagueScoreboard(league);
        List<TeamOnScoreboard> scoreboard = leagueScoreboard.loadScoreboard();
        scoreboard.get(1).setScore(10);
        assertEquals(10, leagueScoreboard.sort().get(0).getScore());
    }

    @Test
    public void getScoreboard() {
        LeagueScoreboard leagueScoreboard = new LeagueScoreboard(league);
        List<TeamOnScoreboard> scoreboard = leagueScoreboard.loadScoreboard();
        assertEquals(scoreboard, leagueScoreboard.getScoreboard());
    }
}
