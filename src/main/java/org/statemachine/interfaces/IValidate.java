package org.statemachine.interfaces;

import org.leaguemodel.interfaces.IGeneralManager;
import org.leaguemodel.interfaces.IHeadCoach;
import org.leaguemodel.interfaces.ILeague;

public interface IValidate {

    boolean validateLOM(ILeague leagueLOM);

    IGeneralManager validateGeneralManager(ILeague leagueOne, String generalManager);

    IHeadCoach validateHeadCoach(ILeague leagueLOM, String headCoach);

    boolean validateString(String userInput);

    boolean schemaValidation(String leagueData, String jsonSchema);
}
