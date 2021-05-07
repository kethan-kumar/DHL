/* @Author: Vikram Singh */

package org.leaguesimulation;

import org.junit.Before;
import org.junit.Test;
import org.leaguesimulation.interfaces.IGame;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlayOffSeriesTest {

    private List<Game> games;
    private TeamOnScoreboard teamOne;
    private TeamOnScoreboard teamTwo;
    private Game game;

    @Before
    public void setUp() throws Exception {
        teamOne = new TeamOnScoreboard();
        teamTwo = new TeamOnScoreboard();
        game = new Game(teamOne, teamTwo);
        games = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            games.add(game);
        }
    }

    @Test
    public void generatePlayOffSeries() {
        PlayOffSeries playOffSeries = new PlayOffSeries(game, 7);
        List<IGame> games = playOffSeries.generatePlayOffSeries(7);
        assertEquals(7, games.size());
    }

    @Test
    public void getPlayOffSeries() {
        PlayOffSeries playOffSeries = new PlayOffSeries(game, 7);
        assertEquals(7, playOffSeries.getPlayOffSeries().size());
    }
}
