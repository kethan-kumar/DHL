/* @Author:  Siddhant Ashutosh */
package org.leaguemodel.interfaces;

public interface IPersistence {

    void saveTeam(ILeague league);

    ILeague loadTeam(String leagueName, String teamName, ILeague league);

    ILeague appendTeam(String divisionName, String conferenceName, ITeam team, ILeague league);
}
