/* @Author: Yash Jaiswal */

package org.trading;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.io.abstractfactory.IOAbstractFactory;
import org.io.interfaces.IDisplayTrade;
import org.leaguemodel.abstractfactory.LeagueModelAbstractFactory;
import org.leaguemodel.interfaces.*;
import org.trading.abstractfactory.TradingAbstractFactory;
import org.trading.interfaces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TradingTeam implements ITradingTeam {
    private final TradingAbstractFactory tradingFactory;
    private final IOAbstractFactory ioFactory;
    private final LeagueModelAbstractFactory leagueFactory;
    private final Logger logger;

    public TradingTeam() {
        logger = Logger.getLogger(TradingTeam.class.getName());
        tradingFactory = TradingAbstractFactory.instance();
        ioFactory = IOAbstractFactory.instance();
        leagueFactory = LeagueModelAbstractFactory.instance();
    }

    public void tradingTeamPlayers(ITeam askingTeam, ILeague leagueOne, ITrading tradingConfig, IDraftPickSlots draftPickSlots) {
        ArrayList<ITeam> universalTeamList = new ArrayList<>();
        List<IPlayers> askingplayerList;
        List<IPlayers> weakPlayers;
        String strongestTeam = null;
        double strength;
        double maxStrength = 0;
        for (int i = 0; i < leagueOne.getConferences().size(); i++) {
            for (int j = 0; j < leagueOne.getConferences().get(i).getDivisions().size(); j++) {
                universalTeamList.addAll(leagueOne.getConferences().get(i).getDivisions().get(j).getTeams());
            }
        }
        for (ITeam iTeam : universalTeamList) {
            iTeam.getPlayers().sort(tradingFactory.createStatsComparator());
        }
        askingplayerList = askingTeam.getPlayers();
        weakPlayers = askingplayerList.subList(askingplayerList.size() - tradingConfig.getMaxPlayersPerTrade(), askingplayerList.size());
        for (ITeam iTeam : universalTeamList) {
            if (StringUtils.equalsIgnoreCase(askingTeam.getTeamName(), iTeam.getTeamName())) {
                continue;
            } else {
                for (int j = 0; j < iTeam.getPlayers().size(); j++) {
                    for (int k = 0; k < weakPlayers.size(); k++) {
                        if (StringUtils.equalsIgnoreCase(weakPlayers.get(weakPlayers.size() - 1).getPosition(), iTeam.getPlayers().get(j).getPosition())) {
                            ICheckStrength checkStrength = leagueFactory.createCheckStrength();
                            strength = checkStrength.playerStrength((IStrength) iTeam.getPlayers().get(j));
                            if (strength > maxStrength) {
                                maxStrength = strength;
                                strongestTeam = iTeam.getTeamName();
                            }
                        }
                    }
                }
            }
        }
        logger.debug("Checking for strongest team to trade");
        for (int i = 0; i < universalTeamList.size(); i++) {
            if (StringUtils.equalsIgnoreCase(universalTeamList.get(i).getTeamName(), strongestTeam)) {
                if (StringUtils.equalsIgnoreCase(askingTeam.getTeamType(), enumTeamType.AI.toString())) {
                    tradeAttempt(askingTeam, universalTeamList.get(i), tradingConfig, leagueOne, draftPickSlots);
                    logger.debug("Trade for Team: " + askingTeam.getTeamName() + " Negotiated");
                    break;
                }
            }
        }
    }

    public void tradeAttempt(ITeam askingTeam, ITeam oppositeTeam, ITrading tradeConfig, ILeague leagueOne, IDraftPickSlots draftPickSlots) {
        List<IPlayers> askingplayerList;
        List<IPlayers> oppositeplayerList;
        List<IPlayers> finalaskingplayerList = new ArrayList<>();
        List<IPlayers> finaloppositeplayerList = new ArrayList<>();
        List<IPlayers> weakPlayers;
        List<IPlayers> strongPlayers;
        List<IPlayers> tempweakPlayers;
        List<IPlayers> tempstrongPlayers;
        Random random = new Random();
        askingplayerList = askingTeam.getPlayers();
        int numberOfPlayersOffering = random.nextInt(tradeConfig.getMaxPlayersPerTrade());
        tempweakPlayers = askingplayerList.subList(askingplayerList.size() - numberOfPlayersOffering, askingplayerList.size());
        weakPlayers = tempweakPlayers;
        oppositeplayerList = oppositeTeam.getPlayers();
        int numberOfPlayersAsking = random.nextInt(tradeConfig.getMaxPlayersPerTrade());
        tempstrongPlayers = oppositeplayerList.subList(0, numberOfPlayersAsking);
        strongPlayers = tempstrongPlayers;
        ITradeDecision tradeAIDecision = tradingFactory.createTradeDecision();
        ITradeDecision trdUserDecision = tradingFactory.createTradeDecision();
        IBalance balance = tradingFactory.createBalance();
        ICheckPlayers check = tradingFactory.createCheckPlayers();
        IDisplayTrade display = ioFactory.createDisplayTrade();
        ITradingDraftPicks tradeDraftPicksObj = tradingFactory.createTradingDraftPicks();
        if (numberOfPlayersAsking == 0 || numberOfPlayersOffering == 0) {
            if (numberOfPlayersAsking == 0) {
                tradeDraftPicksObj.tradeDrafts(askingTeam, oppositeTeam, tradeConfig, draftPickSlots.getDraftPickSlots(), leagueOne);
            } else if (numberOfPlayersOffering == 0) {
                tradeDraftPicksObj.tradeDrafts(oppositeTeam, askingTeam, tradeConfig, draftPickSlots.getDraftPickSlots(), leagueOne);
            }
        } else {
            boolean tradeDecision = false;
            String teamTypeDecision = null;
            if (StringUtils.equalsIgnoreCase(askingTeam.getTeamType(), enumTeamType.AI.toString()) &&
                    StringUtils.equalsIgnoreCase(oppositeTeam.getTeamType(), enumTeamType.USER.toString())) {
                tradeDecision = trdUserDecision.tradeUserDecision(weakPlayers, strongPlayers);
                teamTypeDecision = "User";

            }
            if (StringUtils.equalsIgnoreCase(askingTeam.getTeamType(), enumTeamType.AI.toString()) &&
                    StringUtils.equalsIgnoreCase(oppositeTeam.getTeamType(), enumTeamType.AI.toString())) {
                tradeDecision = tradeAIDecision.tradeAIDecision(tradeConfig.getRandomAcceptanceChance(), oppositeTeam.getGeneralManager().getPersonality(), tradeConfig.getGmTable());
                teamTypeDecision = "AI";
            }
            if (tradeDecision) {
                finalaskingplayerList.addAll(strongPlayers);
                finaloppositeplayerList.addAll(weakPlayers);
                askingplayerList.removeAll(weakPlayers);
                oppositeplayerList.removeAll(strongPlayers);
                finalaskingplayerList.addAll(askingplayerList);
                finaloppositeplayerList.addAll(oppositeplayerList);
                askingTeam.setPlayers(finalaskingplayerList);
                oppositeTeam.setPlayers(finaloppositeplayerList);
                balance.balanceTeams(askingTeam, leagueOne);
                balance.balanceTeams(oppositeTeam, leagueOne);
                check.captain(askingTeam);
                check.captain(oppositeTeam);
            }
            if (StringUtils.equalsIgnoreCase(teamTypeDecision, "User")) {
                display.displayUserTradeStatus(askingTeam.getTeamName(), oppositeTeam.getTeamName(), tradeDecision);
            }
            if (StringUtils.equalsIgnoreCase(teamTypeDecision, "AI")) {
                display.displayAITradeStatus(askingTeam.getTeamName(), oppositeTeam.getTeamName(), tradeDecision);
            }
        }

    }

    enum enumTeamType {
        AI, USER
    }
}
