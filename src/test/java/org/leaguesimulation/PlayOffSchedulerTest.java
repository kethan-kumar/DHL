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

import static org.junit.Assert.assertEquals;

public class PlayOffSchedulerTest {

    ILeague league;
    IConference conference;
    IDivisions division;
    private ArrayList<ITeam> teams;
    private ArrayList<IDivisions> divisions;
    private ArrayList<IConference> conferences;
    private ITeam teamOne;
    private ITeam teamTwo;

    @Before
    public void setUp() throws Exception {
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
        LeaguePlayOff leaguePlayOff = new LeaguePlayOff(new LeagueScoreboard(league));
        PlayOffScheduler playOffScheduler = new PlayOffScheduler(leaguePlayOff);
        assertEquals(0, playOffScheduler.schedule(2).size());
    }
}
