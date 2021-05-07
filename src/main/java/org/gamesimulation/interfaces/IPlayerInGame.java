package org.gamesimulation.interfaces;

import org.leaguemodel.interfaces.IPlayers;

public interface IPlayerInGame {
    IPlayers getPlayer();

    int getGoals();

    void incrementGoals();

    int getShots();

    void incrementShots();

    int getPenalties();

    void incrementPenalties();

    int getSaves();

    void incrementSaves();
}
