package org.leaguesimulation.interfaces;

import org.gamesimulation.interfaces.IGameSimulation;
import org.leaguemodel.interfaces.IGameplayConfig;

public interface IGame {
    IGameSimulation executeGame(IGameplayConfig gameplayConfig);

    ITeamOnScoreboard getTeamOne();

    ITeamOnScoreboard getTeamTwo();
}
