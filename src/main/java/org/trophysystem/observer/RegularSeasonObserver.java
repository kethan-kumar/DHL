/* @Author: Kethan Kumar */

package org.trophysystem.observer;

import org.leaguesimulation.interfaces.IScoreboard;
import org.trophysystem.abstractfactory.TrophyAbstractFactory;
import org.trophysystem.interfaces.ITeamObserver;
import org.trophysystem.interfaces.ITrophyNominees;

public class RegularSeasonObserver implements ITeamObserver {
    protected IScoreboard leagueScoreboard;
    protected TrophyAbstractFactory trophyFactory;
    ITrophyNominees awardTrophy;

    public RegularSeasonObserver() {
        trophyFactory = TrophyAbstractFactory.instance();
        awardTrophy = trophyFactory.createAwardCeremony();
    }

    public void update(IScoreboard leagueScoreboard) {
        this.leagueScoreboard = leagueScoreboard;
        awardTrophy.teamNominees(leagueScoreboard);
    }

    public ITrophyNominees getAwardTrophy() {
        return awardTrophy;
    }
}
