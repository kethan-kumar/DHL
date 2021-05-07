/* @Author: Vikram Singh */

package org.gamesimulation;

import org.apache.log4j.Logger;
import org.gamesimulation.interfaces.IShot;
import org.leaguemodel.interfaces.IGameplayConfig;

public class Shot implements IShot {
    private final double shotToGoalChance;
    private final Logger logger;

    public Shot(IGameplayConfig gameplayConfig) {
        this.logger = Logger.getLogger(Shot.class.getName());
        this.shotToGoalChance = gameplayConfig.getGameResolver().getShotToGoalChance();

    }

    public boolean execute(TeamInGame offense, TeamInGame defense) {
        offense.incrementShots();
        if (isShotConvertedToGoal()) {
            logger.debug("Shot taken by offense converted to goal");
            offense.incrementGoals();
            offense.getForwardOnIce().getRandomPlayer().incrementGoals();
            return false;
        } else {
            logger.debug("Shot saved by defense");
            defense.incrementSaves();
            defense.getDefenseOnIce().getRandomPlayer().incrementSaves();
        }
        return true;
    }

    public boolean isShotConvertedToGoal() {
        return Math.random() < shotToGoalChance;
    }

}
