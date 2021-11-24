/* @Author: Kethan Kumar */
package org.leaguemodel;

import org.leaguemodel.interfaces.IDivisions;
import org.leaguemodel.interfaces.ITeam;

import java.util.List;

public class Divisions implements IDivisions {

    private String divisionName;
    private List<ITeam> teams;

    public Divisions() {
        this.divisionName = null;
        this.teams = null;
    }

    public Divisions(String divisionName, List<ITeam> teams) {

        this.divisionName = divisionName;
        this.teams = teams;
    }


    public String getDivisionName() {
        return divisionName;
    }


    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }


    public List<ITeam> getTeams() {
        return teams;
    }


    public void setTeams(List<ITeam> teams) {
        this.teams = teams;
    }
}
