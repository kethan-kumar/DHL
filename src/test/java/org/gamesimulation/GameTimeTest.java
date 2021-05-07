/* @Author: Vikram Singh */

package org.gamesimulation;

import org.apache.log4j.Logger;
import org.gamesimulation.abstractfactory.GameAbstractFactory;
import org.gamesimulation.interfaces.IGameTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Logger.class)
public class GameTimeTest {
    private final GameAbstractFactory factory = GameAbstractFactory.instance();
    IGameTime gameTime;
    @Mock
    Logger logger;

    @Before
    public void setup() {
        PowerMockito.mockStatic(Logger.class);
        when(Logger.getLogger(GameTime.class.getName())).thenReturn(logger);
        doNothing().when(logger).info(anyString());
        doNothing().when(logger).debug(anyString());
        gameTime = factory.createGameTime();
    }

    @Test
    public void getTimeJumpInSeconds() {
        int jump = gameTime.getTimeJumpInSeconds();
        assertEquals(30, jump);
    }

    @Test
    public void getTotalTimeInSeconds() {
        int totalTime = gameTime.getTotalTimeInSeconds();
        assertEquals(3600, totalTime);
    }

    @Test
    public void isShiftTime() {
        boolean isShiftTime = gameTime.isShiftTime(90);
        assertTrue(isShiftTime);
    }

    @Test
    public void isShotTime() {
        boolean isShotTime = gameTime.isShotTime(120);
        assertTrue(isShotTime);
    }
}
