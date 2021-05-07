/* @Author: Yash Jaiswal */

package org.trading.interfaces;

import org.leaguemodel.interfaces.ILeague;
import org.leaguesimulation.interfaces.IScoreboard;

public interface ITradeOffer {

    void generateTradeOffer(IScoreboard scoreboardObj, ILeague leagueObj, IDraftPickSlots draftPickSlots);

    Boolean randomTradeOffer(Double randomTradeOfferChance);
}
