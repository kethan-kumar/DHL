/* @Author: Vikram Singh */

package org.leaguesimulation;

import org.apache.commons.lang3.StringUtils;
import org.gamesimulation.interfaces.IGameSimulation;
import org.io.abstractfactory.IOAbstractFactory;
import org.io.interfaces.IDisplayLeagueSimulation;
import org.leaguemodel.interfaces.IGameplayConfig;
import org.leaguesimulation.abstractfactory.LeagueAbstractFactory;
import org.leaguesimulation.interfaces.IGame;
import org.leaguesimulation.interfaces.ITeamOnScoreboard;
import org.trophysystem.BestPerformanceSubject;
import org.trophysystem.abstractfactory.TrophyAbstractFactory;
import org.trophysystem.interfaces.IPerformanceObserver;

public class Game extends BestPerformanceSubject implements IGame {
    private final LeagueAbstractFactory factory;
    private final IOAbstractFactory displayFactory;
    private final ITeamOnScoreboard teamOne;
    private final ITeamOnScoreboard teamTwo;
    private final TrophyAbstractFactory trophyFactory;
    IPerformanceObserver defenseMenObserver;
    IPerformanceObserver draftedPlayerObserver;
    IPerformanceObserver goalieObserver;
    IPerformanceObserver goalScorerObserver;

    public Game(ITeamOnScoreboard teamOne, ITeamOnScoreboard teamTwo) {
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        trophyFactory = TrophyAbstractFactory.instance();
        factory = LeagueAbstractFactory.instance();
        displayFactory = IOAbstractFactory.instance();
    }

    public IGameSimulation executeGame(IGameplayConfig gameplayConfig) {
        IDisplayLeagueSimulation display = displayFactory.createDisplayLeagueSimulation();
        IGameSimulation gameSimulation = factory.createGameSimulation(teamOne.getTeam(), teamTwo.getTeam(), gameplayConfig);
        gameSimulation.execute();
        ITeamOnScoreboard gameWinner = gameSimulation.getWinner() == teamOne.getTeam() ? teamOne : teamTwo;
        ITeamOnScoreboard gameLoser = gameSimulation.getLoser() == teamTwo.getTeam() ? teamTwo : teamOne;
        display.displayGameStats(gameSimulation.getTeamOne(), gameSimulation.getTeamTwo());
        display.printGameResult(gameWinner.getTeam());
        updateScores((TeamOnScoreboard) gameWinner, (TeamOnScoreboard) gameLoser);
        trophySystemObservers(gameSimulation);
        return gameSimulation;
    }

    public void updateScores(TeamOnScoreboard gameWinner, TeamOnScoreboard gameLoser) {
        gameWinner.setScore(gameWinner.getScore() + 2);
        gameLoser.setScore(gameLoser.getScore() - 2);
        gameLoser.setLossPointsForTrade(gameLoser.getLossPointsForTrade() + 2);
        gameLoser.setLossPoints(gameLoser.getLossPoints() + 2);
    }

    public ITeamOnScoreboard getTeamOne() {
        return teamOne;
    }

    public ITeamOnScoreboard getTeamTwo() {
        return teamTwo;
    }

    public void trophySystemObservers(IGameSimulation gameSimulation) {
        defenseMenObserver = trophyFactory.createDefenseMenObserver();
        draftedPlayerObserver = trophyFactory.createDraftPlayerObserver();
        goalieObserver = trophyFactory.createGoalieObserver();
        goalScorerObserver = trophyFactory.createGoalScorerObserver();

        for (int i = 0; i < gameSimulation.getPlayers().size(); i++) {
            this.performerName = gameSimulation.getPlayers().get(i).getPlayer().getPlayerName();
            String playerPosition = gameSimulation.getPlayers().get(i).getPlayer().getPosition();
            int totalScore = gameSimulation.getPlayers().get(i).getSaves() + gameSimulation.getPlayers().get(i).getGoals();
            this.attachPlayerObserver(goalScorerObserver);
            if (StringUtils.equalsIgnoreCase(playerPosition, POSITION.GOALIE.toString())) {
                this.attachPlayerObserver(defenseMenObserver);
                this.score = this.score + totalScore;
                this.notifyObserver();
                this.detachPlayerObserver(defenseMenObserver);
            }
            if (StringUtils.equalsIgnoreCase(playerPosition, POSITION.FORWARD.toString())) {
                this.attachPlayerObserver(draftedPlayerObserver);
                this.score = this.score + totalScore;
                this.notifyObserver();
                this.detachPlayerObserver(defenseMenObserver);
            }
            if (StringUtils.equalsIgnoreCase(playerPosition, POSITION.DEFENSE.toString())) {
                this.attachPlayerObserver(goalieObserver);
                this.score = this.score + totalScore;
                this.notifyObserver();
                this.detachPlayerObserver(defenseMenObserver);
            }
            this.detachPlayerObserver(goalScorerObserver);
        }
    }

    enum POSITION {
        GOALIE, FORWARD, DEFENSE
    }

}
