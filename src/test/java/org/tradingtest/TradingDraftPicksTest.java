package org.tradingtest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.leaguemodel.abstractfactory.LeagueModelAbstractFactory;
import org.leaguemodel.interfaces.ITrading;
import org.trading.abstractfactory.TradingAbstractFactory;
import org.trading.interfaces.IDraftPickSlots;
import org.trading.interfaces.ITradingDraftPicks;
import resources.abstractfactory.MockAbstractFactory;
import resources.interfaces.IMockDataTrading;

public class TradingDraftPicksTest {

    IMockDataTrading mockDataTradingObj;
    ITrading mockTradingObj;
    ITradingDraftPicks draftPicksObj;

    @Before
    public void initialize() {
        MockAbstractFactory mockFactory = MockAbstractFactory.instance();
        TradingAbstractFactory tradingFactory = TradingAbstractFactory.instance();
        LeagueModelAbstractFactory leagueFactory = LeagueModelAbstractFactory.instance();
        mockTradingObj = mockFactory.createMockTrading();
        mockDataTradingObj = mockFactory.createMockDataTrading();
        draftPicksObj = tradingFactory.createTradingDraftPicks();
    }

    @Test
    public void draftPickSlotsInitializerTest() {
        IDraftPickSlots draftPickSlotsObj = draftPicksObj.draftPickSlotsInitializer(mockDataTradingObj.getLeagueOne());
        Assert.assertEquals(7, (int) draftPickSlotsObj.getDraftPickSlots().get("Team 12"));
    }

    @Test
    public void tradeDraftsTest() {
        IDraftPickSlots draftPickSlotsObj = draftPicksObj.draftPickSlotsInitializer(mockDataTradingObj.getLeagueOne());
        draftPicksObj.tradeDrafts(mockDataTradingObj.getLeagueOne().getConferences().get(0).getDivisions().get(0).getTeams().get(0), mockDataTradingObj.getLeagueOne().getConferences().get(1).getDivisions().get(0).getTeams().get(0), mockTradingObj, draftPickSlotsObj.getDraftPickSlots(), mockDataTradingObj.getLeagueOne());
        Assert.assertEquals(6, (int) draftPickSlotsObj.getDraftPickSlots().get("Team 12"));
    }

    @After
    public void destroy() {
        mockDataTradingObj = null;
        mockTradingObj = null;
        draftPicksObj = null;
    }
}
