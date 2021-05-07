package org.tradingtest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.trading.abstractfactory.TradingAbstractFactory;
import org.trading.interfaces.IDraftPickSlots;

import java.util.HashMap;
import java.util.Map;

public class DraftPickSlotsTest {

    IDraftPickSlots draftPickSlotsObj;

    @Before
    public void initialize() {
        TradingAbstractFactory tradingFactory = TradingAbstractFactory.instance();
        draftPickSlotsObj = tradingFactory.createDraftPickSlots();
    }

    @Test
    public void getDraftPickSlotsTest() {
        Map<String, Integer> draftPickSlotsTemp = new HashMap<>();
        draftPickSlotsTemp.put("Team A", 7);
        draftPickSlotsTemp.put("Team B", 7);
        draftPickSlotsObj.setDraftPickSlots(draftPickSlotsTemp);
        Assert.assertEquals(draftPickSlotsTemp, draftPickSlotsObj.getDraftPickSlots());
    }

    @Test
    public void setDraftPickSlotsTest() {
        Map<String, Integer> draftPickSlotsTemp = new HashMap<>();
        draftPickSlotsTemp.put("Team A", 7);
        draftPickSlotsTemp.put("Team B", 7);
        draftPickSlotsObj.setDraftPickSlots(draftPickSlotsTemp);
        Assert.assertEquals(draftPickSlotsTemp, draftPickSlotsObj.getDraftPickSlots());
    }

    @After
    public void destroy() {
        draftPickSlotsObj = null;
    }
}
