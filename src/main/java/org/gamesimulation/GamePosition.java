/* @Author: Vikram Singh */

package org.gamesimulation;

import org.gamesimulation.interfaces.IGamePosition;

public class GamePosition implements IGamePosition {
    public position getPosition(String key) {
        return position.valueOf(key);
    }

    public enum position {FORWARD, DEFENSE, GOALIE}
}
