/* @Author: Kethan Kumar */

package org.statemachine;

import org.leaguemodel.interfaces.IGeneralManager;
import org.leaguemodel.interfaces.IHeadCoach;
import org.leaguemodel.interfaces.ILeague;
import org.statemachine.interfaces.ICreateTeamParameterObject;

public class CreateTeamParameterObject implements ICreateTeamParameterObject {
    private final String conferenceName;
    private final String divisionName;
    private final String teamName;
    private final IGeneralManager generalManager;
    private final IHeadCoach teamHeadCoach;
    private final ILeague leagueLOM;

    public CreateTeamParameterObject(String conferenceName, String divisionName, String teamName, IGeneralManager generalManager, IHeadCoach teamHeadCoach, ILeague leagueLOM) {
        this.conferenceName = conferenceName;
        this.divisionName = divisionName;
        this.teamName = teamName;
        this.generalManager = generalManager;
        this.teamHeadCoach = teamHeadCoach;
        this.leagueLOM = leagueLOM;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public String getTeamName() {
        return teamName;
    }

    public IGeneralManager getGeneralManager() {
        return generalManager;
    }

    public IHeadCoach getTeamHeadCoach() {
        return teamHeadCoach;
    }

    public ILeague getLeagueLOM() {
        return leagueLOM;
    }
}
