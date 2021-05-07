/* @Author: Kethan Kumar */

package org.statemachine;

import org.io.abstractfactory.IOAbstractFactory;
import org.io.interfaces.IDisplay;
import org.leaguemodel.interfaces.ILeague;
import org.statemachine.abstractfactory.StateAbstractFactory;
import org.statemachine.interfaces.IContext;
import org.statemachine.interfaces.IGameState;
import org.statemachine.interfaces.IImportLeagueData;

public class ImportState implements IGameState {
    private static final String jsonSchemaLocation = "/Users/vikrams/IdeaProjects/Hockey-League/G12-Hockey/src/main/conf/schema.json";
    IContext gameContext;
    IDisplay display;
    IImportLeagueData importLeagueData;
    IGameState createTeamState;
    ILeague leagueLOM;
    private String leagueJsonFile;
    private String[] args;
    private StateAbstractFactory stateFactory;
    private IOAbstractFactory ioFactory;

    public ImportState(IContext gameContext, String[] args) {
        stateFactory = StateAbstractFactory.instance();
        ioFactory = IOAbstractFactory.instance();
        leagueJsonFile = args[0];
        this.args = args;
        this.gameContext = gameContext;
        this.importLeagueData = stateFactory.createImportLeagueData();
        display = ioFactory.createDisplay();
    }

    public void entryProcess() {
        boolean validationStatus = importLeagueData.leagueJSONValidation(args, jsonSchemaLocation);
        if (validationStatus) {
            display.displayMessage("Imported league JSON validation is successfully");
        } else {
            display.displayMessage("Aborted due to incorrect values in JSON!");
            System.exit(1);
        }
    }

    public void doProcess() {
        leagueLOM = importLeagueData.loadLeagueMemory(leagueJsonFile);
        createTeamState = stateFactory.createCreateTeamState(gameContext, leagueLOM);
        gameContext.setCurrentState(createTeamState);
    }

    public void exitProcess() {

    }
}
