/* @Author: Vikram Singh */

package org.leaguesimulation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.leaguemodel.CheckStrength;
import org.leaguemodel.GameResolver;
import org.leaguemodel.GameplayConfig;
import org.leaguemodel.Team;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Game.class)
public class GameTest {

    private Game game;
    @Mock
    private CheckStrength checkStrength;
    private TeamOnScoreboard teamOne;
    private TeamOnScoreboard teamTwo;
    private GameplayConfig gameplayConfig;


    @Before
    public void setUp() throws Exception {
        teamOne = new TeamOnScoreboard();
        teamTwo = new TeamOnScoreboard();
        teamOne.setTeam(new Team());
        teamTwo.setTeam(new Team());
        gameplayConfig = new GameplayConfig();
        GameResolver gameResolver = new GameResolver();
        gameResolver.setPenaltyChance(0.1);
        gameResolver.setShotToGoalChance(0.1);
        gameResolver.setPenaltyToGoalChance(0.1);
        gameplayConfig.setGameResolver(gameResolver);
        PowerMockito.whenNew(CheckStrength.class).withNoArguments().thenReturn(checkStrength);
        when(checkStrength.teamStrength(any())).thenReturn(10.0);
    }

    @Test
    public void executeGame() {
    }

    @Test
    public void updateScores() {
        game = Mockito.spy(new Game(teamOne, teamTwo));
        game.updateScores(teamOne, teamTwo);
        assertEquals(2, teamOne.getScore());
        assertEquals(0, teamOne.getLossPointsForTrade());
        assertEquals(2, teamTwo.getLossPointsForTrade());
        assertEquals(-2, teamTwo.getScore());
    }
}
