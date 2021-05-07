/* @Author: Yash Jaiswal */

package org.trading;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.io.DisplayTrade;
import org.io.interfaces.IDisplayTrade;
import org.leaguemodel.FreeAgents;
import org.leaguemodel.Players;
import org.leaguemodel.interfaces.ILeague;
import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.ITeam;
import org.trading.abstractfactory.TradingAbstractFactory;
import org.trading.interfaces.IBalance;
import org.trading.interfaces.ICheckPlayers;

import java.util.Collections;
import java.util.List;

public class Balance implements IBalance {
    static final int CONST_TEAM_SIZE_NUM = 30;
    static final int CONST_FORWARD_SIZE_NUM = 16;
    static final int CONST_DEFENSE_SIZE_NUM = 10;
    static final int CONST_GOALIE_SIZE_NUM = 4;
    static final int CONST_ZERO = 0;
    private final TradingAbstractFactory tradingFactory;
    private final Logger logger;
    public Balance() {
        logger = Logger.getLogger(Balance.class.getName());
        tradingFactory = TradingAbstractFactory.instance();
    }

    public void balanceTeams(ITeam team, ILeague leagueObj) {
        logger.debug("Balancing team's roster");
        int count = 0;
        ICheckPlayers check = tradingFactory.createCheckPlayers();
        IDisplayTrade display = new DisplayTrade();
        for (int i = 0; i < leagueObj.getFreeAgents().size(); i++) {
            leagueObj.getFreeAgents().sort(tradingFactory.createStatsComparator());
        }
        if (StringUtils.equalsIgnoreCase(team.getTeamType(), enumTeamType.AI.toString())) {
            logger.debug("Balancing AI team");
            boolean goaliesNotBalanced = true;
            boolean forwardsNotBalanced = true;
            boolean defenseNotBalanced = true;
            boolean start = true;
            if (check.goalies(team) == CONST_GOALIE_SIZE_NUM && check.forwards(team) == CONST_FORWARD_SIZE_NUM && check.defense(team) == CONST_DEFENSE_SIZE_NUM) {
                start = false;
            }
            if (start) {
                while ((goaliesNotBalanced && forwardsNotBalanced && defenseNotBalanced) && (count < 100)) {
                    int countGoalie = check.goalies(team);
                    int countForwards = check.forwards(team);
                    int countDefense = check.defense(team);
                    count++;
                    if (countGoalie < CONST_GOALIE_SIZE_NUM) {
                        addFromFreeAgent(team, leagueObj, enumPosition.GOALIE.toString(), CONST_GOALIE_SIZE_NUM - countGoalie);
                        countGoalie = CONST_GOALIE_SIZE_NUM;
                    }
                    if (countForwards < CONST_FORWARD_SIZE_NUM) {
                        addFromFreeAgent(team, leagueObj, enumPosition.FORWARD.toString(), CONST_FORWARD_SIZE_NUM - countForwards);
                        countForwards = CONST_FORWARD_SIZE_NUM;
                    }
                    if (countDefense < CONST_DEFENSE_SIZE_NUM) {
                        addFromFreeAgent(team, leagueObj, enumPosition.DEFENSE.toString(), CONST_DEFENSE_SIZE_NUM - countDefense);
                        countDefense = CONST_DEFENSE_SIZE_NUM;
                    }
                    if (countGoalie > CONST_GOALIE_SIZE_NUM) {
                        removeFromTeam(team, leagueObj, enumPosition.GOALIE.toString(), countGoalie);
                    }
                    if (countForwards > CONST_FORWARD_SIZE_NUM) {
                        removeFromTeam(team, leagueObj, enumPosition.FORWARD.toString(), countForwards);
                    }
                    if (countDefense > CONST_DEFENSE_SIZE_NUM) {
                        removeFromTeam(team, leagueObj, enumPosition.DEFENSE.toString(), countDefense);
                    }
                    if (check.goalies(team) == CONST_GOALIE_SIZE_NUM && check.forwards(team) == CONST_FORWARD_SIZE_NUM && check.defense(team) == CONST_DEFENSE_SIZE_NUM) {
                        goaliesNotBalanced = false;
                        forwardsNotBalanced = false;
                        defenseNotBalanced = false;
                    }
                }
            }
        }
        if (StringUtils.equalsIgnoreCase(team.getTeamType(), enumTeamType.USER.toString())) {
            logger.debug("Balancing User team");
            boolean goaliesNotBalanced = true;
            boolean forwardsNotBalanced = true;
            boolean defenseNotBalanced = true;
            boolean start = true;
            if (check.goalies(team) == CONST_GOALIE_SIZE_NUM && check.forwards(team) == CONST_FORWARD_SIZE_NUM && check.defense(team) == CONST_DEFENSE_SIZE_NUM) {
                start = false;
            }
            if (start) {
                while (goaliesNotBalanced && forwardsNotBalanced && defenseNotBalanced) {
                    if (team.getPlayers().size() <= CONST_TEAM_SIZE_NUM) {
                        Collections.sort(team.getPlayers(), tradingFactory.createStatsComparator());
                        int freeAgentIndex = display.displayFreeAgents(team, leagueObj);
                        boolean flagAdd = true;
                        if (check.goalies(team) == CONST_GOALIE_SIZE_NUM && StringUtils.equalsIgnoreCase(leagueObj.getFreeAgents().get(freeAgentIndex - 1).getPosition(), enumPosition.GOALIE.toString())) {
                            display.displayOutOfBound(enumPosition.GOALIE.toString(), enumPickType.ADD.toString());
                            flagAdd = false;
                        }
                        if (check.forwards(team) == CONST_FORWARD_SIZE_NUM && StringUtils.equalsIgnoreCase(leagueObj.getFreeAgents().get(freeAgentIndex - 1).getPosition(), enumPosition.FORWARD.toString())) {
                            display.displayOutOfBound(enumPosition.FORWARD.toString(), enumPickType.ADD.toString());
                            flagAdd = false;
                        }
                        if (check.defense(team) == CONST_DEFENSE_SIZE_NUM && StringUtils.equalsIgnoreCase(leagueObj.getFreeAgents().get(freeAgentIndex - 1).getPosition(), enumPosition.DEFENSE.toString())) {
                            display.displayOutOfBound(enumPosition.DEFENSE.toString(), enumPickType.ADD.toString());
                            flagAdd = false;
                        }
                        if (flagAdd) {
                            addUserTeam(team, leagueObj, freeAgentIndex - 1);
                        }
                    }
                    if (team.getPlayers().size() >= CONST_TEAM_SIZE_NUM) {
                        Collections.sort(team.getPlayers(), tradingFactory.createStatsComparator());
                        int playerDropIndex = display.displayPlayerList(team);
                        boolean flagDrop = true;
                        if (check.goalies(team) == CONST_GOALIE_SIZE_NUM && StringUtils.equalsIgnoreCase(team.getPlayers().get(playerDropIndex - 1).getPosition(), enumPosition.GOALIE.toString())) {
                            display.displayOutOfBound(enumPosition.GOALIE.toString(), enumPickType.DROP.toString());
                            flagDrop = false;
                        }
                        if (check.forwards(team) == CONST_FORWARD_SIZE_NUM && StringUtils.equalsIgnoreCase(team.getPlayers().get(playerDropIndex - 1).getPosition(), enumPosition.FORWARD.toString())) {
                            display.displayOutOfBound(enumPosition.FORWARD.toString(), enumPickType.DROP.toString());
                            flagDrop = false;
                        }
                        if (check.defense(team) == CONST_DEFENSE_SIZE_NUM && StringUtils.equalsIgnoreCase(team.getPlayers().get(playerDropIndex - 1).getPosition(), enumPosition.DEFENSE.toString())) {
                            display.displayOutOfBound(enumPosition.DEFENSE.toString(), enumPickType.DROP.toString());
                            flagDrop = false;
                        }
                        if (flagDrop) {
                            removeUserTeam(team, leagueObj, playerDropIndex - 1);
                        }
                    }
                    if (check.goalies(team) == CONST_GOALIE_SIZE_NUM && check.forwards(team) == CONST_FORWARD_SIZE_NUM && check.defense(team) == CONST_DEFENSE_SIZE_NUM) {
                        goaliesNotBalanced = false;
                        forwardsNotBalanced = false;
                        defenseNotBalanced = false;
                    }
                }
            }
        }
    }

