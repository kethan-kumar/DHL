package org.statemachine.interfaces;


import org.leaguemodel.interfaces.ILeague;
import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.ITeam;

import java.util.List;

public interface ICreate {
    ITeam createTeam(ICreateTeamParameterObject ICreateTeamParameterObject, List<IPlayers> playersList);

    List<IPlayers> chooseTeamPlayers(ILeague league);
}
