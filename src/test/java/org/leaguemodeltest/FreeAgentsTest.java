package org.leaguemodeltest;

import junit.framework.TestCase;
import org.junit.Test;
import org.leaguemodel.FreeAgents;
import org.leaguemodel.Players;
import org.leaguemodel.interfaces.IPlayers;

import java.util.ArrayList;

public class FreeAgentsTest extends TestCase {

    ArrayList<IPlayers> testPlayers = new ArrayList<>();
    IPlayers testPlayer = new Players("Front", "testPlayer", true);

    @Test
    public void testSetPlayers() {
        testPlayers.add(testPlayer);
        IPlayers freeAgents = new FreeAgents("defence", "A1", 20, 10, 30, 10, 20);
        freeAgents.setPlayerName("A1");
        freeAgents.setPosition("defense");
        freeAgents.setAge(10);
        freeAgents.setChecking(15);
        freeAgents.setSaving(0);
        freeAgents.setShooting(12);
        freeAgents.setSkating(18);
        assertEquals((Integer) 10, freeAgents.getAge());
    }

    @Test
    public void testGetPlayers() {
        testPlayers.add(testPlayer);
        IPlayers freeAgents = new FreeAgents("defence", "A1", 20, 10, 30, 10, 20);
        freeAgents.setPlayerName("A1");
        freeAgents.setPosition("defense");
        freeAgents.setAge(10);
        freeAgents.setChecking(15);
        freeAgents.setSaving(0);
        freeAgents.setShooting(12);
        freeAgents.setSkating(18);
        assertEquals((Integer) 10, freeAgents.getAge());
    }
}
