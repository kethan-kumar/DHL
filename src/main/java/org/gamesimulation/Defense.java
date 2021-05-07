/* @Author: Vikram Singh */

package org.gamesimulation;

import org.apache.log4j.Logger;
import org.gamesimulation.interfaces.IFormation;
import org.gamesimulation.interfaces.IPlayerInGame;

import java.util.ArrayList;
import java.util.List;


public class Defense implements IFormation {
    private final List<IPlayerInGame> players;
    private final Logger logger;

    public Defense() {
        logger = Logger.getLogger(Defense.class.getName());
        players = new ArrayList<>();
    }

    public GamePosition.position getFormationType() {
        return GamePosition.position.DEFENSE;
    }

    public List<IPlayerInGame> getPlayers() {
        return players;
    }

    public List<IPlayerInGame> addPlayer(IPlayerInGame player) {
        players.add(player);
        return players;
    }

    public double getStats() {
        double stats = 0;
        for (IPlayerInGame player : players) {
            stats += player.getPlayer().getChecking() + player.getPlayer().getSaving();
        }
        logger.debug("Defense line average strength: " + stats / players.size());
        return stats / players.size();
    }

    public IPlayerInGame getRandomPlayer() {
        return players.get(randomIntBeforeIndex(players.size()));
    }

    private int randomIntBeforeIndex(int upperLimitExclusive) {
        return (int) (upperLimitExclusive * Math.random());
    }
}
