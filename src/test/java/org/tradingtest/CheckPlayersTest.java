package org.tradingtest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.trading.abstractfactory.TradingAbstractFactory;
import org.trading.interfaces.ICheckPlayers;
import resources.abstractfactory.MockAbstractFactory;
import resources.interfaces.IMockDataTrading;

public class CheckPlayersTest {
    ICheckPlayers checkPlayersObj;
    IMockDataTrading mockDataTradingObj;

    @Before
    public void initialize() {
        MockAbstractFactory mockFactory = MockAbstractFactory.instance();
        TradingAbstractFactory tradingFactory = TradingAbstractFactory.instance();
        mockDataTradingObj = mockFactory.createMockDataTrading();
        checkPlayersObj = tradingFactory.createCheckPlayers();
    }

    @Test
    public void testCaptain() {
        checkPlayersObj.captain(mockDataTradingObj.getLeagueOne().getConferences().get(0).getDivisions().get(0).getTeams().get(0));
        Assert.assertTrue(mockDataTradingObj.getLeagueOne().getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).isCaptain());
    }

    @Test
    public void testGoalies() {
        Assert.assertEquals(2, checkPlayersObj.goalies(mockDataTradingObj.getLeagueOne().getConferences().get(0).getDivisions().get(0).getTeams().get(0)));
    }

    @Test
    public void testForwards() {
        Assert.assertEquals(18, checkPlayersObj.forwards(mockDataTradingObj.getLeagueOne().getConferences().get(1).getDivisions().get(0).getTeams().get(0)));
    }

    @Test
    public void testDefense() {
        Assert.assertEquals(16, checkPlayersObj.defense(mockDataTradingObj.getLeagueOne().getConferences().get(1).getDivisions().get(0).getTeams().get(0)));
    }

    @After
    public void destroy() {
        mockDataTradingObj = null;
        checkPlayersObj = null;
    }

}