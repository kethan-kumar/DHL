/* @Author: Vikram Singh */

package org.leaguesimulation;

import org.leaguesimulation.interfaces.IGame;
import org.leaguesimulation.interfaces.IPlayOffSeries;

import java.util.ArrayList;
import java.util.List;

public class PlayOffSeries implements IPlayOffSeries {

    private final IGame playOffGame;
    private List<IGame> playOffSeries;

    public PlayOffSeries(IGame playOffGame, int bestOf) {
        this.playOffGame = playOffGame;
        playOffSeries = generatePlayOffSeries(bestOf);
    }

    public List<IGame> generatePlayOffSeries(int bestOf) {
        playOffSeries = new ArrayList<>();
        for (int i = 0; i < bestOf; i++) {
            playOffSeries.add(playOffGame);
        }
        return playOffSeries;
    }

    public List<IGame> getPlayOffSeries() {
        return playOffSeries;
    }
}
