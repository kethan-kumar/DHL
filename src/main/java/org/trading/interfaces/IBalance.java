/* @Author: Yash Jaiswal */

package org.trading.interfaces;

import org.leaguemodel.interfaces.ILeague;
import org.leaguemodel.interfaces.ITeam;

public interface IBalance {

    void balanceTeams(ITeam team, ILeague leagueOne);

    void addFromFreeAgent(ITeam team, ILeague leagueOne, String playerPosition, int count);

    void removeFromTeam(ITeam team, ILeague leagueOne, String playerPosition, int count);

    void addUserTeam(ITeam team, ILeague leagueOne, int freeAgentNum);

    void removeUserTeam(ITeam team, ILeague leagueOne, int playerNum);

}
