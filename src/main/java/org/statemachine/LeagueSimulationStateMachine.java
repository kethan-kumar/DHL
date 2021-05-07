/* @Author: Vikram Singh */

package org.statemachine;

import org.apache.log4j.Logger;
import org.gamesimulation.interfaces.IGameSimulation;
import org.io.abstractfactory.IOAbstractFactory;
import org.io.interfaces.IDisplayLeagueSimulation;
import org.leaguemodel.abstractfactory.LeagueModelAbstractFactory;
import org.leaguemodel.interfaces.*;
import org.leaguesimulation.LeaguePlayOff;
import org.leaguesimulation.abstractfactory.LeagueAbstractFactory;
import org.leaguesimulation.interfaces.*;
import org.statemachine.abstractfactory.StateAbstractFactory;
import org.statemachine.interfaces.ILeagueDatePersist;
import org.statemachine.interfaces.IStateMachine;
import org.statemachine.interfaces.ITrainingSystem;
import org.trading.abstractfactory.TradingAbstractFactory;
import org.trading.interfaces.IDraftPickSlots;
import org.trading.interfaces.ITradeOffer;
import org.trading.interfaces.ITradingDraftPicks;
import org.trophysystem.RegularSeasonSubject;
import org.trophysystem.abstractfactory.TrophyAbstractFactory;
import org.trophysystem.interfaces.ITeamObserver;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LeagueSimulationStateMachine extends RegularSeasonSubject implements IStateMachine, ILeagueDatePersist {
    private static final int PLAYOFF_GAMES_PER_TEAM = 7;
    private static final int REGULAR_SEASON_GAMES_PER_TEAM = 82;
    private static final int EXPECTED_PLAYOFF_MATCHES = 16 * 7;
    private static final int ONEYEAR = 365;
    ILeague league;
    IDisplayLeagueSimulation display;
    LocalDate currentDate;
    ITeamObserver teamObserver;
    ILeagueScoreStats scoreStats;
    TrophyAbstractFactory trophyState;
    IOAbstractFactory ioFactory;
    LeagueModelAbstractFactory leagueModelFactory;
    LeagueAbstractFactory leagueFactory;
    TradingAbstractFactory tradingFactory;
    StateAbstractFactory stateFactory;
    IPlayerDraft playerDraft;

    public LeagueSimulationStateMachine() {
        initStateFactories();
    }

    public LeagueSimulationStateMachine(ILeague league) {
        this.league = league;
        initStateFactories();

    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    private void initStateFactories() {
        leagueModelFactory = LeagueModelAbstractFactory.instance();
        leagueFactory = LeagueAbstractFactory.instance();
        tradingFactory = TradingAbstractFactory.instance();
        stateFactory = StateAbstractFactory.instance();
        trophyState = TrophyAbstractFactory.instance();
        ioFactory = IOAbstractFactory.instance();
        display = ioFactory.createDisplayLeagueSimulation();
        teamObserver = trophyState.createRegularSeasonObserver();
        scoreStats = leagueFactory.createLeagueScoreStats();
    }

    public void runStateMachine() {
        Logger logger = Logger.getLogger(LeagueSimulationStateMachine.class.getName());
        logger.info("Entering Simulation..");
        ITradingDraftPicks tradingDraftPicksObj = tradingFactory.createTradingDraftPicks();
        IDraftPickSlots draftPickSlotsObj = tradingDraftPicksObj.draftPickSlotsInitializer(league);
        IScoreboard leagueScoreboard = leagueFactory.createLeagueScoreboard(league);
        leagueScoreboard.loadScoreboard();
        IScheduler regularSeasonScheduler = leagueFactory.createRegularSeasonScheduler(leagueScoreboard);
        ILeagueTimeline leagueTimeline = leagueFactory.createLeagueTimeline(league.getLeagueDate());
        leagueTimeline.setCurrentDateFromDB(league.getLeagueDate());
        LocalDate newSeasonDate = LocalDate.of(leagueTimeline.getCurrentDate().getYear(), Month.SEPTEMBER, 30);
        league.setLeagueDate(newSeasonDate);
        leagueTimeline.setCurrentDate(newSeasonDate);
        List<IGame> regularSeasonGames = regularSeasonScheduler.schedule(REGULAR_SEASON_GAMES_PER_TEAM);
        List<IGame> playOffGames = new ArrayList<>();
        boolean stanleyCupWinnerNotDecided = true;
        IScheduler playOffScheduler = null;
        IScoreboard leaguePlayOff = null;
        int expectedNumberOfPlayOffMatches = EXPECTED_PLAYOFF_MATCHES;

        while (stanleyCupWinnerNotDecided) {
            leagueTimeline.incrementDateByOne();
            LocalDate playerDraftDate = leagueTimeline.getCurrentDate();
            int draftInitiationMonth = playerDraftDate.getMonthValue();
            int draftInitiationDay = playerDraftDate.getDayOfMonth();
            logger.info("Advancing time by one day.");
            display.printDate(leagueTimeline.getCurrentDate());
            if (!leagueTimeline.isRegularSeason() && playOffScheduler == null) {
                leaguePlayOff = leagueFactory.createLeaguePlayOff(leagueScoreboard);
                leaguePlayOff.loadScoreboard();
                seedLeaguePlayOff((ISeed) leaguePlayOff);
                initiateLeaguePlayOffTeamsScore((IPlayOffScoreboard) leaguePlayOff);
                playOffScheduler = leagueFactory.createPlayOffScheduler(leaguePlayOff);
                playOffGames = playOffScheduler.schedule(PLAYOFF_GAMES_PER_TEAM);
            }

            simulateRegularSeasonMatches(leagueTimeline, regularSeasonGames, leagueScoreboard);
            if (leagueTimeline.isTradePossible()) {
                ITradeOffer tradeOffer = tradingFactory.createTradeOffer();
                tradeOffer.generateTradeOffer(leagueScoreboard, league, draftPickSlotsObj);
            }

            if (playOffGames.size() > 0) {
                expectedNumberOfPlayOffMatches = simulatePlayOffMatches(leagueTimeline, playOffGames, expectedNumberOfPlayOffMatches);
            }

            if (regularSeasonGames.isEmpty() && playOffGames.isEmpty() && Objects.nonNull(playOffScheduler)) {
                playOffGames = playOffScheduler.schedule(PLAYOFF_GAMES_PER_TEAM);
                IPlayOffScoreboard leaguePlayOffScoreboard = (IPlayOffScoreboard) leaguePlayOff;

                if (Objects.isNull(leaguePlayOffScoreboard.getWinner())) {
                    leaguePlayOffScoreboard.checkWinner();
                } else {
                    stanleyCupWinnerNotDecided = false;
                    display.printPlayOffSeason((IScoreboard) leaguePlayOffScoreboard);
                    display.displayLeagueScoreAverage(scoreStats);
                    display.printWinner(((LeaguePlayOff) leaguePlayOffScoreboard).getScoreboard().get(0));
                }
            }

            IInjurySystem injurySystem = leagueModelFactory.createInjurySystem();
            IAging aging = league.getGameplayConfig().getAging();
            IAgingSystem agingSystem = leagueModelFactory.createAgingSystem();
            IPlayerReplacement playerReplacement = leagueModelFactory.createPlayerReplacement();
            IAgingEngine agingEngine = leagueModelFactory.createAgingEngine(injurySystem, aging, agingSystem, playerReplacement);
            agingEngine.initAging(league, ONEYEAR);
            setCurrentDate(leagueTimeline.getCurrentDate());
        }
        playerDraft = leagueModelFactory.createPlayerDraft(leagueScoreboard, leaguePlayOff, draftPickSlotsObj);
        playerDraft.startDrafting();

    }

    private void simulateRegularSeasonMatches(ILeagueTimeline leagueTimeline, List<IGame> regularSeasonGames, IScoreboard leagueScoreboard) {
        if (leagueTimeline.isRegularSeason()) {
            Logger logger = Logger.getLogger(LeagueSimulationStateMachine.class.getName());
            logger.info("Simulating Regular Season: " + leagueTimeline.getCurrentDate());
            int j = 0;
            int numberOfMatchesPerDay = calculateNumberOfRegularMatchesPerDay(leagueTimeline, regularSeasonGames);
            while (j < numberOfMatchesPerDay) {
                IInjuries injuries = null;
                try {
                    injuries = executeStatistics(regularSeasonGames);
                    display.printGame(regularSeasonGames.get(0).getTeamOne().getTeam(), regularSeasonGames.get(0).getTeamTwo().getTeam());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                IGameSimulation gameSimulation = regularSeasonGames.get(0).executeGame(league.getGameplayConfig());
                scoreStats.updateStats(gameSimulation);
                executeInjurySystem(regularSeasonGames, injuries);
                regularSeasonGames.remove(0);
                j++;
            }
            if (regularSeasonGames.isEmpty()) {
                display.printRegularSeason(leagueScoreboard);
                this.leagueScoreboard = leagueScoreboard;
                this.attachPlayerObserver(teamObserver);
                this.notifyObserver();
            }
        }
    }

    private int simulatePlayOffMatches(ILeagueTimeline leagueTimeline, List<IGame> playOffGames, int expectedMatches) {
        Logger logger = Logger.getLogger(LeagueSimulationStateMachine.class.getName());
        logger.info("Simulating Playoff: " + leagueTimeline.getCurrentDate());
        int j = 0;
        int numberOfMatchesPerDay = calculateNumberOfPlayOffMatchesPerDay(leagueTimeline, expectedMatches);
        while (j < numberOfMatchesPerDay && playOffGames.size() > 0) {
            IInjuries injuries = executeStatistics(playOffGames);
            IGameSimulation gameSimulation = playOffGames.get(0).executeGame(league.getGameplayConfig());
            scoreStats.updateStats(gameSimulation);
            executeInjurySystem(playOffGames, injuries);
            playOffGames.remove(0);
            expectedMatches--;
            j++;
        }
        return expectedMatches;
    }

    private void executeInjurySystem(List<IGame> games, IInjuries injuries) {
        IInjurySystem iInjurySystem = leagueModelFactory.createInjurySystem();
        iInjurySystem.checkTeamInjuries(games.get(0).getTeamOne().getTeam(), injuries);
        iInjurySystem.checkTeamInjuries(games.get(0).getTeamTwo().getTeam(), injuries);
        System.out.println("Injury Check:" + games.get(0).getTeamOne().getTeam().getPlayers().get(0).getNoOfDaysInjured());
    }

    private IInjuries executeStatistics(List<IGame> games) {
        ITrainingSystem iTraining = stateFactory.createTrainingSystem();
        IInjuries injuries = league.getGameplayConfig().getInjuries();
        try {
            iTraining.checkStatistics(injuries, games.get(0).getTeamOne().getTeam());
            iTraining.checkStatistics(injuries, games.get(0).getTeamTwo().getTeam());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(" Stat Increased:" + games.get(0).getTeamOne().getTeam().getPlayers().get(0).getSkating() + " finished training!");
        return injuries;
    }

    private int calculateNumberOfPlayOffMatchesPerDay(ILeagueTimeline leagueTimeline, int expectedMatches) {
        int numberOfMatchesPerDay = expectedMatches / leagueTimeline.daysLeftOfPlayOffSeason();
        return numberOfMatchesPerDay < 1 ? 1 : numberOfMatchesPerDay;
    }

    private void seedLeaguePlayOff(ISeed leaguePlayOff) {
        ISeed seedLeague = leaguePlayOff;
        seedLeague.seed(2);
    }

    private void initiateLeaguePlayOffTeamsScore(IPlayOffScoreboard leagueScoreboard) {
        IPlayOffScoreboard leaguePlayOffScoreboard = leagueScoreboard;
        leaguePlayOffScoreboard.initiateScoreToZero();
    }

    private int calculateNumberOfRegularMatchesPerDay(ILeagueTimeline leagueTimeline, List<IGame> regularSeasonGames) {
        int numberOfMatchesPerDay = regularSeasonGames.size() / leagueTimeline.daysLeftOfRegularSeason();
        return numberOfMatchesPerDay < 1 ? 1 : numberOfMatchesPerDay;
    }

}
