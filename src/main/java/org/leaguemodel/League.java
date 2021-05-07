/* @Author:  Siddhant Ashutosh */
package org.leaguemodel;

import org.leaguemodel.interfaces.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class League implements ILeague {

    private String leagueName;
    private IGameplayConfig gameplayConfig;
    private List<IConference> conferences;
    private List<IPlayers> freeAgents;
    private List<IHeadCoach> coaches;
    private List<IGeneralManager> generalManagers;
    private LocalDate leagueDate;

    public League() {
        this.leagueName = null;
        this.conferences = null;
        this.leagueDate = LocalDate.of(2020, Month.SEPTEMBER, 30);
    }

    public League(String leagueName, List<IConference> conferences, List<IPlayers> freeagent) {
        this.leagueDate = LocalDate.of(2020, Month.SEPTEMBER, 30);
        this.leagueName = leagueName;
        this.conferences = conferences;
    }


    public String getLeagueName() {
        return leagueName;
    }


    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }


    public IGameplayConfig getGameplayConfig() {
        return gameplayConfig;
    }


    public void setGameplayConfig(IGameplayConfig gameplayConfig) {
        this.gameplayConfig = gameplayConfig;
    }


    public List<IConference> getConferences() {
        return conferences;
    }


    public void setConferences(List<IConference> conferences) {
        this.conferences = conferences;
    }


    public List<IPlayers> getFreeAgents() {
        return freeAgents;
    }


    public void setFreeAgents(List<IPlayers> freeAgents) {
        this.freeAgents = freeAgents;
    }


    public List<IHeadCoach> getCoaches() {
        return coaches;
    }


    public void setCoaches(List<IHeadCoach> coaches) {
        this.coaches = coaches;
    }


    public List<IGeneralManager> getGeneralManagers() {
        return generalManagers;
    }


    public void setGeneralManagers(List<IGeneralManager> generalManagers) {
        this.generalManagers = generalManagers;
    }

    public LocalDate getLeagueDate() {
        return leagueDate;
    }

    public void setLeagueDate(LocalDate leagueDate) {
        this.leagueDate = leagueDate;
    }
}
