/* @Author: Kethan Kumar */

package org.trophysystem.abstractfactory;

import org.trophysystem.interfaces.IAwardWinners;
import org.trophysystem.interfaces.IPerformanceObserver;
import org.trophysystem.interfaces.ITeamObserver;
import org.trophysystem.interfaces.ITrophyNominees;

public abstract class TrophyAbstractFactory {
    private static TrophyAbstractFactory uniqueInstance = null;

    public static TrophyAbstractFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new TrophyFactory();
        }
        return uniqueInstance;
    }

    public static void setFactory(TrophyFactory trophyFactory) {
        uniqueInstance = trophyFactory;
    }

    public abstract IPerformanceObserver createDefenseMenObserver();

    public abstract IPerformanceObserver createDraftPlayerObserver();

    public abstract IPerformanceObserver createGoalieObserver();

    public abstract IPerformanceObserver createGoalScorerObserver();

    public abstract IPerformanceObserver createHeadCoachObserver();

    public abstract ITeamObserver createRegularSeasonObserver();

    public abstract ITrophyNominees createAwardCeremony();

    public abstract IAwardWinners createAwardWinners();

}
