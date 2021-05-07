/* @Author: Vikram Singh */

package org.gamesimulation.abstractfactory;

import org.gamesimulation.GamePosition;
import org.gamesimulation.TeamInGame;
import org.gamesimulation.interfaces.*;
import org.leaguemodel.interfaces.IGameplayConfig;
import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.ITeam;

public abstract class GameAbstractFactory {

    private static GameAbstractFactory uniqueInstance = null;

    protected GameAbstractFactory() {

    }

    public static GameAbstractFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new GameSimulationFactory();
        }
        return uniqueInstance;
    }

    public abstract IFormation createFormation(GamePosition.position type);

    public abstract IPlayerInGame createPlayerInGame(IPlayers player);

    public abstract TeamInGame createShift(ITeam team);

    public abstract IShot createShot(IGameplayConfig gameplayConfig);

    public abstract IGameTime createGameTime();

    public abstract ISprint createSprint(IGameplayConfig gameplayConfig);

    public abstract IPenalty createPenalty(IGameplayConfig gameplayConfig);

}
