/* @Author: Vikram Singh */

package org.gamesimulation.abstractfactory;

import org.gamesimulation.*;
import org.gamesimulation.interfaces.*;
import org.leaguemodel.interfaces.IGameplayConfig;
import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.ITeam;

public class GameSimulationFactory extends GameAbstractFactory {

    public IFormation createFormation(GamePosition.position type) {
        if (type.equals(GamePosition.position.FORWARD)) {
            return new Forward();
        } else if (type.equals(GamePosition.position.DEFENSE)) {
            return new Defense();
        } else if (type.equals(GamePosition.position.GOALIE)) {
            return new Goalie();
        }
        return null;
    }

    public IPlayerInGame createPlayerInGame(IPlayers player) {
        return new PlayerInGame(player);
    }

    public TeamInGame createShift(ITeam team) {
        return new TeamOnIce(team);
    }

    public IShot createShot(IGameplayConfig gameplayConfig) {
        return new Shot(gameplayConfig);
    }

    public IGameTime createGameTime() {
        return new GameTime();
    }

    public ISprint createSprint(IGameplayConfig gameplayConfig) {
        return new Sprint(gameplayConfig);
    }

    public IPenalty createPenalty(IGameplayConfig gameplayConfig) {
        return new Penalty(gameplayConfig);
    }
}
