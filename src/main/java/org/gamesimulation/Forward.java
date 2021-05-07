/* @Author: Vikram Singh */

package org.gamesimulation;

import org.apache.log4j.Logger;
import org.gamesimulation.interfaces.IFormation;
import org.gamesimulation.interfaces.IPlayerInGame;

import java.util.ArrayList;
import java.util.List;

public class Forward implements IFormation {

    private final List<IPlayerInGame> players;
    private final Logger logger;

    public Forward() {
        logger = Logger.getLogger(Forward.class.getName());
        players = new ArrayList<>();
    }

    public GamePosition.position getFormationType() {
        return GamePosition.position.FORWARD;
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
            stats += player.getPlayer().getShooting() + player.getPlayer().getSkating();
        }
        logger.debug("Forward line average strength: " + stats / players.size());
        return stats / players.size();
    }

    public IPlayerInGame getRandomPlayer() {
        return players.get(randomIntBeforeIndex(players.size()));
    }

    private int randomIntBeforeIndex(int upperLimitExclusive) {
        return (int) (upperLimitExclusive * Math.random());
    }

}
