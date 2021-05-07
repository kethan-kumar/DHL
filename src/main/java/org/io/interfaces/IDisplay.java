package org.io.interfaces;

import org.leaguemodel.interfaces.ILeague;

public interface IDisplay {

    void displayLeagueList(ILeague league);

    void displayTeamList(String leagueName, ILeague league);

    void displayTeam(ILeague league);

    void displayGeneralManagerList(ILeague league);

    void displayHeadCoach(ILeague league);

    void displayFreeAgents(ILeague league);

    void displayMessage(String message);

}
