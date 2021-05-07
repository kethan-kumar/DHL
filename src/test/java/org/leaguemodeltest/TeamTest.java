package org.leaguemodeltest;

import org.junit.Test;
import org.leaguemodel.*;
import org.leaguemodel.interfaces.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TeamTest {

    ArrayList<IPlayers> testPlayers = new ArrayList<>();
    IPlayers testPlayer = new Players("Front", "testPlayer", true);
    private String teamName = "Team12";
    private String generalManager = "testGM1";
    private String headCoach = "testCoach";

    @Test
    public void constructorTest() {
        ITeam testTeam = new Team();
        assertNull(testTeam.getTeamName());
        assertNull(testTeam.getHeadCoach());
        assertNull(testTeam.getGeneralManager());
        assertNull(testTeam.getPlayers());
    }

    @Test
    public void paramertizedConstructorTest() {
        testPlayers.add(testPlayer);
        IHeadCoach headCoach = new HeadCoach();
        headCoach.setName("headcoach");
        headCoach.setChecking(2.4);
        headCoach.setSaving(3.1);
        headCoach.setShooting(1.2);
        headCoach.setSkating(0.5);
        IGeneralManager testGeneralManager = new GeneralManager();
        testGeneralManager.setName("testGM1");
        testGeneralManager.setPersonality("gambler");
        ITeam testTeam = new Team(teamName, "AI", testGeneralManager, headCoach, testPlayers);
        assertEquals(teamName, testTeam.getTeamName());
        assertEquals(generalManager, testTeam.getGeneralManager().getName());
        assertEquals(headCoach, testTeam.getHeadCoach());
        assertEquals(1, testTeam.getPlayers().size());
    }

    @Test
    public void getTeamNameTest() {
        ITeam testTeam = new Team();
        testTeam.setTeamName(teamName);
        assertEquals(teamName, testTeam.getTeamName());
    }

    @Test
    public void setTeamNameTest() {
        ITeam testTeam = new Team();
        testTeam.setTeamName(teamName);
        assertEquals(teamName, testTeam.getTeamName());
    }

    @Test
    public void getHeadCoachTest() {
        ITeam testTeam = new Team();
        IHeadCoach headCoach = new HeadCoach();
        headCoach.setName("headcoach");
        headCoach.setChecking(2.4);
        headCoach.setSaving(3.1);
        headCoach.setShooting(1.2);
        headCoach.setSkating(0.5);
        testTeam.setHeadCoach(headCoach);
        assertEquals(headCoach, testTeam.getHeadCoach());
    }

    @Test
    public void setHeadCoachTest() {
        ITeam testTeam = new Team();
        IHeadCoach headCoach = new HeadCoach();
        headCoach.setName("headcoach");
        headCoach.setChecking(2.4);
        headCoach.setSaving(3.1);
        headCoach.setShooting(1.2);
        headCoach.setSkating(0.5);
        testTeam.setHeadCoach(headCoach);
        assertEquals(headCoach, testTeam.getHeadCoach());
    }

    @Test
    public void setGeneralManagerTest() {
        ITeam testTeam = new Team();
        IGeneralManager testGeneralManager = new GeneralManager();
        testGeneralManager.setName("testGM1");
        testGeneralManager.setPersonality("gambler");
        testTeam.setGeneralManager(testGeneralManager);
        assertEquals(generalManager, testTeam.getGeneralManager().getName());
    }

    @Test
    public void getGeneralManagerTest() {
        ITeam testTeam = new Team();
        IGeneralManager testGeneralManager = new GeneralManager();
        testGeneralManager.setName("testGM1");
        testGeneralManager.setPersonality("gambler");
        testTeam.setGeneralManager(testGeneralManager);
        assertEquals(testGeneralManager.getName(), testTeam.getGeneralManager().getName());
    }

    @Test
    public void setPlayersTest() {
        ITeam testTeam = new Team();
        testPlayers.add(testPlayer);
        testTeam.setPlayers(testPlayers);
        assertEquals(testPlayer.getPlayerName(), testTeam.getPlayers().get(0).getPlayerName());
        assertEquals(testPlayer.getPosition(), testTeam.getPlayers().get(0).getPosition());
        assertEquals(testPlayer.isCaptain(), testTeam.getPlayers().get(0).isCaptain());
    }

    @Test
    public void getPlayersTest() {
        ITeam testTeam = new Team();
        testPlayers.add(testPlayer);
        testTeam.setPlayers(testPlayers);
        assertEquals(testPlayer.getPlayerName(), testTeam.getPlayers().get(0).getPlayerName());
        assertEquals(testPlayer.getPosition(), testTeam.getPlayers().get(0).getPosition());
        assertEquals(testPlayer.isCaptain(), testTeam.getPlayers().get(0).isCaptain());
    }

    @Test
    public void validateTeamNameTest() {
        IValidationsInLeague testTeam = new ValidationsInLeague();
        TestData test = new TestData();
        assertTrue(testTeam.validateTeamName("Team 12", test.getLeagueOne()));
        assertFalse(testTeam.validateTeamName("Team 13", test.getLeagueOne()));
    }
}