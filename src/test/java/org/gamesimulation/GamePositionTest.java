package org.gamesimulation;

import org.gamesimulation.interfaces.IGamePosition;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GamePositionTest {

    @Test
    public void getPosition() {
        IGamePosition gamePosition = new GamePosition();
        assertEquals(GamePosition.position.FORWARD, gamePosition.getPosition("FORWARD"));
    }
}
