/* @Author:  Siddhant Ashutosh */
package org.leaguemodel;

import org.leaguemodel.interfaces.IGeneralManager;
import org.leaguemodel.interfaces.IHeadCoach;
import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.ITeam;

import java.util.List;

public class Team implements ITeam {

    private String teamName;
    private IGeneralManager generalManager;
    private List<IPlayers> players;
    private IHeadCoach headCoach;
    private String teamType;

    public Team() {
        this.teamName = null;
        this.generalManager = null;
        this.headCoach = null;
        this.teamType = "AI";
        players = null;
    }

    public Team(String teamName, String teamType, IGeneralManager generalManager, IHeadCoach headCoach, List<IPlayers> players) {

        this.teamName = teamName;
        this.generalManager = generalManager;
        this.headCoach = headCoach;
        this.teamType = teamType;
        this.players = players;
    }


    public String getTeamName() {
        return teamName;
    }


    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }


    public IGeneralManager getGeneralManager() {
        return generalManager;
    }


    public void setGeneralManager(IGeneralManager generalManager) {
        this.generalManager = generalManager;
    }


    public IHeadCoach getHeadCoach() {
        return headCoach;
    }


    public void setHeadCoach(IHeadCoach headCoach) {
        this.headCoach = headCoach;
    }


    public List<IPlayers> getPlayers() {
        return players;
    }


    public void setPlayers(List<IPlayers> players) {
        this.players = players;
    }

    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }
}