    public void addFromFreeAgent(ITeam team, ILeague leagueObj, String playerPosition, int count) {
        List<IPlayers> fr = leagueObj.getFreeAgents();
        IPlayers players = new Players();
        List<IPlayers> pl = team.getPlayers();

        for (int j = 0; j < fr.size(); j++) {
            if (StringUtils.equalsIgnoreCase(fr.get(j).getPosition(), playerPosition) && count > CONST_ZERO) {
                players.setPlayerName(fr.get(j).getPlayerName());
                players.setPosition(fr.get(j).getPosition());
                players.setIsCaptain(false);
                players.setAge(fr.get(j).getAge());
                players.setSkating(fr.get(j).getSkating());
                players.setShooting(fr.get(j).getShooting());
                players.setChecking(fr.get(j).getChecking());
                players.setSaving(fr.get(j).getSaving());
                players.setBirthDay(fr.get(j).getBirthDay());
                players.setBirthMonth(fr.get(j).getBirthMonth());
                players.setBirthYear(fr.get(j).getBirthYear());
                pl.add(players);
                fr.remove(fr.get(j));
                team.setPlayers(pl);
                count--;
            }
        }
    }

    public void removeFromTeam(ITeam team, ILeague leagueObj, String playerPosition, int count) {

        List<IPlayers> pl = team.getPlayers();
        IPlayers fr = new FreeAgents();
        List<IPlayers> frList = leagueObj.getFreeAgents();
        for (int j = 0; j < pl.size(); j++) {
            if ((StringUtils.equalsIgnoreCase(pl.get(j).getPosition(), playerPosition) && count > CONST_FORWARD_SIZE_NUM) ||
                    (StringUtils.equalsIgnoreCase(pl.get(j).getPosition(), playerPosition) && count > CONST_DEFENSE_SIZE_NUM) ||
                    (StringUtils.equalsIgnoreCase(pl.get(j).getPosition(), playerPosition) && count > CONST_GOALIE_SIZE_NUM)
                            && team.getPlayers().size() > CONST_TEAM_SIZE_NUM) {
                fr.setPlayerName(pl.get(j).getPlayerName());
                fr.setPosition(pl.get(j).getPosition());
                fr.setAge(pl.get(j).getAge());
                fr.setSkating(pl.get(j).getSkating());
                fr.setShooting(pl.get(j).getShooting());
                fr.setChecking(pl.get(j).getChecking());
                fr.setSaving(pl.get(j).getSaving());
                fr.setBirthDay(pl.get(j).getBirthDay());
                fr.setBirthMonth(pl.get(j).getBirthMonth());
                fr.setBirthYear(pl.get(j).getBirthYear());
                frList.add(fr);
                pl.remove(pl.get(j));
                leagueObj.setFreeAgents(frList);
                count--;
            }
        }
    }

