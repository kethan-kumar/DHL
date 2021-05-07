/* @Author: Yash Jaiswal */

package org.trading;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.io.DisplayTrade;
import org.io.interfaces.IDisplayTrade;
import org.leaguemodel.interfaces.IPlayers;
import org.trading.interfaces.ITradeDecision;

import java.util.List;
import java.util.Map;

public class TradeDecision implements ITradeDecision {

    private final Logger logger;

    public TradeDecision() {
        logger = Logger.getLogger(TradeDecision.class.getName());
    }

    public Boolean tradeAIDecision(Double randomAcceptanceChance, String personality, Map<String, Double> gmTable) {
        logger.debug("Attempting trade with another AI team.");
        double randomValue = Math.random();
        double modifiedRandomAcceptance = randomAcceptanceChance + gmTable.get(StringUtils.lowerCase(personality));
        return randomValue < modifiedRandomAcceptance;
    }

    public Boolean tradeUserDecision(List<IPlayers> weakPlayers, List<IPlayers> strongPlayers) {
        IDisplayTrade displayUserTrade = new DisplayTrade();
        logger.debug("Attempting trade with user's team.");
        return displayUserTrade.displayUserTrade(weakPlayers, strongPlayers);
    }
}
