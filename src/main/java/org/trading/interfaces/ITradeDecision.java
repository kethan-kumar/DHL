/* @Author: Yash Jaiswal */

package org.trading.interfaces;

import org.leaguemodel.interfaces.IPlayers;

import java.util.List;
import java.util.Map;

public interface ITradeDecision {

    Boolean tradeAIDecision(Double randomAcceptanceChance, String personality, Map<String, Double> gmTable);

    Boolean tradeUserDecision(List<IPlayers> weakPlayers, List<IPlayers> strongPlayers);
}
