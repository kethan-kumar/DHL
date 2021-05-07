/* @Author: Vikram Singh */

package org.gamesimulation;

import org.apache.log4j.Logger;
import org.gamesimulation.abstractfactory.GameAbstractFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.ITeam;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Logger.class)
public class TeamOnIceTest {
    public static final int PLAYERS_IN_FORWARD_LINE = 3;
    public static final int PLAYERS_IN_DEFENSE_LINE = 2;
    public static final int PLAYERS_IN_GOALIE_LINE = 1;
    TeamInGame teamInGame;
    @Mock
    ITeam team;
    @Mock
    IPlayers playerOne;
    @Mock
    IPlayers playerTwo;
    @Mock
    IPlayers playerThree;
    @Mock
    IPlayers playerFour;
    @Mock
    IPlayers playerFive;
    @Mock
    IPlayers playerSix;
    @Mock
    Logger logger;

    @Before
    public void setup() {
        PowerMockito.mockStatic(Logger.class);
        when(Logger.getLogger(anyString())).thenReturn(logger);
        doNothing().when(logger).info(anyString());
        doNothing().when(logger).debug(anyString());
        List<IPlayers> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        players.add(playerThree);
        players.add(playerFour);
        players.add(playerFive);
        players.add(playerSix);
        PowerMockito.when(playerOne.getPosition()).thenReturn(GamePosition.position.FORWARD.toString());
        PowerMockito.when(playerTwo.getPosition()).thenReturn(GamePosition.position.FORWARD.toString());
        PowerMockito.when(playerThree.getPosition()).thenReturn(GamePosition.position.FORWARD.toString());
        PowerMockito.when(playerFour.getPosition()).thenReturn(GamePosition.position.DEFENSE.toString());
        PowerMockito.when(playerFive.getPosition()).thenReturn(GamePosition.position.DEFENSE.toString());
        PowerMockito.when(playerSix.getPosition()).thenReturn(GamePosition.position.GOALIE.toString());
        PowerMockito.when(team.getPlayers()).thenReturn(players);
        teamInGame = GameAbstractFactory.instance().createShift(team);
    }

    @Test
    public void getForwardLines() {
        teamInGame.buildLines();
        int count = teamInGame.getForwardLines().get(0).getPlayers().size();
        assertEquals(PLAYERS_IN_FORWARD_LINE, count);
    }

    @Test
    public void getDefenseLines() {
        teamInGame.buildLines();
        int count = teamInGame.getDefenseLines().get(0).getPlayers().size();
        assertEquals(PLAYERS_IN_DEFENSE_LINE, count);
    }

    @Test
    public void getGoalies() {
        teamInGame.buildLines();
        int count = teamInGame.getGoalies().get(0).getPlayers().size();
        assertEquals(PLAYERS_IN_GOALIE_LINE, count);
    }

    @Test
    public void getTeam() {
        assertEquals(team, teamInGame.getTeam());
    }

    @Test
    public void getGoals() {
        teamInGame.incrementGoals();
        assertEquals(1, teamInGame.getGoals());
    }

    @Test
    public void getShots() {
        teamInGame.incrementShots();
        assertEquals(1, teamInGame.getShots());
    }

    @Test
    public void getPenalties() {
        teamInGame.incrementPenalties();
        assertEquals(1, teamInGame.getPenalties());
    }

    @Test
    public void getSaves() {
        teamInGame.incrementSaves();
        assertEquals(1, teamInGame.getSaves());
    }

    @Test
    public void buildLines() {
        boolean isLinesBuilt = teamInGame.buildLines();
        assertTrue(isLinesBuilt);
    }

    @Test
    public void getPlayers() {
        teamInGame.buildLines();
        int countOfPlayers = teamInGame.getPlayers().size();
        assertEquals(team.getPlayers().size(), countOfPlayers);
    }

    @Test
    public void getForwardOnIce() {
        teamInGame.buildLines();
        teamInGame.setInitialTeamOnIce();
        assertEquals(teamInGame.getForwardLines().get(0), teamInGame.getForwardOnIce());
    }

    @Test
    public void getDefenseOnIce() {
        teamInGame.buildLines();
        teamInGame.setInitialTeamOnIce();
        assertEquals(teamInGame.getDefenseLines().get(0), teamInGame.getDefenseOnIce());
    }

    @Test
    public void getGoalieOnIce() {
        teamInGame.buildLines();
        teamInGame.setInitialTeamOnIce();
        assertEquals(teamInGame.getGoalies().get(0), teamInGame.getGoalieOnIce());
    }

    @Test
    public void shuffle() {
        teamInGame.buildLines();
        teamInGame.setInitialTeamOnIce();
        teamInGame.shuffle();
        assertEquals(teamInGame.getForwardLines().get(0), teamInGame.getForwardOnIce());
    }
}
