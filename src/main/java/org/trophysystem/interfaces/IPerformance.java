/* @Author: Kethan Kumar */

package org.trophysystem.interfaces;

import java.util.List;

public interface IPerformance {

    void attachPlayerObserver(IPerformanceObserver observer);

    void detachPlayerObserver(IPerformanceObserver observer);

    void notifyObserver();

    List<IPerformanceObserver> getObservers();
}
