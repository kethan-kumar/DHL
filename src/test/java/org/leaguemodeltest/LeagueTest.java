package org.leaguemodeltest;

import org.junit.Test;
import org.leaguemodel.*;
import org.leaguemodel.interfaces.*;
import resources.MockData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LeagueTest {

    IConference testConference = new Conferences("test conference", null);
    List<IConference> testConferenceList = new ArrayList<>();
    IPlayers testPlayer = new Players("Front", "testPlayer", true);
    List<IPlayers> testFreeAgentsList = new ArrayList<>();
    ITeam teamTest = new Team("Test 22", "AI", null, null, null);
    private String leagueName = "Test League";

    @Test
    public void getLeagueNameTest() {
        ILeague league = new League(leagueName, null, null);
        assertEquals(leagueName, league.getLeagueName());
    }

    @Test
    public void setLeagueNameTest() {
        ILeague league = new League();
        league.setLeagueName(leagueName);
        assertEquals(leagueName, league.getLeagueName());
    }

    @Test
    public void getConferenceTest() {
        testConferenceList.add(testConference);
        ILeague league = new League(leagueName, testConferenceList, null);
        assertEquals(1, league.getConferences().size());
    }

    @Test
    public void setConferenceTest() {
        testConferenceList.add(testConference);
        ILeague league = new League();
        league.setConferences(testConferenceList);
        assertEquals(1, league.getConferences().size());
    }

    @Test
    public void validateConferenceTest() {
        IValidationsInLeague testLeague = new ValidationsInLeague();
        TestData testObj = new TestData();
        assertTrue(testLeague.validateConference("Eastern Conference", testObj.getLeagueOne()));
        assertFalse(testLeague.validateConference("test conference", testObj.getLeagueOne()));
    }

    @Test
    public void validateDivisionsTest() {
        IValidationsInLeague testLeague = new ValidationsInLeague();
        TestData testObj = new TestData();
        assertTrue(testLeague.validateDivisions("Eastern Conference", "Atlantic", testObj.getLeagueOne()));
        assertFalse(testLeague.validateDivisions("Eastern Conference", "test division", testObj.getLeagueOne()));
    }

    @Test
    public void appendTeamTest() {
        IPersistence testLeague = new Persist();
        TestData testObj = new TestData();
        ILeague league = testLeague.appendTeam("Atlantic", "Eastern Conference", teamTest, testObj.getLeagueOne());
        ILeague leagueQuery = testLeague.appendTeam("Ontario", "Eastern Conference", teamTest, testObj.getLeagueOne());
        assertEquals(league.getConferences().get(0).getDivisions().get(0).getTeams().size(), 2);
        assertNull(leagueQuery);
    }

    @Test
    public void validateConferenceListTest() {
        IValidationsInLeague testLeague = new ValidationsInLeague();
        TestData testObj = new TestData();
        assertFalse(testLeague.validateConferenceList(testObj.getLeagueOne()));
    }

    @Test
    public void validateDivisionListTest() {
        IValidationsInLeague testLeague = new ValidationsInLeague();
        TestData testObj = new TestData();
        assertFalse(testLeague.validateDivisionList(testObj.getLeagueOne()));
    }

    @Test
    public void validateTeamListTest() {
        IValidationsInLeague testLeague = new ValidationsInLeague();
        TestData testObj = new TestData();
        assertFalse(testLeague.validateTeamList(testObj.getLeagueOne()));
    }

    @Test
    public void validateCaptainTest() {
        IValidationsInLeague testLeague = new ValidationsInLeague();
        TestData testObj = new TestData();
        assertFalse(testLeague.validateCaptain(testObj.getLeagueOne()));
    }

    @Test
    public void insertTeamTest() {
        MockData mockData = new MockData();
        IPersistence iPersistence = new MockData();
        iPersistence.saveTeam(mockData.leagueOne);
        assertEquals(mockData.leagueOne.getLeagueName(), "Dalhousie Hockey League");
    }

    @Test
    public void loadTeamTest() {
        MockData mockData = new MockData();
        IPersistence iPersistence = new MockData();
        ILeague testLeague = mockData.leagueOne;
        iPersistence.loadTeam("Dalhousie Hockey League", "Team 12", testLeague);
        assertEquals(testLeague.getLeagueName(), "Dalhousie Hockey League");
    }
}