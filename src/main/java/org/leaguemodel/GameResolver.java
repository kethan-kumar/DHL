/* @Author: Kethan Kumar */
package org.leaguemodel;

import org.leaguemodel.interfaces.IGameResolver;

public class GameResolver implements IGameResolver {

    private Double penaltyChance;
    private Double shotToGoalChance;
    private Double penaltyToGoalChance;


    public Double getPenaltyChance() {
        return penaltyChance;
    }

    public void setPenaltyChance(Double penaltyChance) {
        this.penaltyChance = penaltyChance;
    }

    public Double getShotToGoalChance() {
        return shotToGoalChance;
    }

    public void setShotToGoalChance(Double shotToGoalChance) {
        this.shotToGoalChance = shotToGoalChance;
    }

    public Double getPenaltyToGoalChance() {
        return penaltyToGoalChance;
    }

    public void setPenaltyToGoalChance(Double penaltyToGoalChance) {
        this.penaltyToGoalChance = penaltyToGoalChance;
    }


}
