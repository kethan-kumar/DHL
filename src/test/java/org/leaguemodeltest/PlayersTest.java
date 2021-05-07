package org.leaguemodeltest;

import org.junit.Test;
import org.leaguemodel.Players;
import org.leaguemodel.interfaces.IPlayers;

import static org.junit.Assert.*;

public class PlayersTest {

    private String playerName = "User";
    private String playerPosition = "Front";
    private boolean isCaptain = true;

    @Test
    public void contructorTest() {
        IPlayers testPlayer = new Players();
        assertNull(testPlayer.getPlayerName());
        assertNull(testPlayer.getPosition());
        assertFalse(testPlayer.isCaptain());
    }

    @Test
    public void parameterizedConstructorTest() {
        IPlayers testPlayer = new Players("Front", "test", true);
        assertEquals("test", testPlayer.getPlayerName());
        assertEquals("Front", testPlayer.getPosition());
        assertTrue(testPlayer.isCaptain());
    }

    @Test
    public void getPlayerNameTest() {
        IPlayers testPlayer = new Players();
        testPlayer.setPlayerName(playerName);
        assertEquals(playerName, testPlayer.getPlayerName());
    }

    @Test
    public void setPlayerNameTest() {
        IPlayers testPlayer = new Players();
        testPlayer.setPlayerName(playerName);
        assertEquals(playerName, testPlayer.getPlayerName());
    }

    @Test
    public void getPlayerPositionTest() {
        IPlayers testPlayer = new Players();
        testPlayer.setPosition(playerPosition);
        assertEquals(playerPosition, testPlayer.getPosition());
    }

    @Test
    public void setPlayerPositionTest() {
        IPlayers testPlayer = new Players();
        testPlayer.setPosition(playerPosition);
        assertEquals(playerPosition, testPlayer.getPosition());
    }

    @Test
    public void isCaptainTest() {
        IPlayers testPlayer = new Players();
        testPlayer.setIsCaptain(isCaptain);
        assertTrue(testPlayer.isCaptain());
    }

    @Test
    public void setIsCaptainTest() {
        IPlayers testPlayer = new Players();
        testPlayer.setIsCaptain(isCaptain);
        assertTrue(testPlayer.isCaptain());
    }

}
