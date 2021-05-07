/* @Author: Vikram Singh */

package org.gamesimulation;

import org.apache.log4j.Logger;
import org.gamesimulation.interfaces.IFormation;
import org.gamesimulation.interfaces.IPlayerInGame;

import java.util.ArrayList;
import java.util.List;

public class Goalie implements IFormation {
    private final List<IPlayerInGame> players;
    private final Logger logger;

    public Goalie() {
        logger = Logger.getLogger(Goalie.class.getName());
        players = new ArrayList<>();
    }

    public GamePosition.position getFormationType() {
        return GamePosition.position.GOALIE;
    }

    public List<IPlayerInGame> getPlayers() {
        return players;
    }

    public List<IPlayerInGame> addPlayer(IPlayerInGame player) {
        players.add(player);
        return players;
    }

    public double getStats() {
        logger.debug("Goalie average strength: " + players.get(0).getPlayer().getSaving());
        return players.get(0).getPlayer().getSaving();
    }

    public IPlayerInGame getRandomPlayer() {
        return players.get(0);
    }
}
