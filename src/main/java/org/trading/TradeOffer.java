/* @Author: Yash Jaiswal */

package org.trading;

import org.apache.log4j.Logger;
import org.leaguemodel.abstractfactory.LeagueModelAbstractFactory;
import org.leaguemodel.interfaces.ILeague;
import org.leaguemodel.interfaces.IRosterStatus;
import org.leaguemodel.interfaces.ITrading;
import org.leaguesimulation.interfaces.IScoreboard;
import org.trading.abstractfactory.TradingAbstractFactory;
import org.trading.interfaces.IDraftPickSlots;
import org.trading.interfaces.ITradeOffer;
import org.trading.interfaces.ITradingTeam;

public class TradeOffer implements ITradeOffer {
    private final TradingAbstractFactory tradingFactory;
    private final Logger logger;
    private LeagueModelAbstractFactory leagueModelAbstractFactory;

    public TradeOffer() {
        leagueModelAbstractFactory = LeagueModelAbstractFactory.instance();
        logger = Logger.getLogger(TradeOffer.class.getName());
        tradingFactory = TradingAbstractFactory.instance();
    }

    public void generateTradeOffer(IScoreboard scoreboardObj, ILeague leagueObj, IDraftPickSlots draftPickSlots) {
        logger.info("Executing Trades");
        logger.debug("Initiating Trading");
        ITradingTeam trading = tradingFactory.createTradingTeam();
        ITrading tradeConfig = leagueObj.getGameplayConfig().getTrading();
        for (int i = 0; i < scoreboardObj.getScoreboard().size(); i++) {
            if (scoreboardObj.getScoreboard().get(i).getLossPointsForTrade() >= tradeConfig.getLossPoint()) {
                if (randomTradeOffer(tradeConfig.getRandomTradeOfferChance())) {
                    trading.tradingTeamPlayers(scoreboardObj.getScoreboard().get(i).getTeam(), leagueObj, tradeConfig, draftPickSlots);
                    logger.debug("Trade offer generated by Team: " + scoreboardObj.getScoreboard().get(i).getTeam().getTeamName());
                    scoreboardObj.getScoreboard().get(i).setLossPointsForTrade(0);
                    logger.debug("Loss points reset for Team: " + scoreboardObj.getScoreboard().get(i).getTeam().getTeamName());
                }
            }
        }
        IRosterStatus roster = leagueModelAbstractFactory.createRosterStatus(leagueObj);
        roster.setStatusInactive();
        roster.setStatusActive();
    }

    public Boolean randomTradeOffer(Double randomTradeOfferChance) {
        double randomValue = Math.random();
        return randomValue < randomTradeOfferChance;
    }
}