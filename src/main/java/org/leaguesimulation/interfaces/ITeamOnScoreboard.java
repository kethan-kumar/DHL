package org.leaguesimulation.interfaces;

import org.leaguemodel.interfaces.ITeam;

public interface ITeamOnScoreboard {
    ITeam getTeam();

    int getScore();

    void setScore(int score);

    int getLossPointsForTrade();

    void setLossPointsForTrade(int lossPointsForTrade);

    int getLossPoints();

    void setLossPoints(int lossPoints);
}
