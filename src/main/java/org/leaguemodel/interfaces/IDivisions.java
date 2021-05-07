/* @Author:  Siddhant Ashutosh */
package org.leaguemodel.interfaces;

import java.util.List;

public interface IDivisions {
    String getDivisionName();

    void setDivisionName(String divisionName);

    List<ITeam> getTeams();

    void setTeams(List<ITeam> teams);
}
