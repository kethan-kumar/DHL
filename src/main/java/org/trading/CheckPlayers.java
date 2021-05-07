/* @Author: Yash Jaiswal */

package org.trading;

import org.leaguemodel.interfaces.ITeam;
import org.trading.abstractfactory.TradingAbstractFactory;
import org.trading.interfaces.ICheckPlayers;

public class CheckPlayers implements ICheckPlayers {
    private final TradingAbstractFactory tradingFactory;

    public CheckPlayers() {
        tradingFactory = TradingAbstractFactory.instance();
    }

    public void captain(ITeam team) {
        team.getPlayers().sort(tradingFactory.createStatsComparator());
        for (int i = 0; i < team.getPlayers().size(); i++) {
            team.getPlayers().get(i).setIsCaptain(false);
        }
        team.getPlayers().get(0).setIsCaptain(true);
    }

    public int goalies(ITeam team) {
        int goaliesSize = 0;
        for (int i = 0; i < team.getPlayers().size(); i++) {
            if (team.getPlayers().get(i).getPosition().equalsIgnoreCase(enumPosition.GOALIE.toString())) {
                goaliesSize++;
            }
        }
        return goaliesSize;
    }

    public int forwards(ITeam team) {
        int forwardCount = 0;
        for (int i = 0; i < team.getPlayers().size(); i++) {
            if (team.getPlayers().get(i).getPosition().equalsIgnoreCase(enumPosition.FORWARD.toString())) {
                forwardCount++;
            }
        }
        return forwardCount;
    }

    public int defense(ITeam team) {
        int defenseCount = 0;
        for (int i = 0; i < team.getPlayers().size(); i++) {
            if (team.getPlayers().get(i).getPosition().equalsIgnoreCase(enumPosition.DEFENSE.toString())) {
                defenseCount++;
            }
        }
        return defenseCount;
    }

    enum enumPosition {
        GOALIE, FORWARD, DEFENSE
    }
}
