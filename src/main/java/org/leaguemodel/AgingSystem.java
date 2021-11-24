/* @Author: Kethan Kumar */
package org.leaguemodel;

import org.apache.log4j.Logger;
import org.leaguemodel.abstractfactory.LeagueModelAbstractFactory;
import org.leaguemodel.interfaces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgingSystem implements IAgingSystem {

    private final LeagueModelAbstractFactory factory;
    Logger logger;

    public AgingSystem() {
        factory = LeagueModelAbstractFactory.instance();
        logger = Logger.getLogger(AgingSystem.class.getName());
    }

    public Map<String, IPlayers> retirementAlgorithm(List<IPlayers> playersList, IAging aging) {
        logger.info("Retirement of players");
        int averageRetirementAge = aging.getAverageRetirementAge();
        int maximumAge = aging.getMaximumAge();
        int range = (maximumAge - averageRetirementAge) / 3;
        Map<String, IPlayers> playersToRetireMap = new HashMap<>();
        ICheckStrength iCheckStrength = factory.createCheckStrength();
        ITeam iTeam = factory.createTeam();
        iTeam.setPlayers(playersList);
        double averageStrength = iCheckStrength.teamStrength(iTeam) / playersList.size();

        for (IPlayers iPlayers : playersList) {
            double playerStrength;
            int age = iPlayers.getAge();
            int thrushHold = averageRetirementAge - range;
            String playerName = iPlayers.getPlayerName();
            playerStrength = iCheckStrength.playerStrength((IStrength) iPlayers) / 2.0;
            if (age >= maximumAge) {
                playersToRetireMap.put(playerName, iPlayers);
                iPlayers.setRetired(true);
                logger.debug("Player retired:" + playerName);
            } else if (age >= averageRetirementAge) {
                if (playerStrength < averageStrength) {
                    playersToRetireMap.put(playerName, iPlayers);
                    iPlayers.setRetired(true);
                    logger.debug("Player retired:" + playerName);
                }
            } else if ((age >= thrushHold) && (playerStrength < averageStrength / 2.0)) {
                playersToRetireMap.put(playerName, iPlayers);
                logger.debug("Player retired:" + playerName);
                iPlayers.setRetired(true);
            }
        }
        return playersToRetireMap;
    }

    public Map<String, IPlayers> retireTeamPlayers(List<Object> agingInputList, List<IPlayers> freeAgents, IAging aging) {
        List<IPlayers> playersList;
        ITeam team = (ITeam) agingInputList.get(0);
        IPlayerReplacement playerReplacement = (IPlayerReplacement) agingInputList.get(1);
        playersList = team.getPlayers();
        Map<String, IPlayers> retiredPlayers = retirementAlgorithm(playersList, aging);
        playerReplacement.replacePlayers(team, freeAgents);
        return retiredPlayers;
    }

    public Map<String, IPlayers> retireFreeAgents(IPlayers freeAgents, IAging aging) {
        List<IPlayers> playersList = new ArrayList<>();
        playersList.add(freeAgents);
        return retirementAlgorithm(playersList, aging);
    }

    public void agePlayer(IPlayers player, IInjurySystem injuryStatusUpdate, int numberOfDays, float statDecayChance) {
        player.setAge(player.getAge() + 1);
        injuryStatusUpdate.isPlayerRecovered(player);
        checkSkatingStat(player, statDecayChance);
        checkShootingStat(player, statDecayChance);
        checkCheckingStat(player, statDecayChance);
        checkSavingStat(player, statDecayChance);
    }

    public void checkSkatingStat(IPlayers player, float statDecayChance) {
        float value = (float) Math.random();
        if (value > statDecayChance) {
            player.setSkating(player.getSkating() + 1);
        }
    }

    void checkShootingStat(IPlayers player, float statDecayChance) {
        float value = (float) Math.random();
        if (value > statDecayChance) {
            player.setShooting(player.getShooting() + 1);
        }
    }

    void checkCheckingStat(IPlayers player, float statDecayChance) {
        float value = (float) Math.random();
        if (value > statDecayChance) {
            player.setChecking(player.getChecking() + 1);
        }
    }

    void checkSavingStat(IPlayers player, float statDecayChance) {
        float value = (float) Math.random();
        if (value > statDecayChance) {
            player.setSaving(player.getSaving() + 1);
        }
    }
}