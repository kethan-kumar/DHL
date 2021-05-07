package org.io;

import org.apache.log4j.Logger;
import org.gamesimulation.TeamInGame;
import org.io.interfaces.IDisplayLeagueSimulation;
import org.leaguemodel.interfaces.ITeam;
import org.leaguesimulation.interfaces.ILeagueScoreStats;
import org.leaguesimulation.interfaces.IScoreboard;
import org.leaguesimulation.interfaces.ITeamOnScoreboard;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class DisplayLeagueSimulation implements IDisplayLeagueSimulation {
    public void printGame(ITeam teamOne, ITeam teamTwo) {
        System.out.println("\nGame: " + teamOne.getTeamName() + " V/S " + teamTwo.getTeamName() + " ===> ");
    }

    public void printGameResult(ITeam team) {
        System.out.print("Winner is " + team.getTeamName());
    }

    public void printDate(LocalDate date) {
        System.out.print("\n===Date:" + date.toString() + "===");
    }

    public void printWinner(ITeamOnScoreboard team) {
        System.out.println("\n============LEAGUE WINNER: " + team.getTeam().getTeamName() + "============\n");
        Logger logger = Logger.getLogger(Display.class.getName());
        logger.info("League Winner: " + team.getTeam().getTeamName());
    }

    public void printRegularSeason(IScoreboard leagueScoreboard) {
        leagueScoreboard.sort();
        System.out.print("\n\n===END OF REGULAR SEASON===");
        printScoreboard(leagueScoreboard);
        System.out.println();
        System.out.print("===END OF REGULAR SEASON===");
    }

    private void printScoreboard(IScoreboard leagueScoreboard) {
        System.out.println("\nTeam\t\tScore\t\tWon\t\tLoss");
        for (ITeamOnScoreboard team :
                leagueScoreboard.getScoreboard()) {
            System.out.println(team.getTeam().getTeamName() + "\t" + team.getScore() + "\t" + (team.getScore() + team.getLossPoints()) / 2 + "\t" + team.getLossPoints() / 2);
        }
    }

    public void displayGameStats(TeamInGame teamOne, TeamInGame teamTwo) {
        System.out.println(teamOne.getTeam().getTeamName() + "-> Goals: " + teamOne.getGoals() + " Saves: " + teamOne.getSaves() + " Shots: " + teamOne.getShots() + " Penalties: " + teamOne.getPenalties());
        System.out.println(teamTwo.getTeam().getTeamName() + "-> Goals: " + teamTwo.getGoals() + " Saves: " + teamTwo.getSaves() + " Shots: " + teamTwo.getShots() + " Penalties: " + teamTwo.getPenalties());
    }


    public void displayLeagueScoreAverage(ILeagueScoreStats leagueStats) {
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println();
        System.out.println("===LEAGUE AVERAGE SCORES==");
        System.out.println("Goals: " + df.format(leagueStats.getAverageGoals()));
        System.out.println("Penalties: " + df.format(leagueStats.getAveragePenalties()));
        System.out.println("Shots: " + df.format(leagueStats.getAverageShots()));
        System.out.println("Saves: " + df.format(leagueStats.getAverageSaves()));
    }

    public void printPlayOffSeason(IScoreboard leagueScoreboard) {
        leagueScoreboard.sort();
        System.out.print("\n\n===END OF PLAYOFFS===");
        printScoreboard(leagueScoreboard);
        System.out.println();
        System.out.print("===END OF PLAYOFFS===");
    }

    public void printSaveSimulationMessage() {
        System.out.println("\nPlease wait while we are saving the progress...");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

}
