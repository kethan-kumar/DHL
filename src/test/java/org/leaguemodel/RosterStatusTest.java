package org.leaguemodel;

import org.junit.Before;
import org.junit.Test;
import org.leaguemodel.interfaces.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RosterStatusTest {
    ILeague league;
    IConference conference;
    IDivisions division;
    List<ITeam> teams;
    List<IDivisions> divisions;
    List<IPlayers> playerList = new ArrayList<>();
    List<IConference> conferences;

    @Before
    public void setUp() {
        conference = new Conferences();
        division = new Divisions();
        league = new League();
        ITeam teamOne = new Team();
        teamOne.setTeamName("teamOne");
        IPlayers playerThree = new Players("Goalie", "Player three", true);
        teams = new ArrayList<>();
        teams.add(teamOne);
        playerList.add(playerThree);
        teamOne.setPlayers(playerList);
        division.setTeams(teams);
        divisions = new ArrayList<>();
        conferences = new ArrayList<>();
        divisions.add(division);
        conference.setDivisions(divisions);
        conferences.add(conference);
        this.league.setConferences(conferences);
    }

    @Test
    public void setStatusInactiveTest() {

        IRosterStatus roster = new RosterStatus(league);
        roster.setStatusInactive();
        assertFalse(league.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getIsActive());
    }

    @Test
    public void setStatusActiveTest() {
        IRosterStatus roster = new RosterStatus(league);
        roster.setStatusActive();
        assertTrue(league.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getIsActive());
    }
}