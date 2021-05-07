/* @Author: Yash Jaiswal */

package org.trading;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.io.abstractfactory.IOAbstractFactory;
import org.io.interfaces.IDisplayTrade;
import org.leaguemodel.interfaces.ILeague;
import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.ITeam;
import org.leaguemodel.interfaces.ITrading;
import org.trading.abstractfactory.TradingAbstractFactory;
import org.trading.interfaces.*;

import java.util.*;

public class TradingDraftPicks implements ITradingDraftPicks {
    private final TradingAbstractFactory tradingFactory;
    private final IOAbstractFactory ioFactory;
    private final Logger logger;

    public TradingDraftPicks() {
        logger = Logger.getLogger(TradingDraftPicks.class.getName());
        tradingFactory = TradingAbstractFactory.instance();
        ioFactory = IOAbstractFactory.instance();
    }

    public void tradeDrafts(ITeam teamOfferingSlot, ITeam teamOfferingPlayers, ITrading tradeConfig, Map<String, Integer> draftPickSlots, ILeague leagueOne) {
        logger.info("Trading Draft Picks");
        logger.debug("Initiating draft picks Trading");
        List<IPlayers> askingplayerList;
        List<IPlayers> oppositeplayerList;
        List<IPlayers> finalaskingplayerList = new ArrayList<>();
        List<IPlayers> finaloppositeplayerList = new ArrayList<>();
        List<IPlayers> strongPlayers;
        List<IPlayers> tempstrongPlayers;
        Random random = new Random();
        askingplayerList = teamOfferingSlot.getPlayers();
        oppositeplayerList = teamOfferingPlayers.getPlayers();
        int numberOfPlayersAsking = random.nextInt(tradeConfig.getMaxPlayersPerTrade());
        if (numberOfPlayersAsking == 0) {
            numberOfPlayersAsking += 1;
        }
        tempstrongPlayers = oppositeplayerList.subList(0, numberOfPlayersAsking);
        strongPlayers = tempstrongPlayers;
        ITradeDecision tradeAIDecision = tradingFactory.createTradeDecision();
        IBalance balance = tradingFactory.createBalance();
        ICheckPlayers check = tradingFactory.createCheckPlayers();
        IDisplayTrade display = ioFactory.createDisplayTrade();
        boolean tradeDecision = false;
        tradeDecision = tradeAIDecision.tradeAIDecision(tradeConfig.getRandomAcceptanceChance(), teamOfferingPlayers.getGeneralManager().getPersonality(), tradeConfig.getGmTable());
        if (tradeDecision) {
            boolean tradePickExecuted = false;
            for (Map.Entry<String, Integer> mapEntry : draftPickSlots.entrySet()) {
                if (StringUtils.equalsIgnoreCase(mapEntry.getKey(), teamOfferingSlot.getTeamName())) {
                    int numOfSlotsAvailable = mapEntry.getValue();
                    if (numOfSlotsAvailable > 0) {
                        mapEntry.setValue(numOfSlotsAvailable - 1);
                        tradePickExecuted = true;
                    }
                }
            }
            if (tradePickExecuted) {
                finalaskingplayerList.addAll(strongPlayers);
                oppositeplayerList.removeAll(strongPlayers);
                finalaskingplayerList.addAll(askingplayerList);
                finaloppositeplayerList.addAll(oppositeplayerList);
                teamOfferingSlot.setPlayers(finalaskingplayerList);
                teamOfferingPlayers.setPlayers(finaloppositeplayerList);
                balance.balanceTeams(teamOfferingSlot, leagueOne);
                balance.balanceTeams(teamOfferingPlayers, leagueOne);
                check.captain(teamOfferingSlot);
                check.captain(teamOfferingPlayers);
                for (Map.Entry<String, Integer> mapEntry : draftPickSlots.entrySet()) {
                    if (StringUtils.equalsIgnoreCase(mapEntry.getKey(), teamOfferingPlayers.getTeamName())) {
                        int numOfSlotsAvailable = mapEntry.getValue();
                        if (numOfSlotsAvailable > 0) {
                            mapEntry.setValue(numOfSlotsAvailable + 1);
                        }
                    }
                }
                display.displayTradePickStatus(teamOfferingSlot.getTeamName(), teamOfferingPlayers.getTeamName(), true);
            } else {
                display.displayTradePickStatus(teamOfferingSlot.getTeamName(), teamOfferingPlayers.getTeamName(), false);
            }
        }
    }

    public IDraftPickSlots draftPickSlotsInitializer(ILeague leagueObj) {
        IDraftPickSlots draftPickSlotsInitializer = tradingFactory.createDraftPickSlots();
        Map<String, Integer> draftPickSlotsTemp = new HashMap<>();
        for (int i = 0; i < leagueObj.getConferences().size(); i++) {
            for (int j = 0; j < leagueObj.getConferences().get(i).getDivisions().size(); j++) {
                for (int k = 0; k < leagueObj.getConferences().get(i).getDivisions().get(j).getTeams().size(); k++) {
                    String teamName = leagueObj.getConferences().get(i).getDivisions().get(j).getTeams().get(k).getTeamName();
                    draftPickSlotsTemp.put(teamName, 7);
                }
            }
        }
        draftPickSlotsInitializer.setDraftPickSlots(draftPickSlotsTemp);
        return draftPickSlotsInitializer;
    }

}
