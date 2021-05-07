package org.gamesimulation.interfaces;

import org.gamesimulation.GamePosition;

import java.util.List;

public interface IFormation {
    GamePosition.position getFormationType();

    List<IPlayerInGame> getPlayers();

    List<IPlayerInGame> addPlayer(IPlayerInGame player);

    double getStats();

    IPlayerInGame getRandomPlayer();
}
