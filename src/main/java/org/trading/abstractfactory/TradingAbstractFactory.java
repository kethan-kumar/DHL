/* @Author: Yash Jaiswal */

package org.trading.abstractfactory;

import org.leaguemodel.interfaces.IPlayers;
import org.trading.interfaces.*;

import java.util.Comparator;

public abstract class TradingAbstractFactory {

    private static TradingAbstractFactory uniqueInstance = null;

    public static TradingAbstractFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new TradingFactory();
        }
        return uniqueInstance;
    }

    public static void setFactory(TradingFactory tradingFactory) {
        uniqueInstance = tradingFactory;
    }

    public abstract IBalance createBalance();

    public abstract ICheckPlayers createCheckPlayers();

    public abstract IDraftPickSlots createDraftPickSlots();

    public abstract ITradeDecision createTradeDecision();

    public abstract ITradeOffer createTradeOffer();

    public abstract ITradingDraftPicks createTradingDraftPicks();

    public abstract ITradingTeam createTradingTeam();

    public abstract Comparator<IPlayers> createStatsComparator();
}
