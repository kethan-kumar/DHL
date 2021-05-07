/* @Author: Vikram Singh */

package org.leaguesimulation.abstractfactory;

import org.gamesimulation.GameSimulation;
import org.gamesimulation.interfaces.IGameSimulation;
import org.leaguemodel.interfaces.*;
import org.leaguesimulation.*;
import org.leaguesimulation.interfaces.*;

import java.time.LocalDate;

public class LeagueSimulationFactory extends LeagueAbstractFactory {

    public IScoreboard createDivisionPlayOff(IScoreboard divisionScoreboard) {
        return new DivisionPlayOff(divisionScoreboard);
    }


    public IScoreboard createLeaguePlayOff(IScoreboard leagueScoreboard) {
        return new LeaguePlayOff(leagueScoreboard);
    }

    public IScoreboard createDivisionScoreboard(IDivisions division) {
        return new DivisionScoreboard(division);
    }

    public TeamOnScoreboard createTeamOnScoreboard(ITeam team) {
        return new TeamOnScoreboard(team);
    }

    public IGameSimulation createGameSimulation(ITeam teamOne, ITeam teamTwo, IGameplayConfig gameplayConfig) {
        return new GameSimulation(teamOne, teamTwo, gameplayConfig);
    }

    public IScoreboard createConferencePlayOffScoreboard(IConferenceScoreboard conferenceScoreboard) {
        return new ConferencePlayOff(conferenceScoreboard);
    }

    public IScoreboard createConferenceScoreboard(IConference conference) {
        return new ConferenceScoreboard(conference);
    }

    public IGame createGame(ITeamOnScoreboard teamOne, ITeamOnScoreboard teamTwo) {
        return new Game(teamOne, teamTwo);
    }

    public IPlayOffSeries createPlayOffSeries(IGame game, int numGames) {
        return new PlayOffSeries(game, numGames);
    }

    public ILeagueScoreStats createLeagueScoreStats() {
        return new LeagueScoreStats();
    }

    public IScoreboard createLeagueScoreboard(ILeague league) {
        return new LeagueScoreboard(league);
    }


    public IScheduler createRegularSeasonScheduler(IScoreboard leagueScoreboard) {
        return new RegularSeasonScheduler(leagueScoreboard);
    }


    public IScheduler createPlayOffScheduler(IScoreboard leagueScoreboard) {
        return new PlayOffScheduler(leagueScoreboard);
    }


    public ILeagueTimeline createLeagueTimeline(LocalDate startDate) {
        return new LeagueTimeline(startDate);
    }
}
