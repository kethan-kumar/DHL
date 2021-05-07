package org.gamesimulation.interfaces;

import org.gamesimulation.TeamInGame;

import java.util.List;

public interface IGameSimulation {
    void execute();

    TeamInGame getWinner();

    TeamInGame getLoser();

    TeamInGame getTeamOne();

    TeamInGame getTeamTwo();

    List<IPlayerInGame> getPlayers();
}
