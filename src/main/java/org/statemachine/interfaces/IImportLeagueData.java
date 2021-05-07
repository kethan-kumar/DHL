package org.statemachine.interfaces;

import org.leaguemodel.interfaces.ILeague;

public interface IImportLeagueData {
    ILeague loadLeagueMemory(String jsonFileLocation);

    boolean leagueJSONValidation(String[] args, String jsonSchemaLocation);
}
