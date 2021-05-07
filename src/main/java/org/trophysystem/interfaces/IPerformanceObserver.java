/* @Author: Kethan Kumar */

package org.trophysystem.interfaces;

public interface IPerformanceObserver {
    void update(String performerName, int score);

    ITrophyNominees getAwardTrophy();
}
