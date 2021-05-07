/* @Author: Yash Jaiswal */

package org.trading.interfaces;

import org.leaguemodel.interfaces.ILeague;
import org.leaguemodel.interfaces.ITeam;
import org.leaguemodel.interfaces.ITrading;

public interface ITradingTeam {

    void tradingTeamPlayers(ITeam askingTeam, ILeague leagueOne, ITrading tradeConfig, IDraftPickSlots draftPickSlots);

    void tradeAttempt(ITeam askingTeam, ITeam oppositeTeam, ITrading tradeConfig, ILeague leagueOne, IDraftPickSlots draftPickSlots);

}
