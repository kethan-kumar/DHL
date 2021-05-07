/* @Author: Yash Jaiswal */

package org.trading.interfaces;

import org.leaguemodel.interfaces.ILeague;
import org.leaguemodel.interfaces.ITeam;
import org.leaguemodel.interfaces.ITrading;

import java.util.Map;

public interface ITradingDraftPicks {

    void tradeDrafts(ITeam teamOfferingSlot, ITeam teamOfferingPlayers, ITrading tradeConfig, Map<String, Integer> draftPickSlots, ILeague leagueOne);

    IDraftPickSlots draftPickSlotsInitializer(ILeague leagueObj);
}
