/* @Author: Vikram Singh */

package org.gamesimulation;

import org.apache.log4j.Logger;
import org.gamesimulation.abstractfactory.GameAbstractFactory;
import org.gamesimulation.interfaces.IPenalty;
import org.gamesimulation.interfaces.IShot;
import org.gamesimulation.interfaces.ISprint;
import org.leaguemodel.interfaces.IGameplayConfig;

public class Sprint implements ISprint {
    public static final double STRONG_OFFENSE_BIAS = 3;
    private final GameAbstractFactory factory;
    private final IShot shot;
    private final IPenalty penalty;
    private final Logger logger;

    public Sprint(IGameplayConfig gameplayConfig) {
        logger = Logger.getLogger(Sprint.class.getName());
        factory = GameAbstractFactory.instance();
        this.shot = factory.createShot(gameplayConfig);
        this.penalty = factory.createPenalty(gameplayConfig);
    }

    public void execute(TeamInGame teamOne, TeamInGame teamTwo) {
        logger.debug("Sprint started");
        if (isStrongOffense(teamOne, teamTwo)) {
            logger.debug("Strong offense by TeamOne");
            strongOffenseGamePlay(teamOne, teamTwo);
        } else if (isStrongOffense(teamTwo, teamOne)) {
            logger.debug("Strong offense by TeamTwo");
            strongOffenseGamePlay(teamTwo, teamOne);
        } else {
            logger.debug("Equally strong offense by both teams");
            shot.execute(teamOne, teamTwo);
            shot.execute(teamTwo, teamOne);
        }
        logger.debug("Sprint ended");
    }

    private void strongOffenseGamePlay(TeamInGame teamA, TeamInGame teamB) {
        boolean isNotGoal = shot.execute(teamA, teamB);
        if (isNotGoal) {
            logger.debug("Offense team took a shot which missed");
            penalty.execute(teamA, teamB);
        }
        logger.debug("Offense team took another shot");
        shot.execute(teamA, teamB);
    }

    private boolean isStrongOffense(TeamInGame teamA, TeamInGame teamB) {
        return teamA.getForwardOnIce().getStats() - teamB.getDefenseOnIce().getStats() > STRONG_OFFENSE_BIAS;
    }
}
