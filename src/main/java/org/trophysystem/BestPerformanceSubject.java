/* @Author: Kethan Kumar */

package org.trophysystem;

import org.apache.log4j.Logger;
import org.trophysystem.interfaces.IPerformance;
import org.trophysystem.interfaces.IPerformanceObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class BestPerformanceSubject implements IPerformance {

    private final List<IPerformanceObserver> observers;
    private final Logger logger;
    protected int score = 0;
    protected String performerName;

    public BestPerformanceSubject() {
        this.observers = new ArrayList<>();
        logger = Logger.getLogger(BestPerformanceSubject.class.getName());
        logger.debug("Best performance subject attached");
    }

    public void attachPlayerObserver(IPerformanceObserver observer) {
        this.observers.add(observer);
    }

    public void detachPlayerObserver(IPerformanceObserver observer) {
        this.observers.remove(observer);
    }

    public void notifyObserver() {
        for (IPerformanceObserver observer : observers) {
            observer.update(performerName, score);
        }
    }

    public List<IPerformanceObserver> getObservers() {
        return observers;
    }
}
