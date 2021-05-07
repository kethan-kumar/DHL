/* @Author:  Siddhant Ashutosh */
package org.leaguemodel.interfaces;

import java.util.Map;

public interface ITrading {
    Integer getLossPoint();

    void setLossPoint(Integer lossPoint);

    Double getRandomTradeOfferChance();

    void setRandomTradeOfferChance(Double randomTradeOfferChance);

    Integer getMaxPlayersPerTrade();

    void setMaxPlayersPerTrade(Integer maxPlayersPerTrade);

    Double getRandomAcceptanceChance();

    void setRandomAcceptanceChance(Double randomAcceptanceChance);

    Map<String, Double> getGmTable();

    void setGmTable(Map<String, Double> gmTable);
}
