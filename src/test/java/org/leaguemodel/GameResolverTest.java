package org.leaguemodel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameResolverTest {

    @Test
    public void getPenaltyChance() {
        Double penaltyChance = 1.0;
        GameResolver gameResolver = new GameResolver();
        gameResolver.setPenaltyChance(penaltyChance);
        assertEquals(gameResolver.getPenaltyChance(), penaltyChance);
    }

    @Test
    public void getShotToGoalChance() {
        Double shotToGoalChance = 2.0;
        GameResolver gameResolver = new GameResolver();
        gameResolver.setShotToGoalChance(shotToGoalChance);
        assertEquals(gameResolver.getShotToGoalChance(), shotToGoalChance);
    }

    @Test
    public void getPenaltyToGoalChance() {
        Double penaltyToGoalChance = 3.0;
        GameResolver gameResolver = new GameResolver();
        gameResolver.setPenaltyToGoalChance(penaltyToGoalChance);
        assertEquals(gameResolver.getPenaltyToGoalChance(), penaltyToGoalChance);
    }
}
