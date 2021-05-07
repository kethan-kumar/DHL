package org.trophysystemtest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.trophysystem.abstractfactory.TrophyAbstractFactory;
import org.trophysystem.interfaces.IAwardWinners;
import org.trophysystem.interfaces.IPerformanceObserver;
import org.trophysystem.interfaces.ITrophyNominees;

import java.util.HashMap;
import java.util.Map;

public class AwardCeremonyTest {
    IPerformanceObserver performanceObserver;
    Map<String, Integer> bestHeadCoaches;
    Map<String, Integer> bestGoalies;
    Map<String, Integer> bestDraftPlayers;
    Map<String, Integer> bestGoalScorers;
    Map<String, Integer> bestDefenseMens;
    ITrophyNominees awardTrophy;

    @Before
    public void initialize() {
        TrophyAbstractFactory trophyFactory = TrophyAbstractFactory.instance();
        performanceObserver = trophyFactory.createGoalScorerObserver();
        bestHeadCoaches = new HashMap<>();
        bestGoalies = new HashMap<>();
        bestDraftPlayers = new HashMap<>();
        bestGoalScorers = new HashMap<>();
        bestDefenseMens = new HashMap<>();
        awardTrophy = performanceObserver.getAwardTrophy();
    }


    @Test
    public void testGoalieNominee() {
        awardTrophy.goalieNominee("Mathew", 20);
        awardTrophy.goalieNominee("Henry", 15);
        IAwardWinners awardTrophy = (IAwardWinners) performanceObserver.getAwardTrophy();
        Assert.assertEquals("Mathew", awardTrophy.getBestGoalie());
    }

    @After
    public void destroy() {
        performanceObserver = null;
    }
}
