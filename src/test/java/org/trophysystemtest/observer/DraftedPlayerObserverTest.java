package org.trophysystemtest.observer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.trophysystem.abstractfactory.TrophyAbstractFactory;
import org.trophysystem.interfaces.IAwardWinners;
import org.trophysystem.interfaces.IPerformanceObserver;

public class DraftedPlayerObserverTest {
    IPerformanceObserver performanceObserver;

    @Before
    public void initialize() {
        TrophyAbstractFactory trophyFactory = TrophyAbstractFactory.instance();
        performanceObserver = trophyFactory.createDraftPlayerObserver();
    }

    @Test
    public void testUpdate() {
        performanceObserver.update("Henry", 20);
        performanceObserver.update("Mathew", 21);
        IAwardWinners awardTrophy = (IAwardWinners) performanceObserver.getAwardTrophy();
        Assert.assertEquals("Mathew", awardTrophy.getBestDraftedPlayer());
    }

    @After
    public void destroy() {
        performanceObserver = null;
    }

}
