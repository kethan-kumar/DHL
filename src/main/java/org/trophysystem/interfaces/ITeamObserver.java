/* @Author: Kethan Kumar */

package org.trophysystem.interfaces;

import org.leaguesimulation.interfaces.IScoreboard;

public interface ITeamObserver {
    void update(IScoreboard leagueScoreboard);

    ITrophyNominees getAwardTrophy();
}
