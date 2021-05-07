/* @Author: Vikram Singh */

package org.gamesimulation;

import org.apache.log4j.Logger;
import org.gamesimulation.abstractfactory.GameAbstractFactory;
import org.gamesimulation.abstractfactory.GameSimulationFactory;
import org.gamesimulation.interfaces.IGameSimulation;
import org.gamesimulation.interfaces.IGameTime;
import org.gamesimulation.interfaces.IPlayerInGame;
import org.gamesimulation.interfaces.ISprint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.leaguemodel.interfaces.IGameplayConfig;
import org.leaguemodel.interfaces.ITeam;
import org.leaguesimulation.abstractfactory.LeagueAbstractFactory;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GameAbstractFactory.class, TeamInGame.class, Logger.class})
public class GameSimulationTest {

    IGameSimulation gameSimulation;
    TeamInGame teamInGameOne;
    TeamInGame teamInGameTwo;
    @Mock
    ITeam teamOne;
    @Mock
    ITeam teamTwo;
    @Mock
    IGameplayConfig gameplayConfig;
    @Mock
    ISprint sprint;
    @Mock
    IGameTime gameTime;
    @Mock
    IPlayerInGame player;
    @Mock
    GameSimulationFactory factory;
    LeagueAbstractFactory leagueSimulationFactory;
    @Mock
    Logger logger;

    @Before
    public void setup() {
        PowerMockito.mockStatic(Logger.class);
        when(Logger.getLogger(GameSimulation.class.getName())).thenReturn(logger);
        doNothing().when(logger).info(anyString());
        doNothing().when(logger).debug(anyString());
        leagueSimulationFactory = LeagueAbstractFactory.instance();
        PowerMockito.mockStatic(GameAbstractFactory.class);
        when(GameAbstractFactory.instance()).thenReturn(factory);
        teamInGameOne = PowerMockito.mock(TeamInGame.class);
        teamInGameTwo = PowerMockito.mock(TeamInGame.class);
        when(factory.createShift(teamOne)).thenReturn(teamInGameOne);
        when(factory.createShift(teamTwo)).thenReturn(teamInGameTwo);
    }

    @Test
    public void execute() {
        doNothing().when(teamInGameOne).setInitialTeamOnIce();
        doNothing().when(teamInGameTwo).setInitialTeamOnIce();
        doNothing().when(sprint).execute(teamInGameOne, teamInGameTwo);
        doNothing().when(teamInGameOne).shuffle();
        doNothing().when(teamInGameTwo).shuffle();
        when(teamInGameOne.getTeam()).thenReturn(teamOne);
        when(teamInGameTwo.getTeam()).thenReturn(teamTwo);
        when(gameTime.getTotalTimeInSeconds()).thenReturn(10);
        when(gameTime.getTimeJumpInSeconds()).thenReturn(9);
        when(gameTime.isShiftTime(anyInt())).thenReturn(true);
        when(gameTime.isShotTime(anyInt())).thenReturn(true);
        when(factory.createSprint(gameplayConfig)).thenReturn(sprint);
        when(factory.createGameTime()).thenReturn(gameTime);
        gameSimulation = leagueSimulationFactory.createGameSimulation(teamOne, teamTwo, gameplayConfig);
        gameSimulation.execute();
        verify(teamInGameOne, Mockito.times(2)).shuffle();
        verify(teamInGameTwo, Mockito.times(2)).shuffle();
        verify(sprint, Mockito.times(2)).execute(teamInGameOne, teamInGameTwo);
    }

    @Test
    public void getPlayers() {
        List<IPlayerInGame> players = new ArrayList<>();
        players.add(player);
        when(teamInGameOne.getPlayers()).thenReturn(players);
        when(teamInGameTwo.getPlayers()).thenReturn(players);
        gameSimulation = leagueSimulationFactory.createGameSimulation(teamOne, teamTwo, gameplayConfig);
        List<IPlayerInGame> actualCol = gameSimulation.getPlayers();
        List<IPlayerInGame> expectedCol = new ArrayList<>();
        expectedCol.addAll(players);
        expectedCol.addAll(players);
        assertEquals(actualCol, expectedCol);
    }

    @Test
    public void getTeam() {
        when(factory.createShift(teamOne)).thenReturn(teamInGameOne);
        when(factory.createShift(teamTwo)).thenReturn(teamInGameTwo);
        gameSimulation = leagueSimulationFactory.createGameSimulation(teamOne, teamTwo, gameplayConfig);
        assertEquals(teamInGameOne, gameSimulation.getTeamOne());
        assertEquals(teamInGameTwo, gameSimulation.getTeamTwo());
    }

    @Test
    public void getResult() {
        when(factory.createShift(teamOne)).thenReturn(teamInGameOne);
        when(factory.createShift(teamTwo)).thenReturn(teamInGameTwo);
        when(teamInGameOne.getGoals()).thenReturn(10);
        when(teamInGameTwo.getGoals()).thenReturn(20);
        gameSimulation = leagueSimulationFactory.createGameSimulation(teamOne, teamTwo, gameplayConfig);
        assertEquals(teamInGameTwo, gameSimulation.getWinner());
        assertEquals(teamInGameOne, gameSimulation.getLoser());
    }

}
