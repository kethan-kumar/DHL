package org.trophysystemtest.observer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.trophysystem.abstractfactory.TrophyAbstractFactory;
import org.trophysystem.interfaces.IAwardWinners;
import org.trophysystem.interfaces.IPerformanceObserver;


public class HeadCoachObserverTest {
    IPerformanceObserver performanceObserver;

    @Before
    public void initialize() {
        TrophyAbstractFactory trophyFactory = TrophyAbstractFactory.instance();
        performanceObserver = trophyFactory.createHeadCoachObserver();
    }

    @Test
    public void testUpdate() {
        performanceObserver.update("Henry", 10);
        performanceObserver.update("Mathew", 9);
        IAwardWinners awardTrophy = (IAwardWinners) performanceObserver.getAwardTrophy();
        Assert.assertEquals("Henry", awardTrophy.getBestCoach());
    }

    @After
    public void destroy() {
        performanceObserver = null;
    }
}
