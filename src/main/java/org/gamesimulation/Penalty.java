/* @Author: Vikram Singh */

package org.gamesimulation;

import org.apache.log4j.Logger;
import org.gamesimulation.interfaces.IPenalty;
import org.leaguemodel.interfaces.IGameplayConfig;

public class Penalty implements IPenalty {
    private final double penaltyChance;
    private final double penaltyToGoalChance;
    private final Logger logger;

    public Penalty(IGameplayConfig gameplayConfig) {
        this.logger = Logger.getLogger(Penalty.class.getName());
        this.penaltyChance = gameplayConfig.getGameResolver().getPenaltyChance();
        this.penaltyToGoalChance = gameplayConfig.getGameResolver().getPenaltyToGoalChance();
    }

    public void execute(TeamInGame offense, TeamInGame defense) {
        if (isPossible(penaltyChance)) {
            logger.debug("Defense tackle, penalty awarded to strong offense");
            defense.incrementPenalties();
            executePenalty(offense, defense);
        } else {
            logger.debug("Defense tackle, Goal saved");
            defense.incrementSaves();
            defense.getDefenseOnIce().getRandomPlayer().incrementSaves();
        }
    }

    private void executePenalty(TeamInGame offense, TeamInGame defense) {
        if (isPossible(penaltyToGoalChance)) {
            logger.debug("Penalty converted to Goal");
            offense.incrementGoals();
            offense.getForwardOnIce().getRandomPlayer().incrementGoals();
        } else {
            logger.debug("Penalty saved by the Goalie");
            defense.getGoalieOnIce().getRandomPlayer().incrementSaves();
        }
    }

    private boolean isPossible(double penaltyChance) {
        return Math.random() < penaltyChance;
    }
}
