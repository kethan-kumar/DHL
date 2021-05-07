/* @Author: Vikram Singh */

package org.leaguesimulation;

import org.apache.log4j.Logger;
import org.leaguesimulation.abstractfactory.LeagueAbstractFactory;
import org.leaguesimulation.interfaces.IGame;
import org.leaguesimulation.interfaces.IScheduler;
import org.leaguesimulation.interfaces.IScoreboard;
import org.leaguesimulation.interfaces.ITeamOnScoreboard;

import java.util.ArrayList;
import java.util.List;

public class RegularSeasonScheduler implements IScheduler {
    private final LeagueAbstractFactory factory;
    List<IGame> listOfGames;
    IScoreboard leagueScoreboard;
    Logger logger;

    public RegularSeasonScheduler(IScoreboard leagueScoreboard) {
        this.listOfGames = new ArrayList<>();
        this.leagueScoreboard = leagueScoreboard;
        factory = LeagueAbstractFactory.instance();
        logger = Logger.getLogger(RegularSeasonScheduler.class.getName());
    }

    public List<IGame> schedule(int totalGamesPerTeam) {
        for (int i = 0; i < repeatGames(totalGamesPerTeam); i++) {
            for (TeamOnScoreboard teamOnScoreboardOne : leagueScoreboard.getScoreboard()) {

                for (ITeamOnScoreboard teamOnScoreboardTwo : leagueScoreboard.getScoreboard()) {
                    if (teamOnScoreboardOne.equals(teamOnScoreboardTwo)) {
                        logger.debug("Regular season match scheduled");
                    } else {
                        listOfGames.add(factory.createGame(teamOnScoreboardOne, teamOnScoreboardTwo));
                    }
                }

            }
        }
        return listOfGames;
    }

    private int repeatGames(int totalGamesPerTeam) {
        int returnValue = totalGamesPerTeam / leagueScoreboard.getScoreboard().size();
        return returnValue == 0 ? 1 : returnValue;
    }

}
