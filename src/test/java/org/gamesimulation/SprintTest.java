/* @Author: Vikram Singh */

package org.gamesimulation;

import org.apache.log4j.Logger;
import org.gamesimulation.abstractfactory.GameAbstractFactory;
import org.gamesimulation.abstractfactory.GameSimulationFactory;
import org.gamesimulation.interfaces.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.leaguemodel.interfaces.IGameplayConfig;
import org.leaguemodel.interfaces.IPlayers;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GameAbstractFactory.class, Logger.class})
public class SprintTest {

    @Mock
    IGameplayConfig gameplayConfig;
    @Mock
    TeamInGame teamInGameOne;
    @Mock
    TeamInGame teamInGameTwo;
    @Mock
    IPlayers player;
    @Mock
    IPenalty penalty;
    @Mock
    IShot shot;
    @Mock
    IFormation forwardOnIce;
    @Mock
    IFormation defenseOnIce;
    IPlayerInGame playerInGame;
    @Mock
    GameSimulationFactory factory;
    @Mock
    Logger logger;

    @Before
    public void setup() {
        PowerMockito.mockStatic(Logger.class);
        when(Logger.getLogger(Sprint.class.getName())).thenReturn(logger);
        doNothing().when(logger).info(anyString());
        doNothing().when(logger).debug(anyString());
        PowerMockito.mockStatic(GameAbstractFactory.class);
        when(GameAbstractFactory.instance()).thenReturn(factory);
        when(factory.createPenalty(gameplayConfig)).thenReturn(penalty);
        when(factory.createShot(gameplayConfig)).thenReturn(shot);
        when(factory.createSprint(gameplayConfig)).thenCallRealMethod();
        when(teamInGameOne.getForwardOnIce()).thenReturn(forwardOnIce);
        when(teamInGameOne.getDefenseOnIce()).thenReturn(defenseOnIce);
        when(teamInGameTwo.getForwardOnIce()).thenReturn(forwardOnIce);
        when(teamInGameTwo.getDefenseOnIce()).thenReturn(defenseOnIce);

    }

    @Test
    public void executeStrongOffenseGoalByTeamOne() {
        when(forwardOnIce.getStats()).thenReturn(20.0);
        when(defenseOnIce.getStats()).thenReturn(10.0);
        when(shot.execute(teamInGameOne, teamInGameTwo)).thenReturn(false);
        ISprint sprint = factory.createSprint(gameplayConfig);
        sprint.execute(teamInGameOne, teamInGameTwo);
        verify(shot, times(2)).execute(teamInGameOne, teamInGameTwo);
    }

    @Test
    public void executeStrongOffenseMissByTeamOne() {
        when(forwardOnIce.getStats()).thenReturn(20.0);
        when(defenseOnIce.getStats()).thenReturn(10.0);
        when(shot.execute(teamInGameOne, teamInGameTwo)).thenReturn(true);
        PowerMockito.doNothing().when(penalty).execute(teamInGameOne, teamInGameTwo);
        ISprint sprint = factory.createSprint(gameplayConfig);
        sprint.execute(teamInGameOne, teamInGameTwo);
        verify(shot, times(2)).execute(teamInGameOne, teamInGameTwo);
        verify(penalty, times(1)).execute(teamInGameOne, teamInGameTwo);
    }

    @Test
    public void executeShotByBothTeams() {
        when(forwardOnIce.getStats()).thenReturn(20.0);
        when(defenseOnIce.getStats()).thenReturn(19.0);
        when(shot.execute(teamInGameOne, teamInGameTwo)).thenReturn(false);
        ISprint sprint = factory.createSprint(gameplayConfig);
        sprint.execute(teamInGameOne, teamInGameTwo);
        verify(shot, times(1)).execute(teamInGameOne, teamInGameTwo);
        verify(shot, times(1)).execute(teamInGameTwo, teamInGameOne);
    }
}
