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
import org.leaguesimulation.interfaces.IGame;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RegularSeasonSchedulerTest {

    ILeague league;
    IConference conference;
    IDivisions division;
    private ArrayList<ITeam> teams;
    private ArrayList<IDivisions> divisions;
    private ArrayList<IConference> conferences;
    private ITeam teamOne;
    private ITeam teamTwo;

    @Before
    public void setUp() {
        conference = new Conferences();
        division = new Divisions();
        league = new League();
        teamOne = new Team();
        teamTwo = new Team();
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
    public void schedule() {
        List<Game> expectedGames = new ArrayList<>();
        TeamOnScoreboard teamOnScoreboardOne = new TeamOnScoreboard(teamOne);
        TeamOnScoreboard teamOnScoreboardTwo = new TeamOnScoreboard(teamTwo);

        expectedGames.add(new Game(teamOnScoreboardOne, teamOnScoreboardTwo));
        expectedGames.add(new Game(teamOnScoreboardTwo, teamOnScoreboardOne));

        LeagueScoreboard leagueScoreboard = new LeagueScoreboard(league);
        leagueScoreboard.loadScoreboard();
        RegularSeasonScheduler regularSeasonScheduler = new RegularSeasonScheduler(leagueScoreboard);
        List<IGame> actualGames = regularSeasonScheduler.schedule(2);
        assertEquals(expectedGames.size(), actualGames.size());
    }
}
