package org.tradingtest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.trading.abstractfactory.TradingAbstractFactory;
import org.trading.interfaces.IBalance;
import resources.abstractfactory.MockAbstractFactory;
import resources.interfaces.IMockDataTrading;

public class BalanceTest {

    IMockDataTrading mockDataTradingObj;
    IBalance balanceObj;

    @Before
    public void initialize() {
        MockAbstractFactory mockFactory = MockAbstractFactory.instance();
        TradingAbstractFactory tradingFactory = TradingAbstractFactory.instance();
        mockDataTradingObj = mockFactory.createMockDataTrading();
        balanceObj = tradingFactory.createBalance();
    }

    @Test
    public void testBalanceTeams() {
        balanceObj.balanceTeams(mockDataTradingObj.getLeagueOne().getConferences().get(1).getDivisions().get(0).getTeams().get(0), mockDataTradingObj.getLeagueOne());
        Assert.assertEquals(30, mockDataTradingObj.getLeagueOne().getConferences().get(1).getDivisions().get(0).getTeams().get(0).getPlayers().size());
    }

    @Test
    public void testAddFromFreeAgent() {
        balanceObj.addFromFreeAgent(mockDataTradingObj.getLeagueOne().getConferences().get(0).getDivisions().get(0).getTeams().get(0), mockDataTradingObj.getLeagueOne(), "Forward", 1);
        int playerIndex = mockDataTradingObj.getLeagueOne().getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().size();
        Assert.assertEquals("Agent Three", mockDataTradingObj.getLeagueOne().getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(playerIndex - 1).getPlayerName());
    }

    @Test
    public void testRemoveFromTeam() {
        int beforeRemoveTeamSize = mockDataTradingObj.getLeagueOne().getConferences().get(1).getDivisions().get(0).getTeams().get(0).getPlayers().size() - 1;
        balanceObj.removeFromTeam(mockDataTradingObj.getLeagueOne().getConferences().get(1).getDivisions().get(0).getTeams().get(0), mockDataTradingObj.getLeagueOne(), "Forward", 14);
        Assert.assertEquals(beforeRemoveTeamSize - 4, mockDataTradingObj.getLeagueOne().getConferences().get(1).getDivisions().get(0).getTeams().get(0).getPlayers().size());
    }

    @Test
    public void testAddUserTeam() {
        int playerIndex = mockDataTradingObj.getLeagueOne().getConferences().get(2).getDivisions().get(0).getTeams().get(0).getPlayers().size();
        balanceObj.addUserTeam(mockDataTradingObj.getLeagueOne().getConferences().get(2).getDivisions().get(0).getTeams().get(0), mockDataTradingObj.getLeagueOne(), 1);
        Assert.assertEquals("A1", mockDataTradingObj.getLeagueOne().getConferences().get(2).getDivisions().get(0).getTeams().get(0).getPlayers().get(playerIndex).getPlayerName());
    }

    @Test
    public void testRemoveUserTeam() {
        int beforeTeamSize = mockDataTradingObj.getLeagueOne().getConferences().get(2).getDivisions().get(0).getTeams().get(0).getPlayers().size();
        balanceObj.removeUserTeam(mockDataTradingObj.getLeagueOne().getConferences().get(2).getDivisions().get(0).getTeams().get(0), mockDataTradingObj.getLeagueOne(), 1);
        Assert.assertEquals(beforeTeamSize - 1, mockDataTradingObj.getLeagueOne().getConferences().get(2).getDivisions().get(0).getTeams().get(0).getPlayers().size());
    }

    @After
    public void destroy() {
        mockDataTradingObj = null;
        balanceObj = null;
    }

}