/* @Author: Vikram Singh */

package org.gamesimulation;

import org.gamesimulation.interfaces.IPlayerInGame;
import org.leaguemodel.interfaces.IPlayers;

public class PlayerInGame implements IPlayerInGame {
    private final IPlayers player;
    private int goals = 0;
    private int shots = 0;
    private int penalties = 0;
    private int saves = 0;

    public PlayerInGame(IPlayers player) {
        this.player = player;
    }

    public IPlayers getPlayer() {
        return player;
    }

    public int getGoals() {
        return goals;
    }


    public void incrementGoals() {
        this.goals += 1;
    }


    public int getShots() {
        return shots;
    }


    public void incrementShots() {
        this.shots += 1;
    }


    public int getPenalties() {
        return penalties;
    }


    public void incrementPenalties() {
        this.penalties += 1;
    }


    public int getSaves() {
        return saves;
    }


    public void incrementSaves() {
        this.saves += 1;
    }

}
