package org.leaguesimulation.interfaces;

import org.gamesimulation.interfaces.IGameSimulation;

public interface ILeagueScoreStats {
    double getAverageGoals();

    double getAveragePenalties();

    double getAverageShots();

    double getAverageSaves();

    void updateStats(IGameSimulation gameSimulation);
}
