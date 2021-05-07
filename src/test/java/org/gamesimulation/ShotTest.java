/* @Author: Vikram Singh */

package org.gamesimulation;

import org.apache.log4j.Logger;
import org.gamesimulation.abstractfactory.GameAbstractFactory;
import org.gamesimulation.interfaces.IFormation;
import org.gamesimulation.interfaces.IPlayerInGame;
import org.gamesimulation.interfaces.IShot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.leaguemodel.interfaces.IGameResolver;
import org.leaguemodel.interfaces.IGameplayConfig;
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
public class ShotTest {

    private final GameAbstractFactory factory = GameAbstractFactory.instance();
    @Mock
    IGameplayConfig gameplayConfig;
    @Mock
    TeamInGame teamInGameOne;
    @Mock
    TeamInGame teamInGameTwo;
    @Mock
    IGameResolver gameResolver;
    @Mock
    IFormation defense;
    @Mock
    IFormation forward;
    @Mock
    IPlayers player;
    IPlayerInGame playerInGame;
    @Mock
    Logger logger;

    @Before
    public void setup() {
        PowerMockito.mockStatic(Logger.class);
        when(Logger.getLogger(Shot.class.getName())).thenReturn(logger);
        doNothing().when(logger).info(anyString());
        doNothing().when(logger).debug(anyString());
    }

    @Test
    public void executeSaveByDefense() {
        PowerMockito.when(gameplayConfig.getGameResolver()).thenReturn(gameResolver);
        PowerMockito.when(gameResolver.getShotToGoalChance()).thenReturn(-1.0);
        PowerMockito.when(teamInGameTwo.getDefenseOnIce()).thenReturn(defense);
        playerInGame = factory.createPlayerInGame(player);
        PowerMockito.when(defense.getRandomPlayer()).thenReturn(playerInGame);

        IShot shot = factory.createShot(gameplayConfig);
        shot.execute(teamInGameOne, teamInGameTwo);
        assertEquals(1, playerInGame.getSaves());
    }

    @Test
    public void executeGoal() {
        PowerMockito.when(gameplayConfig.getGameResolver()).thenReturn(gameResolver);
        PowerMockito.when(gameResolver.getShotToGoalChance()).thenReturn(2.0);
        PowerMockito.when(teamInGameOne.getForwardOnIce()).thenReturn(forward);
        playerInGame = factory.createPlayerInGame(player);
        PowerMockito.when(forward.getRandomPlayer()).thenReturn(playerInGame);

        IShot shot = factory.createShot(gameplayConfig);
        shot.execute(teamInGameOne, teamInGameTwo);
        assertEquals(1, playerInGame.getGoals());
    }
}
