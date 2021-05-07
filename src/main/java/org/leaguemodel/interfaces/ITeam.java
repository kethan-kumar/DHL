/* @Author:  Siddhant Ashutosh */
package org.leaguemodel.interfaces;

import java.util.List;

public interface ITeam {
    String getTeamName();

    void setTeamName(String teamName);

    IGeneralManager getGeneralManager();

    void setGeneralManager(IGeneralManager generalManager);

    IHeadCoach getHeadCoach();

    void setHeadCoach(IHeadCoach headCoach);

    List<IPlayers> getPlayers();

    void setPlayers(List<IPlayers> players);

    String getTeamType();

    void setTeamType(String teamType);
}
