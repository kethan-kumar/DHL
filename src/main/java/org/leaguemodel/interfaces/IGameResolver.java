/* @Author: Kethan Kumar */
package org.leaguemodel.interfaces;

public interface IGameResolver {

    Double getPenaltyChance();

    void setPenaltyChance(Double penaltyChance);

    Double getShotToGoalChance();

    void setShotToGoalChance(Double shotToGoalChance);

    Double getPenaltyToGoalChance();

    void setPenaltyToGoalChance(Double penaltyToGoalChance);
}
