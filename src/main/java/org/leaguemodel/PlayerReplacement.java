/* @Author:  Siddhant Ashutosh */
package org.leaguemodel;

import org.apache.commons.lang3.StringUtils;
import org.leaguemodel.abstractfactory.LeagueModelAbstractFactory;
import org.leaguemodel.interfaces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;

public class PlayerReplacement implements IPlayerReplacement {
    private final LeagueModelAbstractFactory factory = LeagueModelAbstractFactory.instance();

    public void replacePlayers(ITeam team, List<IPlayers> freeAgentsList) {
        double playerStrength;
        boolean retireStatus = false;
        ICheckStrength iCheckStrength = factory.createCheckStrength();
        Map<IPlayers, Double> freeAgentStrength = new HashMap<>();

        for (IPlayers freeAgents : freeAgentsList) {
            playerStrength = iCheckStrength.playerStrength((IStrength) freeAgents);
            freeAgentStrength.put(freeAgents, playerStrength);
        }
        List<Map.Entry<IPlayers, Double>> sortedPlayers = freeAgentStrength.entrySet().stream().sorted(reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toList());
        List<IPlayers> freeAgentsToRemove = new ArrayList<>();

        for (int i = 0; i < team.getPlayers().size(); i++) {
            IPlayers player = team.getPlayers().get(i);
            if (player.isRetired()) {
                for (Map.Entry<IPlayers, Double> sortedPlayer : sortedPlayers) {
                    IPlayers freeAgent = sortedPlayer.getKey();
                    if (StringUtils.equalsIgnoreCase(player.getPosition(), freeAgent.getPosition())) {
                        player.setPlayerName(freeAgent.getPlayerName());
                        player.setSkating(freeAgent.getSkating());
                        player.setShooting(freeAgent.getShooting());
                        player.setChecking(freeAgent.getChecking());
                        player.setSaving(freeAgent.getSaving());
                        player.setAge(freeAgent.getAge());
                        player.setRetired(retireStatus);
                        freeAgentsToRemove.add(freeAgent);
                        break;
                    }
                }
            }
        }
        for (IPlayers iPlayers : freeAgentsToRemove) {
            if (freeAgentsList.contains(iPlayers)) {
                freeAgentsList.remove(iPlayers);
            }
        }
    }
}
