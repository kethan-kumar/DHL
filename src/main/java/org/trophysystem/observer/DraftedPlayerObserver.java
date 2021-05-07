/* @Author: Kethan Kumar */

package org.trophysystem.observer;

import org.trophysystem.abstractfactory.TrophyAbstractFactory;
import org.trophysystem.interfaces.IPerformanceObserver;
import org.trophysystem.interfaces.ITrophyNominees;

public class DraftedPlayerObserver implements IPerformanceObserver {
    protected TrophyAbstractFactory trophyFactory;
    ITrophyNominees awardTrophy;

    public DraftedPlayerObserver() {
        trophyFactory = TrophyAbstractFactory.instance();
        awardTrophy = trophyFactory.createAwardCeremony();
    }

    public void update(String draftPlayerName, int draftPlayerPoints) {
        awardTrophy.draftNominee(draftPlayerName, draftPlayerPoints);
    }

    public ITrophyNominees getAwardTrophy() {
        return awardTrophy;
    }
}
