/* @Author: Vikram Singh */

package org.leaguesimulation.abstractfactory;

import org.gamesimulation.interfaces.IGameSimulation;
import org.leaguemodel.interfaces.*;
import org.leaguesimulation.TeamOnScoreboard;
import org.leaguesimulation.interfaces.*;

import java.time.LocalDate;

public abstract class LeagueAbstractFactory {

    private static LeagueAbstractFactory uniqueInstance = null;

    protected LeagueAbstractFactory() {

    }

    public static LeagueAbstractFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new LeagueSimulationFactory();
        }
        return uniqueInstance;
    }

    public static void setFactory(LeagueSimulationFactory leagueSimulationFactory) {
        uniqueInstance = leagueSimulationFactory;
    }

    public abstract IScoreboard createDivisionPlayOff(IScoreboard divisionScoreboard);

    public abstract IScoreboard createLeaguePlayOff(IScoreboard leagueScoreboard);

    public abstract IScoreboard createDivisionScoreboard(IDivisions division);

    public abstract TeamOnScoreboard createTeamOnScoreboard(ITeam team);

    public abstract IGameSimulation createGameSimulation(ITeam teamOne, ITeam teamTwo, IGameplayConfig gameplayConfig);

    public abstract IScoreboard createConferencePlayOffScoreboard(IConferenceScoreboard conferenceScoreboard);

    public abstract IScoreboard createConferenceScoreboard(IConference conference);

    public abstract IGame createGame(ITeamOnScoreboard teamOne, ITeamOnScoreboard teamTwo);

    public abstract IPlayOffSeries createPlayOffSeries(IGame game, int numGames);

    public abstract ILeagueScoreStats createLeagueScoreStats();

    public abstract IScoreboard createLeagueScoreboard(ILeague league);

    public abstract IScheduler createRegularSeasonScheduler(IScoreboard leagueScoreboard);

    public abstract IScheduler createPlayOffScheduler(IScoreboard leagueScoreboard);

    public abstract ILeagueTimeline createLeagueTimeline(LocalDate startDate);

}
