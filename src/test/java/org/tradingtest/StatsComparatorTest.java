package org.tradingtest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.trading.abstractfactory.TradingAbstractFactory;
import resources.abstractfactory.MockAbstractFactory;
import resources.interfaces.IMockDataTrading;

public class StatsComparatorTest {

    IMockDataTrading mockDataTradingObj;
    TradingAbstractFactory tradingFactory;

    @Before
    public void initialize() {
        MockAbstractFactory mockFactory = MockAbstractFactory.instance();
        mockDataTradingObj = mockFactory.createMockDataTrading();
        tradingFactory = TradingAbstractFactory.instance();

    }

    @Test
    public void testCompare() {
        Assert.assertEquals("Player one", mockDataTradingObj.getLeagueOne().getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getPlayerName());
        mockDataTradingObj.getLeagueOne().getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().sort(tradingFactory.createStatsComparator());
        Assert.assertEquals("Player seventeen", mockDataTradingObj.getLeagueOne().getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getPlayerName());
    }

    @After
    public void destroy() {
        mockDataTradingObj = null;
        tradingFactory = null;
    }
}