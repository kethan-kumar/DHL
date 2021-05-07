package org.statemachine;


import junit.framework.TestCase;
import org.junit.Test;

public class LeagueDataKeysTest extends TestCase {

    LeagueDataKeys leagueDataKeys = new LeagueDataKeys();

    @Test
    public void testGetConferenceName() {
        assertEquals("conferenceName", leagueDataKeys.getConferenceName());
    }

    @Test
    public void testGetDivisionName() {
        assertEquals("divisionName", leagueDataKeys.getDivisionName());
    }

    @Test
    public void testGetTeamName() {
        assertEquals("teamName", leagueDataKeys.getTeamName());
    }

    @Test
    public void testGetGeneralManager() {
        assertEquals("generalManager", leagueDataKeys.getGeneralManager());
    }

    @Test
    public void testGetHeadCoach() {
        assertEquals("headCoach", leagueDataKeys.getHeadCoach());
    }

    @Test
    public void testGetPlayerName() {
        assertEquals("playerName", leagueDataKeys.getPlayerName());
    }

    @Test
    public void testGetPosition() {
        assertEquals("position", leagueDataKeys.getPosition());
    }

    @Test
    public void testGetCaptain() {
        assertEquals("captain", leagueDataKeys.getCaptain());
    }

    @Test
    public void testGetDivisions() {
        assertEquals("divisions", leagueDataKeys.getDivisions());
    }

    @Test
    public void testGetTeams() {
        assertEquals("teams", leagueDataKeys.getTeams());
    }

    @Test
    public void testGetPlayers() {
        assertEquals("players", leagueDataKeys.getPlayers());
    }

    @Test
    public void testGetFreeAgents() {
        assertEquals("freeAgents", leagueDataKeys.getFreeAgents());
    }

    @Test
    public void testGetLeagueName() {
        assertEquals("leagueName", leagueDataKeys.getLeagueName());
    }

    @Test
    public void testGetConferences() {
        assertEquals("conferences", leagueDataKeys.getConferences());
    }
}