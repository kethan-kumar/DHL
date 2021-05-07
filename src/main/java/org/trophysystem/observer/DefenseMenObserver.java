/* @Author: Kethan Kumar */

package org.trophysystem.observer;

import org.trophysystem.abstractfactory.TrophyAbstractFactory;
import org.trophysystem.interfaces.IPerformanceObserver;
import org.trophysystem.interfaces.ITrophyNominees;

public class DefenseMenObserver implements IPerformanceObserver {
    protected TrophyAbstractFactory trophyFactory;
    ITrophyNominees awardTrophy;

    public DefenseMenObserver() {
        trophyFactory = TrophyAbstractFactory.instance();
        awardTrophy = trophyFactory.createAwardCeremony();
    }

    public void update(String defenseMenName, int defenseMenPoints) {
        awardTrophy.defenseMenNominees(defenseMenName, defenseMenPoints);
    }

    public ITrophyNominees getAwardTrophy() {
        return awardTrophy;
    }
}
