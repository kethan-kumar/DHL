/* @Author:  Siddhant Ashutosh */
package org.leaguemodel;

import org.leaguemodel.interfaces.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class AgingEngine implements IAgingEngine {
    private static final int ONEYEAR = 365;
    private IInjurySystem injurySystem;
    private IAging aging;
    private IAgingSystem agingSystem;
    private IPlayerReplacement playerReplacement;

    public AgingEngine(IInjurySystem injurySystem, IAging aging, IAgingSystem agingSystem, IPlayerReplacement playerReplacement) {
        this.injurySystem = injurySystem;
        this.aging = aging;
        this.agingSystem = agingSystem;
        this.playerReplacement = playerReplacement;
    }


    public void initAging(ILeague leagueLOM, int numberOfDays) {
        float statDecayChance = leagueLOM.getGameplayConfig().getAging().getStatDecayChance();
        int month;
        numberOfDays = 365;
        LocalDate leagueDate;
        LocalDate playerBday;
        Period actualAge;
        List<Object> agingInputList = new ArrayList<>();
        List<IPlayers> freeAgents = leagueLOM.getFreeAgents();
        for (IConference conference : leagueLOM.getConferences()) {
            for (IDivisions divisions : conference.getDivisions()) {
                for (ITeam team : divisions.getTeams()) {
                    for (IPlayers player : team.getPlayers()) {
                        leagueDate = leagueLOM.getLeagueDate();
                        playerBday = LocalDate.of(player.getBirthYear(), player.getBirthMonth(), player.getBirthDay());
                        actualAge = Period.between(playerBday, leagueDate);
                        month = actualAge.getMonths();
                        agingInputList.add(team);
                        agingInputList.add(playerReplacement);
                        agingInputList.add(freeAgents);
                        if (month % 12 == 0) {
                            agingSystem.agePlayer(player, injurySystem, numberOfDays, statDecayChance);
                        }
                        if ((numberOfDays % ONEYEAR) == 0) {
                            agingSystem.retireTeamPlayers(agingInputList, freeAgents, aging);
                        }
                    }
                }
            }
        }
        for (IPlayers freeAgent : leagueLOM.getFreeAgents()) {
            agingSystem.agePlayer(freeAgent, injurySystem, numberOfDays, statDecayChance);
            if ((numberOfDays % ONEYEAR) == 0) {
                agingSystem.retireFreeAgents(freeAgent, aging);
            }
        }
    }
}
