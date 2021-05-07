package org.statemachine.interfaces;

import org.leaguemodel.interfaces.IGeneralManager;
import org.leaguemodel.interfaces.IHeadCoach;
import org.leaguemodel.interfaces.ILeague;

public interface ICreateTeamParameterObject {
    String getConferenceName();

    String getDivisionName();

    String getTeamName();

    IGeneralManager getGeneralManager();

    IHeadCoach getTeamHeadCoach();

    ILeague getLeagueLOM();
}
