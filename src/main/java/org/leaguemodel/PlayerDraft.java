/* @Author:  Siddhant Ashutosh */
package org.leaguemodel;

import org.apache.log4j.Logger;
import org.io.abstractfactory.IOAbstractFactory;
import org.io.interfaces.IDisplay;
import org.leaguemodel.abstractfactory.LeagueModelAbstractFactory;
import org.leaguemodel.interfaces.IGeneratePlayers;
import org.leaguemodel.interfaces.IPlayerDraft;
import org.leaguemodel.interfaces.IPlayers;
import org.leaguesimulation.TeamOnScoreboard;
import org.leaguesimulation.interfaces.IScoreboard;
import org.trading.interfaces.IDraftPickSlots;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlayerDraft implements IPlayerDraft {
    private IScoreboard leagueScoreboard;
    private IScoreboard leaguePlayOff;
    private IDraftPickSlots draftPickSlotsObj;
    private LeagueModelAbstractFactory leagueModelAbstractFactory;
    private Logger logger;
    private IOAbstractFactory ioFactory;
    private IDisplay display;

    public PlayerDraft(IScoreboard leagueScoreboard, IScoreboard leaguePlayOff, IDraftPickSlots draftPickSlotsObj) {
        ioFactory = IOAbstractFactory.instance();
        leagueModelAbstractFactory = LeagueModelAbstractFactory.instance();
        this.leagueScoreboard = leagueScoreboard;
        this.leaguePlayOff = leaguePlayOff;
        this.draftPickSlotsObj = draftPickSlotsObj;
        logger = Logger.getLogger(PlayerDraft.class.getName());
        display = ioFactory.createDisplay();
    }

    public void startDrafting() {
        sortTeam();
    }

    public void sortTeam() {
        logger.debug("team sort for player draft");
        List<TeamOnScoreboard> standings = leagueScoreboard.getScoreboard();
        Comparator<TeamOnScoreboard> scoreBoard = new Comparator<TeamOnScoreboard>() {

            public int compare(TeamOnScoreboard o1, TeamOnScoreboard o2) {
                if (o1.getScore() > o2.getScore()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
        Collections.sort(standings, scoreBoard);
        int teamSize = standings.size();
        int n = teamSize - 16;
        List<TeamOnScoreboard> draftList = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            draftList.add(standings.get(j));
        }
        List<TeamOnScoreboard> playoffStandings = leaguePlayOff.getScoreboard();
        Comparator<TeamOnScoreboard> playoffScoreBoard = new Comparator<TeamOnScoreboard>() {

            public int compare(TeamOnScoreboard o1, TeamOnScoreboard o2) {
                if (o1.getScore() > o2.getScore()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
        Collections.sort(playoffStandings, playoffScoreBoard);
        playoffStandings.addAll(draftList);
        drafting(playoffStandings);

    }

    public void drafting(List<TeamOnScoreboard> teamsList) {
        IGeneratePlayers players = leagueModelAbstractFactory.createGeneratePlayers();
        List<Players> newPlayersList = players.generate(teamsList.size() * 7 + 1);
        for (int i = 0; i < 7; i++) {
            draftPickSlotsObj.getDraftPickSlots();
            for (TeamOnScoreboard teamOnScoreboard : teamsList) {
                List<IPlayers> teamPlayersList = teamOnScoreboard.getTeam().getPlayers();
                teamPlayersList.add(newPlayersList.get(0));
                teamOnScoreboard.getTeam().setPlayers(teamPlayersList);
                logger.debug("Player drafted to team:" + teamOnScoreboard.getTeam().getTeamName());
                display.displayMessage("Player drafted to team:" + teamOnScoreboard.getTeam().getTeamName());
                newPlayersList.remove(0);
            }
        }
        maintainTeam(teamsList);
    }

    public void maintainTeam(List<TeamOnScoreboard> teamsList) {
        List<IPlayers> additionalPlayers = new ArrayList<>();
        for (TeamOnScoreboard teamOnScoreboard : teamsList) {
            int forwardCount = 0;
            int defenseCount = 0;
            int goalieCount = 0;
            List<IPlayers> teamPlayersList = teamOnScoreboard.getTeam().getPlayers();
            if (teamPlayersList.size() > 30) {
                for (int i = 0; i < teamPlayersList.size(); i++) {
                    switch (teamPlayersList.get(i).getPosition()) {
                        case "forward":
                            forwardCount++;
                            if (forwardCount > 16) {
                                additionalPlayers.add(teamPlayersList.get(i));
                                teamPlayersList.remove(teamPlayersList.get(i));
                            }
                            break;
                        case "defense":
                            defenseCount++;
                            if (defenseCount > 10) {
                                additionalPlayers.add(teamPlayersList.get(i));
                                teamPlayersList.remove(teamPlayersList.get(i));
                            }
                            break;
                        case "goalie":
                            goalieCount++;
                            if (goalieCount > 4) {
                                additionalPlayers.add(teamPlayersList.get(i));
                                teamPlayersList.remove(teamPlayersList.get(i));
                            }
                            break;
                    }
                }
            }
        }
    }
}
