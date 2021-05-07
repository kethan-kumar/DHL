package org.tradingtest;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.leaguemodel.interfaces.ITrading;
import org.trading.abstractfactory.TradingAbstractFactory;
import org.trading.interfaces.IDraftPickSlots;
import org.trading.interfaces.ITradingDraftPicks;
import org.trading.interfaces.ITradingTeam;
import resources.abstractfactory.MockAbstractFactory;
import resources.interfaces.IMockDataTrading;

import java.io.ByteArrayInputStream;

public class TradingTeamTest {

    IMockDataTrading mockDataTradingObj;
    ITrading mockTradingObj;
    ITradingDraftPicks draftPicksObj;
    ITradingTeam tradingTeamObj;

    @Before
    public void initialize() {
        MockAbstractFactory mockFactory = MockAbstractFactory.instance();
        TradingAbstractFactory tradingFactory = TradingAbstractFactory.instance();
        mockTradingObj = mockFactory.createMockTrading();
        mockDataTradingObj = mockFactory.createMockDataTrading();
        draftPicksObj = tradingFactory.createTradingDraftPicks();
        tradingTeamObj = tradingFactory.createTradingTeam();
    }

    @Test
    public void testTradingTeams() {
        IDraftPickSlots draftPickSlotsObj = draftPicksObj.draftPickSlotsInitializer(mockDataTradingObj.getLeagueOne());
        tradingTeamObj.tradingTeamPlayers(mockDataTradingObj.getLeagueOne().getConferences().get(0).getDivisions().get(0).getTeams().get(0), mockDataTradingObj.getLeagueOne(), mockTradingObj, draftPickSlotsObj);
        Assert.assertTrue(StringUtils.equalsIgnoreCase("Player oneOne", mockDataTradingObj.getLeagueOne().getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getPlayerName())
                || StringUtils.equalsIgnoreCase("A58", mockDataTradingObj.getLeagueOne().getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getPlayerName())
                || StringUtils.equalsIgnoreCase("A42", mockDataTradingObj.getLeagueOne().getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getPlayerName()));
    }

    @Test
    public void testTradeAttempt() {
        IDraftPickSlots draftPickSlotsObj = draftPicksObj.draftPickSlotsInitializer(mockDataTradingObj.getLeagueOne());
        tradingTeamObj.tradeAttempt(mockDataTradingObj.getLeagueOne().getConferences().get(0).getDivisions().get(0).getTeams().get(0), mockDataTradingObj.getLeagueOne().getConferences().get(1).getDivisions().get(0).getTeams().get(0), mockTradingObj, mockDataTradingObj.getLeagueOne(), draftPickSlotsObj);
        Assert.assertTrue(StringUtils.equalsIgnoreCase("Player oneOne", mockDataTradingObj.getLeagueOne().getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getPlayerName())
                || StringUtils.equalsIgnoreCase("A58", mockDataTradingObj.getLeagueOne().getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getPlayerName())
                || StringUtils.equalsIgnoreCase("A42", mockDataTradingObj.getLeagueOne().getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getPlayerName()));
    }

    public void userInputMock(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @After
    public void destroy() {
        mockDataTradingObj = null;
        mockTradingObj = null;
        draftPicksObj = null;
        tradingTeamObj = null;
    }
}