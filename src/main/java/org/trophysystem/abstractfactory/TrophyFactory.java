/* @Author: Kethan Kumar */

package org.trophysystem.abstractfactory;

import org.trophysystem.AwardCeremony;
import org.trophysystem.interfaces.IAwardWinners;
import org.trophysystem.interfaces.IPerformanceObserver;
import org.trophysystem.interfaces.ITeamObserver;
import org.trophysystem.interfaces.ITrophyNominees;
import org.trophysystem.observer.DefenseMenObserver;

public class TrophyFactory extends TrophyAbstractFactory {
    public IPerformanceObserver createDefenseMenObserver() {
        return new DefenseMenObserver();
    }

    public IPerformanceObserver createDraftPlayerObserver() {
        return new org.trophysystem.observer.DraftedPlayerObserver();
    }

    public IPerformanceObserver createGoalieObserver() {
        return new org.trophysystem.observer.GoalieObserver();
    }

    public IPerformanceObserver createGoalScorerObserver() {
        return new org.trophysystem.observer.GoalScorerObserver();
    }

    public IPerformanceObserver createHeadCoachObserver() {
        return new org.trophysystem.observer.HeadCoachObserver();
    }

    public ITeamObserver createRegularSeasonObserver() {
        return new org.trophysystem.observer.RegularSeasonObserver();
    }

    public ITrophyNominees createAwardCeremony() {
        return AwardCeremony.instance();
    }

    public IAwardWinners createAwardWinners() {
        return AwardCeremony.instance();
    }
}
