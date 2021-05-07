/* @Author:  Siddhant Ashutosh */
package org.leaguemodel;

import org.leaguemodel.interfaces.ITrading;

import java.util.Map;

public class Trading implements ITrading {

    private Integer lossPoint;
    private Double randomTradeOfferChance;
    private Integer maxPlayersPerTrade;
    private Double randomAcceptanceChance;
    private Map<String, Double> gmTable;


    public Integer getLossPoint() {
        return lossPoint;
    }


    public void setLossPoint(Integer lossPoint) {
        this.lossPoint = lossPoint;
    }


    public Double getRandomTradeOfferChance() {
        return randomTradeOfferChance;
    }


    public void setRandomTradeOfferChance(Double randomTradeOfferChance) {
        this.randomTradeOfferChance = randomTradeOfferChance;
    }


    public Integer getMaxPlayersPerTrade() {
        return maxPlayersPerTrade;
    }


    public void setMaxPlayersPerTrade(Integer maxPlayersPerTrade) {
        this.maxPlayersPerTrade = maxPlayersPerTrade;
    }


    public Double getRandomAcceptanceChance() {
        return randomAcceptanceChance;
    }


    public void setRandomAcceptanceChance(Double randomAcceptanceChance) {
        this.randomAcceptanceChance = randomAcceptanceChance;
    }


    public Map<String, Double> getGmTable() {
        return gmTable;
    }


    public void setGmTable(Map<String, Double> gmTable) {
        this.gmTable = gmTable;
    }

}
