package org.tradingtest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.leaguemodel.Players;
import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.ITrading;
import org.trading.abstractfactory.TradingAbstractFactory;
import org.trading.interfaces.ITradeDecision;
import resources.abstractfactory.MockAbstractFactory;
import resources.interfaces.IMockDataTrading;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class TradeDecisionTest {

    IMockDataTrading mockDataTradingObj;
    ITrading mockTradingObj;
    ITradeDecision tradeDecisionObj;

    @Before
    public void initialize() {
        MockAbstractFactory mockFactory = MockAbstractFactory.instance();
        TradingAbstractFactory tradingFactory = TradingAbstractFactory.instance();
        mockTradingObj = mockFactory.createMockTrading();
        mockDataTradingObj = mockFactory.createMockDataTrading();
        tradeDecisionObj = tradingFactory.createTradeDecision();
    }

    @Test
    public void testTradeAIDecision() {
        Assert.assertTrue(tradeDecisionObj.tradeAIDecision(2.0, mockDataTradingObj.getLeagueOne().getGeneralManagers().get(0).getPersonality(), mockTradingObj.getGmTable()));
    }

    @Test
    public void testTradeUserDecision() {
        List<IPlayers> weakPlayers = new ArrayList<>();
        IPlayers playerOne = new Players("Forward", "Player Onethird", true, 33, 11, 14, 11, 0);
        IPlayers playerTwo = new Players("Defense", "Player Twothird", false, 20, 10, 10, 10, 0);
        weakPlayers.add(playerOne);
        weakPlayers.add(playerTwo);
        List<IPlayers> strongPlayers = new ArrayList<>();
        IPlayers playerOneTwo = new Players("Forward", "Player oneOne", true, 33, 120, 20, 20, 0);
        IPlayers playerTwoTwo = new Players("Defense", "Player twoTwo", false, 20, 110, 10, 10, 0);
        strongPlayers.add(playerOneTwo);
        strongPlayers.add(playerTwoTwo);
        userInputMock("1\n");
        Assert.assertTrue(tradeDecisionObj.tradeUserDecision(weakPlayers, strongPlayers));
    }

    public void userInputMock(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @After
    public void destroy() {
        mockDataTradingObj = null;
        tradeDecisionObj = null;
        mockTradingObj = null;
    }

}
