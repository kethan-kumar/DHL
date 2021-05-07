/* @Author:  Siddhant Ashutosh */
package org.leaguemodel.interfaces;

import java.time.LocalDate;
import java.util.List;

public interface ILeague {
    String getLeagueName();

    void setLeagueName(String leagueName);

    IGameplayConfig getGameplayConfig();

    void setGameplayConfig(IGameplayConfig gameplayConfig);

    List<IConference> getConferences();

    void setConferences(List<IConference> conferences);

    List<IPlayers> getFreeAgents();

    void setFreeAgents(List<IPlayers> freeAgents);

    List<IHeadCoach> getCoaches();

    void setCoaches(List<IHeadCoach> coaches);

    List<IGeneralManager> getGeneralManagers();

    void setGeneralManagers(List<IGeneralManager> generalManagers);

    LocalDate getLeagueDate();

    void setLeagueDate(LocalDate leagueDate);
}
