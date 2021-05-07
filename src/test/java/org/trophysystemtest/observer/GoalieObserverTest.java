package org.trophysystemtest.observer;

import org.junit.After;
import org.junit.Before;
import org.trophysystem.abstractfactory.TrophyAbstractFactory;
import org.trophysystem.interfaces.IPerformanceObserver;

public class GoalieObserverTest {
    IPerformanceObserver performanceObserver;

    @Before
    public void initialize() {
        TrophyAbstractFactory trophyFactory = TrophyAbstractFactory.instance();
        performanceObserver = trophyFactory.createGoalieObserver();
    }

    @After
    public void destroy() {
        performanceObserver = null;
    }
}
