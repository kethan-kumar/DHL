package org.statemachine;

import org.io.abstractfactory.IOAbstractFactory;
import org.io.interfaces.IDisplay;
import org.io.interfaces.IInput;
import org.leaguemodel.League;
import org.leaguemodel.Persist;
import org.leaguemodel.interfaces.ILeague;
import org.leaguemodel.interfaces.IPersistence;
import org.statemachine.abstractfactory.StateAbstractFactory;
import org.statemachine.interfaces.IContext;
import org.statemachine.interfaces.IGameState;

public class LoadTeamState implements IGameState {
    private static final String LEAGUENAME = "league name from the list to load";
    private static final String EXISTINGTEAMNAME = "existing team name from last saved data";
    IInput input;
    ILeague leagueLOM;
    IContext gameContext;
    IDisplay display;
    IPersistence iPersistence;
    IGameState playerChoice;
    private String leagueName;
    private String teamName;
    private StateAbstractFactory stateFactory;
    private IOAbstractFactory ioFactory;

    public LoadTeamState(IContext gameContext) {
        stateFactory = StateAbstractFactory.instance();
        ioFactory = IOAbstractFactory.instance();
        this.leagueLOM = new League();
        this.gameContext = gameContext;
        iPersistence = new Persist();
        display = ioFactory.createDisplay();
        input = ioFactory.createInput();
    }

    public void entryProcess() {
        display.displayMessage("Here is the list of leagues:");
        leagueLOM = iPersistence.loadTeam(leagueName, teamName, leagueLOM);
        display.displayLeagueList(leagueLOM);
        leagueName = input.userInput(LEAGUENAME);
        display.displayTeamList(leagueName, leagueLOM);
        teamName = input.userInput(EXISTINGTEAMNAME);
        display.displayMessage("Please wait while we load the team ...");
    }

    public void doProcess() {
        playerChoice = stateFactory.createPlayerChoiceState(gameContext, leagueLOM);
        gameContext.setCurrentState(playerChoice);
    }

    public void exitProcess() {

    }
}
