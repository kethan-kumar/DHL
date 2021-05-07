/* @Author: Kethan Kumar */

package org.trophysystem;

import org.apache.log4j.Logger;
import org.leaguesimulation.interfaces.IScoreboard;
import org.trophysystem.interfaces.ITeamObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class RegularSeasonSubject {
    private final List<ITeamObserver> observers;
    protected IScoreboard leagueScoreboard;
    Logger logger;

    public RegularSeasonSubject() {
        observers = new ArrayList<>();
        logger = Logger.getLogger(RegularSeasonSubject.class.getName());
        logger.debug("Regular season subject attached");
    }

    public void attachPlayerObserver(ITeamObserver observer) {
        this.observers.add(observer);
    }

    public void detachPlayerObserver(ITeamObserver observer) {
        this.observers.remove(observer);
    }

    public void notifyObserver() {
        for (ITeamObserver observer : observers) {
            observer.update(leagueScoreboard);
        }
    }
}
