/* @Author: Vikram Singh */

package org.gamesimulation;

import org.apache.log4j.Logger;
import org.gamesimulation.abstractfactory.GameAbstractFactory;
import org.gamesimulation.interfaces.IPlayerInGame;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.leaguemodel.interfaces.IPlayers;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Logger.class)
public class PlayerInGameTest {

    private final GameAbstractFactory factory = GameAbstractFactory.instance();
    @Mock
    IPlayers player;
    IPlayerInGame playerInGame;
    @Mock
    Logger logger;

    @Before
    public void setup() {
        PowerMockito.mockStatic(Logger.class);
        when(Logger.getLogger(PlayerInGame.class.getName())).thenReturn(logger);
        doNothing().when(logger).info(anyString());
        doNothing().when(logger).debug(anyString());
        playerInGame = factory.createPlayerInGame(player);
    }

    @Test
    public void getPlayer() {
        assertEquals(player, playerInGame.getPlayer());
    }

    @Test
    public void incrementGoals() {
        playerInGame.incrementGoals();
        assertEquals(1, playerInGame.getGoals());
    }

    @Test
    public void incrementShots() {
        playerInGame.incrementShots();
        assertEquals(1, playerInGame.getShots());
    }

    @Test
    public void incrementPenalties() {
        playerInGame.incrementPenalties();
        assertEquals(1, playerInGame.getPenalties());
    }

    @Test
    public void incrementSaves() {
        playerInGame.incrementSaves();
        assertEquals(1, playerInGame.getSaves());
    }
}
