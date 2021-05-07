/* @Author: Yash Jaiswal */

package org.trading.interfaces;

import org.leaguemodel.interfaces.ITeam;

public interface ICheckPlayers {

    void captain(ITeam team);

    int goalies(ITeam team);

    int forwards(ITeam team);

    int defense(ITeam team);
}
