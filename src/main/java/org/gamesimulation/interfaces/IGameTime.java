package org.gamesimulation.interfaces;

public interface IGameTime {
    int getTimeJumpInSeconds();

    int getTotalTimeInSeconds();

    boolean isShiftTime(int currentTime);

    boolean isShotTime(int currentTime);
}
