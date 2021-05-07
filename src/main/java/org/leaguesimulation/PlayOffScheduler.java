/* @Author: Vikram Singh */

package org.leaguesimulation;

import org.apache.log4j.Logger;
import org.leaguesimulation.abstractfactory.LeagueAbstractFactory;
import org.leaguesimulation.interfaces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayOffScheduler implements IScheduler {

    private final LeagueAbstractFactory factory;
    private final IScoreboard leaguePlayOffScoreboard;
    private List<IGame> playOffGames;

    public PlayOffScheduler(IScoreboard leaguePlayOffScoreboard) {
        Logger logger = Logger.getLogger(PlayOffScheduler.class.getName());
        logger.info("Generating playoff schedule.");
        this.leaguePlayOffScoreboard = leaguePlayOffScoreboard;
        playOffGames = new ArrayList<>();
        factory = LeagueAbstractFactory.instance();
    }

    public void initializePlayOffGames() {
        this.playOffGames = new ArrayList<>();
    }

    public List<IGame> schedule(int totalGamesPerTeam) {
        ILeaguePlayOffScoreboard leagueScoreboard = (ILeaguePlayOffScoreboard) leaguePlayOffScoreboard;
        IPlayOffScoreboard playOffScoreboard = (IPlayOffScoreboard) leaguePlayOffScoreboard;
        if (Objects.isNull(playOffScoreboard.getWinner())) {
            scheduleConferences(totalGamesPerTeam, leagueScoreboard);
        }
        return playOffGames;
    }

    private void scheduleConferences(int totalGamesPerTeam, ILeaguePlayOffScoreboard leagueScoreboard) {
        ArrayList<ITeamOnScoreboard> teams = new ArrayList<>();
        boolean isFinal = false;
        for (IConferencePlayOffScoreboard conference : leagueScoreboard.getConferenceScoreboards()) {
            IPlayOffScoreboard conferencePlayOffScoreboard = (IPlayOffScoreboard) conference;

            if (Objects.isNull(conferencePlayOffScoreboard.getWinner())) {
                scheduleDivisions(totalGamesPerTeam, conference);
            } else {
                teams.add(conferencePlayOffScoreboard.getWinner());
                isFinal = true;
            }
        }
        if (isFinal) {
            IPlayOffSeries playOffSeries = factory.createPlayOffSeries(factory.createGame(teams.get(0), teams.get(1)), totalGamesPerTeam);
            playOffGames.addAll(playOffSeries.getPlayOffSeries());
            IPlayOffScoreboard leaguePlayOff = (IPlayOffScoreboard) leagueScoreboard;
            leaguePlayOff.setFinal();
        }
    }

    private void scheduleDivisions(int totalGamesPerTeam, IConferencePlayOffScoreboard conference) {
        ArrayList<ITeamOnScoreboard> teams = new ArrayList<>();
        boolean isConferenceFinal = false;

        for (IScoreboard division : conference.getDivisionScoreboards()) {
            IPlayOffScoreboard divisionPlayOffScoreboard = (IPlayOffScoreboard) division;

            if (Objects.isNull(divisionPlayOffScoreboard.getWinner())) {
                schedulePlayOffSeries(totalGamesPerTeam, division);
            } else {
                teams.add(divisionPlayOffScoreboard.getWinner());
                isConferenceFinal = true;
            }
        }
        if (isConferenceFinal) {
            IPlayOffSeries playOffSeries = factory.createPlayOffSeries(factory.createGame(teams.get(0), teams.get(1)), totalGamesPerTeam);
            playOffGames.addAll(playOffSeries.getPlayOffSeries());
            IPlayOffScoreboard conferencePlayOff = (IPlayOffScoreboard) conference;
            conferencePlayOff.setFinal();
        }
    }

    private void schedulePlayOffSeries(int totalGamesPerTeam, IScoreboard division) {
        boolean isDivisionFinal = false;

        for (int i = 0, j = division.getScoreboard().size() - 1; i < division.getScoreboard().size() / 2 && j >= division.getScoreboard().size() / 2; i++, j--) {
            TeamOnScoreboard teamOne = division.getScoreboard().get(i);
            TeamOnScoreboard teamTwo = division.getScoreboard().get(j);
            if (teamOne.getScore() == 0) {
                Game game = new Game(teamOne, teamTwo);
                IPlayOffSeries playOffSeries = factory.createPlayOffSeries(game, totalGamesPerTeam);
                playOffGames.addAll(playOffSeries.getPlayOffSeries());
            } else {
                isDivisionFinal = true;
            }
        }
        if (isDivisionFinal) {
            division.sort();
            PlayOffSeries playOffSeries = new PlayOffSeries(factory.createGame(division.getScoreboard().get(0), division.getScoreboard().get(1)), totalGamesPerTeam);
            playOffGames.addAll(playOffSeries.getPlayOffSeries());
            IPlayOffScoreboard divisionPlayOff = (IPlayOffScoreboard) division;
            divisionPlayOff.setFinal();
        }
    }
}
