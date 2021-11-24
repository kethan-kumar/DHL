/* @Author: Kethan Kumar */
package org.leaguemodel.interfaces;

import java.util.List;
import java.util.Map;

public interface IAgingSystem {
    Map<String, IPlayers> retireTeamPlayers(List<Object> agingInputList, List<IPlayers> freeAgents, IAging aging);

    Map<String, IPlayers> retireFreeAgents(IPlayers freeAgents, IAging aging);

    void agePlayer(IPlayers player, IInjurySystem injuryStatusUpdate, int numberOfDays, float statDecayChance);
}
