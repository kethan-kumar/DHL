package resources;

import org.leaguemodel.interfaces.ITrading;

import java.util.HashMap;
import java.util.Map;

public class MockTrading implements ITrading {

    private Integer lossPoint;
    private Double randomTradeOfferChance;
    private Integer maxPlayersPerTrade;
    private Double randomAcceptanceChance;
    private Map<String, Double> gmTable;

    public MockTrading() {
        setLossPoint(8);
        setMaxPlayersPerTrade(8);
        setRandomAcceptanceChance(2.0);
        setRandomTradeOfferChance(0.8);
        Map<String, Double> gmTable1 = new HashMap<>();
        gmTable1.put("shrewd", -0.1);
        gmTable1.put("gambler", 0.1);
        gmTable1.put("normal", 0.0);
        setGmTable(gmTable1);
    }

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
