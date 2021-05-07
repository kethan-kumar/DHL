/* @Author: Vikram Singh */

package org.leaguesimulation;

import org.apache.log4j.Logger;
import org.gamesimulation.interfaces.IGameSimulation;
import org.leaguesimulation.interfaces.ILeagueScoreStats;

public class LeagueScoreStats implements ILeagueScoreStats {
    private final Logger logger;
    private double goals = 0;
    private double shots = 0;
    private double penalties = 0;
    private double saves = 0;
    private double numTeams = 0;

    public LeagueScoreStats() {
        logger = Logger.getLogger(LeagueScoreStats.class.getName());
    }

    public double getAverageGoals() {
        return goals / numTeams;
    }

    public double getAverageShots() {
        return shots / numTeams;
    }

    public double getAveragePenalties() {
        return penalties / numTeams;
    }

    public double getAverageSaves() {
        return saves / numTeams;
    }

    public void updateStats(IGameSimulation gameSimulation) {
        logger.info("Updating League Stats");
        goals += gameSimulation.getTeamOne().getGoals() + gameSimulation.getTeamTwo().getGoals();
        shots += gameSimulation.getTeamOne().getShots() + gameSimulation.getTeamTwo().getShots();
        penalties += gameSimulation.getTeamOne().getPenalties() + gameSimulation.getTeamTwo().getPenalties();
        saves += gameSimulation.getTeamOne().getSaves() + gameSimulation.getTeamTwo().getSaves();
        numTeams += 2;
    }
}
