package org.io.interfaces;

import org.leaguemodel.interfaces.ILeague;
import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.ITeam;

import java.util.List;

public interface IDisplayTrade {

    Boolean displayUserTrade(List<IPlayers> weakPlayers, List<IPlayers> strongPlayers);

    int displayFreeAgents(ITeam team, ILeague leagueObj);

    int displayPlayerList(ITeam team);

    void displayAITradeStatus(String askingTeam, String oppositeTeam, Boolean tradeDecision);

    void displayUserTradeStatus(String askingTeam, String oppositeTeam, Boolean tradeDecision);

    void displayTradePickStatus(String askingTeam, String oppositeTeam, Boolean tradeDecision);

    void displayOutOfBound(String postion, String type);

}
