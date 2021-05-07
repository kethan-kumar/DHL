/* @Author: Vikram Singh */

package org.gamesimulation;

import org.apache.log4j.Logger;
import org.gamesimulation.interfaces.IFormation;
import org.leaguemodel.interfaces.ITeam;

import java.util.ArrayList;
import java.util.List;

public class TeamOnIce extends TeamInGame {
    private IFormation forwardOnIce;
    private IFormation defenseOnIce;
    private IFormation goalieOnIce;
    private int shiftCount = 1;
    private Logger logger;

    public TeamOnIce(ITeam team) {
        super(team);
    }

    public IFormation getForwardOnIce() {
        return forwardOnIce;
    }

    public IFormation getDefenseOnIce() {
        return defenseOnIce;
    }

    public IFormation getGoalieOnIce() {
        return goalieOnIce;
    }

    public void shuffle() {
        logger = Logger.getLogger(TeamOnIce.class.getName());
        logger.info("Shift count" + shiftCount);
        List<IFormation> lines = new ArrayList<>();
        lines.addAll(super.getForwardLines());
        lines.addAll(super.getDefenseLines());
        IFormation lineToBeShifted = lines.get(shiftCount % lines.size());
        setLine(lineToBeShifted.getFormationType(), lineToBeShifted);
        shiftCount++;
    }

    public void setInitialTeamOnIce() {
        setLine(GamePosition.position.FORWARD, super.getForwardLines().get(0));
        setLine(GamePosition.position.DEFENSE, super.getDefenseLines().get(0));
        setLine(GamePosition.position.GOALIE, super.getGoalies().get(0));
    }

    private void setLine(GamePosition.position type, IFormation line) {
        if (type.equals(GamePosition.position.FORWARD)) {
            forwardOnIce = line;
        } else if (type.equals(GamePosition.position.DEFENSE)) {
            defenseOnIce = line;
        } else if (type.equals(GamePosition.position.GOALIE)) {
            goalieOnIce = line;
        }
    }
}
