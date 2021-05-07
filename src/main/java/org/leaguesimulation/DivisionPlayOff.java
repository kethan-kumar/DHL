/* @Author: Vikram Singh */

package org.leaguesimulation;

import org.apache.log4j.Logger;
import org.leaguesimulation.interfaces.IPlayOffScoreboard;
import org.leaguesimulation.interfaces.IScoreboard;
import org.leaguesimulation.interfaces.ISeed;
import org.leaguesimulation.interfaces.ITeamOnScoreboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DivisionPlayOff implements IScoreboard, IPlayOffScoreboard, ISeed {

    private final IScoreboard divisionScoreboard;
    private final List<TeamOnScoreboard> divisionPlayOffScoreboard;
    private final Logger logger;
    private TeamOnScoreboard winner;
    private boolean isFinal;

    public DivisionPlayOff(IScoreboard divisionScoreboard) {
        this.divisionScoreboard = divisionScoreboard;
        divisionPlayOffScoreboard = new ArrayList<>();
        logger = Logger.getLogger(DivisionPlayOff.class.getName());
    }

    public List<TeamOnScoreboard> loadScoreboard() {
        logger.info("Loading playOff Scoreboard for Division");
        divisionScoreboard.sort();
        divisionPlayOffScoreboard.add(divisionScoreboard.getScoreboard().get(0));
        divisionPlayOffScoreboard.add(divisionScoreboard.getScoreboard().get(1));
        divisionPlayOffScoreboard.add(divisionScoreboard.getScoreboard().get(2));
        return divisionPlayOffScoreboard;
    }

    public List<TeamOnScoreboard> getScoreboard() {
        return divisionPlayOffScoreboard;
    }

    public List<TeamOnScoreboard> sort() {
        Collections.sort(divisionPlayOffScoreboard);
        return divisionPlayOffScoreboard;
    }

    public TeamOnScoreboard getWinner() {
        return winner;
    }

    public ITeamOnScoreboard checkWinner() {
        if (isFinal) {
            winner = sort().get(0);
            return winner;
        }
        return null;
    }

    public boolean setFinal() {
        isFinal = true;
        return true;
    }

    public void initiateScoreToZero() {
        for (TeamOnScoreboard team :
                divisionPlayOffScoreboard) {
            team.setScore(0);
            team.setLossPointsForTrade(0);
        }
    }

    public void seed(int numberOfWildCards) {
        logger.error("Correct usage: call with League");
    }

    public void seed(TeamOnScoreboard wildCard) {
        divisionPlayOffScoreboard.add(wildCard);
    }
}