    public void addUserTeam(ITeam team, ILeague leagueObj, int freeAgentNum) {
        List<IPlayers> fr = leagueObj.getFreeAgents();
        IPlayers players = new Players();
        List<IPlayers> pl = team.getPlayers();
        players.setPlayerName(fr.get(freeAgentNum).getPlayerName());
        players.setPosition(fr.get(freeAgentNum).getPosition());
        players.setIsCaptain(false);
        players.setAge(fr.get(freeAgentNum).getAge());
        players.setSkating(fr.get(freeAgentNum).getSkating());
        players.setShooting(fr.get(freeAgentNum).getShooting());
        players.setChecking(fr.get(freeAgentNum).getChecking());
        players.setSaving(fr.get(freeAgentNum).getSaving());
        players.setBirthDay(fr.get(freeAgentNum).getBirthDay());
        players.setBirthMonth(fr.get(freeAgentNum).getBirthMonth());
        players.setBirthYear(fr.get(freeAgentNum).getBirthYear());

        pl.add(players);
        fr.remove(fr.get(freeAgentNum));
        team.setPlayers(pl);
    }

    public void removeUserTeam(ITeam team, ILeague leagueObj, int playerNum) {
        List<IPlayers> pl = team.getPlayers();
        IPlayers fr = new FreeAgents();
        List<IPlayers> frList = leagueObj.getFreeAgents();
        fr.setPlayerName(pl.get(playerNum).getPlayerName());
        fr.setPosition(pl.get(playerNum).getPosition());
        fr.setAge(pl.get(playerNum).getAge());
        fr.setSkating(pl.get(playerNum).getSkating());
        fr.setShooting(pl.get(playerNum).getShooting());
        fr.setChecking(pl.get(playerNum).getChecking());
        fr.setSaving(pl.get(playerNum).getSaving());
        fr.setBirthDay(pl.get(playerNum).getBirthDay());
        fr.setBirthMonth(pl.get(playerNum).getBirthMonth());
        fr.setBirthYear(pl.get(playerNum).getBirthYear());
        frList.add(fr);
        pl.remove(pl.get(playerNum));
        leagueObj.setFreeAgents(frList);
    }

    enum enumPosition {
        GOALIE, FORWARD, DEFENSE
    }

    enum enumTeamType {
        AI, USER
    }

    enum enumPickType {
        ADD, DROP
    }

}
