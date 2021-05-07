/* @Author: Kethan Kumar */

package org.trophysystem.observer;

import org.trophysystem.abstractfactory.TrophyAbstractFactory;
import org.trophysystem.interfaces.IPerformanceObserver;
import org.trophysystem.interfaces.ITrophyNominees;

public class GoalScorerObserver implements IPerformanceObserver {
    protected TrophyAbstractFactory trophyFactory;
    ITrophyNominees awardTrophy;

    public GoalScorerObserver() {
        trophyFactory = TrophyAbstractFactory.instance();
        awardTrophy = trophyFactory.createAwardCeremony();
    }

    public void update(String goalScorerName, int goalScorerPoints) {
        awardTrophy.goalScorerNominees(goalScorerName, goalScorerPoints);
    }

    @Override
    public ITrophyNominees getAwardTrophy() {
        return awardTrophy;
    }
}
