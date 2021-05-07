/* @Author:  Siddhant Ashutosh */
package org.leaguemodel.interfaces;

public interface IInjurySystem {
    void checkTeamInjuries(ITeam team, IInjuries injuries);

    boolean isPlayerInjured(IInjuries injuries, IPlayers players);

    void isPlayerRecovered(IPlayers players);
}
