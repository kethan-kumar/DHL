/* @Author: Vikram Singh */

package org.leaguesimulation;

import org.leaguemodel.interfaces.ITeam;
import org.leaguesimulation.interfaces.ITeamOnScoreboard;

public class TeamOnScoreboard implements ITeamOnScoreboard, Comparable<TeamOnScoreboard> {

    private ITeam team;
    private int score;
    private int lossPoints;
    private int lossPointsForTrade;

    public TeamOnScoreboard() {
    }

    public TeamOnScoreboard(ITeam team) {
        this.team = team;
        this.score = 0;
        this.lossPoints = 0;
        this.lossPointsForTrade = 0;
    }

    public int getLossPoints() {
        return lossPoints;
    }

    public void setLossPoints(int lossPoints) {
        this.lossPoints = lossPoints;
    }

    public ITeam getTeam() {
        return team;
    }

    public void setTeam(ITeam team) {
        this.team = team;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLossPointsForTrade() {
        return lossPointsForTrade;
    }

    public void setLossPointsForTrade(int lossPointsForTrade) {
        this.lossPointsForTrade = lossPointsForTrade;
    }

    public int compareTo(TeamOnScoreboard team) {
        int compareScore = team.getScore();
        return compareScore - this.score;
    }
}
