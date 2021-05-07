package org.tradingtest;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.leaguemodel.abstractfactory.LeagueModelAbstractFactory;
import org.leaguemodel.interfaces.IGameplayConfig;
import org.leaguemodel.interfaces.ITrading;
import org.leaguesimulation.abstractfactory.LeagueAbstractFactory;
import org.leaguesimulation.interfaces.IScoreboard;
import org.trading.TradeOffer;
import org.trading.abstractfactory.TradingAbstractFactory;
import org.trading.interfaces.IDraftPickSlots;
import org.trading.interfaces.ITradeOffer;
import org.trading.interfaces.ITradingDraftPicks;
import resources.abstractfactory.MockAbstractFactory;
import resources.interfaces.IMockDataTrading;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class TradeOfferTest {

    private final ByteArrayOutputStream systemOutContent = new ByteArrayOutputStream();
    IMockDataTrading mockDataTradingObj;
    ITrading mockTradingObj;
    ITradeOffer tradeOfferObj;
    ITradingDraftPicks draftPicksObj;
    IGameplayConfig gameplayConfig;
    IScoreboard scoreboardObj;
    LeagueAbstractFactory leagueSimulationFactory;
    private PrintStream printStream;

    @Before
    public void initialize() {
        leagueSimulationFactory = LeagueAbstractFactory.instance();
        MockAbstractFactory mockFactory = MockAbstractFactory.instance();
        TradingAbstractFactory tradingFactory = TradingAbstractFactory.instance();
        LeagueModelAbstractFactory leagueFactory = LeagueModelAbstractFactory.instance();
        mockTradingObj = mockFactory.createMockTrading();
        mockDataTradingObj = mockFactory.createMockDataTrading();
        tradeOfferObj = tradingFactory.createTradeOffer();
        draftPicksObj = tradingFactory.createTradingDraftPicks();
        gameplayConfig = leagueFactory.createGameplayConfig();
    }

    @Before
    public void setUpStream() {
        printStream = System.out;
        System.setOut(new PrintStream(systemOutContent));
    }

    @After
    public void resetStream() {
        System.setOut(printStream);
    }

    @Test
    public void testGenerateTradeOffer() {
        mockTradingObj.setLossPoint(5);
        mockTradingObj.setMaxPlayersPerTrade(2);
        mockTradingObj.setRandomAcceptanceChance(10.0);
        mockTradingObj.setRandomTradeOfferChance(10.0);
        Map<String, Double> gmTable1 = new HashMap<>();
        gmTable1.put("shrewd", -0.1);
        gmTable1.put("gambler", 0.1);
        gmTable1.put("normal", 0.0);
        mockTradingObj.setGmTable(gmTable1);
        gameplayConfig.setTrading(mockTradingObj);
        mockDataTradingObj.getLeagueOne().setGameplayConfig(gameplayConfig);
        scoreboardObj = leagueSimulationFactory.createLeagueScoreboard(mockDataTradingObj.getLeagueOne());
        scoreboardObj.loadScoreboard();
        scoreboardObj.getScoreboard().get(0).setLossPointsForTrade(9);
        scoreboardObj.getScoreboard().get(1).setLossPointsForTrade(4);
        scoreboardObj.getScoreboard().get(2).setLossPointsForTrade(4);
        scoreboardObj.loadScoreboard();
        IDraftPickSlots draftPickSlotsObj = draftPicksObj.draftPickSlotsInitializer(mockDataTradingObj.getLeagueOne());
        tradeOfferObj.generateTradeOffer(scoreboardObj, mockDataTradingObj.getLeagueOne(), draftPickSlotsObj);
        Assert.assertTrue(StringUtils.isNotEmpty(systemOutContent.toString()));
    }

    @Test
    public void testRandomTradeOffer() {
        ITradeOffer tradeOffer = new TradeOffer();
        Assert.assertTrue(tradeOffer.randomTradeOffer(2.0));
    }

    @After
    public void destroy() {
        mockDataTradingObj = null;
        mockTradingObj = null;
        tradeOfferObj = null;
        draftPicksObj = null;
        gameplayConfig = null;
        scoreboardObj = null;
        leagueSimulationFactory = null;
    }
}
