/* @Author: Yash Jaiswal */

package org.trading.abstractfactory;

import org.leaguemodel.interfaces.IPlayers;
import org.trading.*;
import org.trading.interfaces.*;

import java.util.Comparator;

public class TradingFactory extends TradingAbstractFactory {

    public IBalance createBalance() {
        return new Balance();
    }

    public ICheckPlayers createCheckPlayers() {
        return new CheckPlayers();
    }

    public IDraftPickSlots createDraftPickSlots() {
        return new DraftPickSlots();
    }

    public ITradeDecision createTradeDecision() {
        return new TradeDecision();
    }

    public ITradeOffer createTradeOffer() {
        return new TradeOffer();
    }

    public ITradingDraftPicks createTradingDraftPicks() {
        return new TradingDraftPicks();
    }

    public ITradingTeam createTradingTeam() {
        return new TradingTeam();
    }

    public Comparator<IPlayers> createStatsComparator() {
        return new StatsComparator();
    }
}
