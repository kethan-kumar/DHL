package org.trophysystemtest.observer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.leaguesimulation.abstractfactory.LeagueAbstractFactory;
import org.leaguesimulation.interfaces.IScoreboard;
import org.trophysystem.abstractfactory.TrophyAbstractFactory;
import org.trophysystem.interfaces.IAwardWinners;
import org.trophysystem.interfaces.ITeamObserver;
import resources.abstractfactory.MockAbstractFactory;
import resources.interfaces.IMockDataTrading;

public class RegularSeasonObserverTest {
    ITeamObserver teamObserver;
    IMockDataTrading mockDataTradingObj;
    IScoreboard scoreboardObj;
    LeagueAbstractFactory leagueSimulationFactory;

    @Before
    public void initialize() {
        leagueSimulationFactory = LeagueAbstractFactory.instance();
        TrophyAbstractFactory trophyFactory = TrophyAbstractFactory.instance();
        teamObserver = trophyFactory.createRegularSeasonObserver();
        MockAbstractFactory mockFactory = MockAbstractFactory.instance();
        mockDataTradingObj = mockFactory.createMockDataTrading();
    }

    @Test
    public void testRegularSeasonUpdateHighestPoints() {
        scoreboardObj = leagueSimulationFactory.createLeagueScoreboard(mockDataTradingObj.getLeagueOne());
        scoreboardObj.loadScoreboard();
        scoreboardObj.getScoreboard().get(0).setScore(80);
        scoreboardObj.getScoreboard().get(1).setScore(40);
        scoreboardObj.loadScoreboard();
        teamObserver.update(scoreboardObj);
        IAwardWinners awardTrophy = (IAwardWinners) teamObserver.getAwardTrophy();
        Assert.assertEquals("Third Team", awardTrophy.getHighestPointsTeam());
    }

    @Test
    public void testRegularSeasonUpdateLowestPoints() {
        scoreboardObj = leagueSimulationFactory.createLeagueScoreboard(mockDataTradingObj.getLeagueOne());
        scoreboardObj.loadScoreboard();
        scoreboardObj.getScoreboard().get(0).setScore(80);
        scoreboardObj.getScoreboard().get(1).setScore(40);
        scoreboardObj.loadScoreboard();
        teamObserver.update(scoreboardObj);
        IAwardWinners awardTrophy = (IAwardWinners) teamObserver.getAwardTrophy();
        Assert.assertEquals("Rob's Team", awardTrophy.getLowestPointsTeam());
    }

    @After
    public void destroy() {
        teamObserver = null;
    }

}