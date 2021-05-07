/* @Author: Vikram Singh */

package org.gamesimulation;

import org.apache.log4j.Logger;
import org.gamesimulation.abstractfactory.GameAbstractFactory;
import org.gamesimulation.interfaces.IGameSimulation;
import org.gamesimulation.interfaces.IGameTime;
import org.gamesimulation.interfaces.IPlayerInGame;
import org.gamesimulation.interfaces.ISprint;
import org.leaguemodel.interfaces.IGameplayConfig;
import org.leaguemodel.interfaces.ITeam;

import java.util.ArrayList;
import java.util.List;

public class GameSimulation implements IGameSimulation {
    private final GameAbstractFactory factory;
    private final TeamInGame teamOne;
    private final TeamInGame teamTwo;
    private final IGameplayConfig gameplayConfig;
    private final Logger logger;


    public GameSimulation(ITeam teamOne, ITeam teamTwo, IGameplayConfig gameplayConfig) {
        factory = GameAbstractFactory.instance();
        logger = Logger.getLogger(GameSimulation.class.getName());
        this.teamOne = factory.createShift(teamOne);
        this.teamTwo = factory.createShift(teamTwo);
        this.gameplayConfig = gameplayConfig;
    }

    public TeamInGame getTeamOne() {
        return teamOne;
    }

    public TeamInGame getTeamTwo() {
        return teamTwo;
    }

    public TeamInGame getWinner() {
        return teamOne.getGoals() > teamTwo.getGoals() ? teamOne : teamTwo;
    }

    public TeamInGame getLoser() {
        return getWinner() == teamOne ? teamTwo : teamOne;
    }

    public void execute() {
        int i = 0;
        IGameTime gameTimer = factory.createGameTime();
        buildLines(teamOne, teamTwo);
        ISprint sprint = factory.createSprint(gameplayConfig);
        while (i < gameTimer.getTotalTimeInSeconds()) {
            if (gameTimer.isShiftTime(i)) {
                teamOne.shuffle();
                teamTwo.shuffle();
            }
            if (gameTimer.isShotTime(i)) {
                sprint.execute(teamOne, teamTwo);
            }
            i += gameTimer.getTimeJumpInSeconds();
        }
        logger.info(teamOne.getTeam().getTeamName() + "-> Goals: " + teamOne.getGoals() + " Saves: " + teamOne.getSaves() + " Shots: " + teamOne.getShots() + " Penalties: " + teamOne.getPenalties());
        logger.info(teamTwo.getTeam().getTeamName() + "-> Goals: " + teamTwo.getGoals() + " Saves: " + teamTwo.getSaves() + " Shots: " + teamTwo.getShots() + " Penalties: " + teamTwo.getPenalties());
    }

    private void buildLines(TeamInGame teamOne, TeamInGame teamTwo) {
        teamOne.buildLines();
        teamTwo.buildLines();
        teamOne.setInitialTeamOnIce();
        teamTwo.setInitialTeamOnIce();
    }

    public List<IPlayerInGame> getPlayers() {
        List<IPlayerInGame> collection = new ArrayList<>();
        collection.addAll(teamOne.getPlayers());
        collection.addAll(teamTwo.getPlayers());
        return collection;
    }
}
