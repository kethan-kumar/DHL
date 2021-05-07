package org.leaguemodel;

import org.junit.Test;
import org.leaguemodel.interfaces.IGeneratePlayers;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class GeneratePlayersTest {
    @Test
    public void generateTest() {
        IGeneratePlayers generatePlayers = new GeneratePlayers();
        List<Players> players = generatePlayers.generate(2);
        assertEquals(players.size(), 1);
    }
}
