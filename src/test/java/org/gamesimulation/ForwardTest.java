/* @Author: Vikram Singh */

package org.gamesimulation;

import org.apache.log4j.Logger;
import org.gamesimulation.abstractfactory.GameAbstractFactory;
import org.gamesimulation.interfaces.IFormation;
import org.gamesimulation.interfaces.IPlayerInGame;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.leaguemodel.interfaces.IPlayers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Logger.class)
public class ForwardTest {

    private final GameAbstractFactory factory = GameAbstractFactory.instance();
    @Mock
    IPlayerInGame playerInGame;
    @Mock
    IPlayers player;
    @Mock
    Logger logger;

    @Before
    public void setup() {
        PowerMockito.mockStatic(Logger.class);
        when(Logger.getLogger(Forward.class.getName())).thenReturn(logger);
        doNothing().when(logger).info(anyString());
        doNothing().when(logger).debug(anyString());
    }

    @Test
    public void getFormationType() {
        IFormation forward = factory.createFormation(GamePosition.position.FORWARD);
        assertEquals(GamePosition.position.FORWARD, forward.getFormationType());
    }

    @Test
    public void getPlayers() {
        IFormation forward = factory.createFormation(GamePosition.position.FORWARD);
        forward.addPlayer(playerInGame);
        List<IPlayerInGame> players = new ArrayList<>();
        players.add(playerInGame);
        assertEquals(players, forward.getPlayers());
    }

    @Test
    public void getStats() {
        Mockito.when(playerInGame.getPlayer()).thenReturn(player);
        Mockito.when(player.getSkating()).thenReturn(10);
        Mockito.when(player.getShooting()).thenReturn(10);
        IFormation forward = factory.createFormation(GamePosition.position.FORWARD);
        forward.addPlayer(playerInGame);
        assertEquals(20, forward.getStats(), 0.1);
    }

    @Test
    public void getRandomPlayer() {
        IFormation forward = factory.createFormation(GamePosition.position.FORWARD);
        forward.addPlayer(playerInGame);
        assertEquals(playerInGame, forward.getRandomPlayer());
    }
}
