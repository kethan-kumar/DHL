package org.leaguesimulation.interfaces;

public interface IPlayOffScoreboard {
    ITeamOnScoreboard getWinner();

    ITeamOnScoreboard checkWinner();

    boolean setFinal();

    void initiateScoreToZero();
}
