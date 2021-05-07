/* @Author: Vikram Singh */

package org.gamesimulation;

import org.apache.log4j.Logger;
import org.gamesimulation.interfaces.IGameTime;


public class GameTime implements IGameTime {

    public static final int TOTAL_TIME = 3600;
    public static final int NUMBER_OF_SHOTS = 30;
    public static final int NUMBER_OF_SHIFTS = 40;
    Logger logger;

    public GameTime() {
        logger = Logger.getLogger(GameTime.class.getName());
    }

    public int getTimeJumpInSeconds() {
        int timeJump = greatestCommonDivisor(TOTAL_TIME / NUMBER_OF_SHOTS, TOTAL_TIME / NUMBER_OF_SHIFTS);
        logger.debug("Time Jump in the game: " + timeJump);
        return timeJump;
    }

    public int getTotalTimeInSeconds() {
        logger.debug("Total time in seconds: " + TOTAL_TIME);
        return TOTAL_TIME;
    }

    public boolean isShiftTime(int currentTime) {
        return currentTime % (TOTAL_TIME / NUMBER_OF_SHIFTS) == 0;
    }

    public boolean isShotTime(int currentTime) {
        return currentTime % (TOTAL_TIME / NUMBER_OF_SHOTS) == 0;
    }

    private int greatestCommonDivisor(int a, int b) {
        return b == 0 ? a : greatestCommonDivisor(b, a % b);
    }
}
