package org.statemachine;

import org.junit.Test;
import org.leaguemodel.interfaces.IGeneralManager;
import org.leaguemodel.interfaces.IHeadCoach;
import org.leaguemodel.interfaces.IPlayers;
import org.statemachine.interfaces.ICreate;
import org.statemachine.interfaces.ICreateTeamParameterObject;
import resources.MockDataTrading;

import java.util.List;

public class CreateTeamTest {

    @Test
    public void testCreateTeam() {
        MockDataTrading leagueLOM = new MockDataTrading();
        ICreate createTeam = new CreateTeam();
        List<IPlayers> playersList = leagueLOM.leagueOne.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers();
        String conferenceName = leagueLOM.leagueOne.getConferences().get(0).getConferenceName();
        String divisionName = leagueLOM.leagueOne.getConferences().get(0).getDivisions().get(0).getDivisionName();
        String teamName = leagueLOM.leagueOne.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getTeamName();
        IGeneralManager generalManager = leagueLOM.leagueOne.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getGeneralManager();
        IHeadCoach headCoach = leagueLOM.leagueOne.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getHeadCoach();
        ICreateTeamParameterObject ICreateTeamParameterObject = new CreateTeamParameterObject(conferenceName, divisionName, teamName, generalManager, headCoach, leagueLOM.leagueOne);
        createTeam.createTeam(ICreateTeamParameterObject, playersList);
    }
}