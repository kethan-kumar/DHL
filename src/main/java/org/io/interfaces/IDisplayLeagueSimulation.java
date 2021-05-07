/* @Author: Vikram Singh */

package org.io.interfaces;

import org.gamesimulation.TeamInGame;
import org.leaguemodel.interfaces.ITeam;
import org.leaguesimulation.interfaces.ILeagueScoreStats;
import org.leaguesimulation.interfaces.IScoreboard;
import org.leaguesimulation.interfaces.ITeamOnScoreboard;

import java.time.LocalDate;

public interface IDisplayLeagueSimulation {
    void printGame(ITeam teamOne, ITeam teamTwo);

    void printGameResult(ITeam team);

    void printDate(LocalDate date);

    void printWinner(ITeamOnScoreboard team);

    void printRegularSeason(IScoreboard leagueScoreboard);

    void printPlayOffSeason(IScoreboard leagueScoreboard);

    void displayGameStats(TeamInGame teamOne, TeamInGame teamTwo);

    void displayLeagueScoreAverage(ILeagueScoreStats leagueStats);

    void printSaveSimulationMessage();

    void displayMessage(String message);
}
