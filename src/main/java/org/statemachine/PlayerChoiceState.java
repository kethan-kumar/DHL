/* @Author: Kethan Kumar */

package org.statemachine;

import org.io.abstractfactory.IOAbstractFactory;
import org.io.interfaces.IDisplay;
import org.io.interfaces.IInput;
import org.leaguemodel.interfaces.ILeague;
import org.statemachine.abstractfactory.StateAbstractFactory;
import org.statemachine.interfaces.IContext;
import org.statemachine.interfaces.IGameState;
import org.statemachine.interfaces.ISimulation;

public class PlayerChoiceState implements IGameState {
    private static final String SIMULATESEASON = "number of seasons to simulate";
    private static final String SIMULATION_BEGIN = "Simulation begins:";
    private static final String SIMULATION_LET_BEGIN = "Let's begin the simulation ...";
    IContext gameContext;
    String numberOfSeasons;
    ISimulation checkNumberOfSimulations;
    ILeague leagueLOM;
    IDisplay display;
    IInput input;
    IGameState simulateState;
    private StateAbstractFactory stateFactory;
    private IOAbstractFactory ioFactory;

    public PlayerChoiceState(IContext gameContext, ILeague leagueLOM) {
        stateFactory = StateAbstractFactory.instance();
        ioFactory = IOAbstractFactory.instance();
        this.gameContext = gameContext;
        this.leagueLOM = leagueLOM;
        display = ioFactory.createDisplay();
        checkNumberOfSimulations = stateFactory.createSimulate();
        input = ioFactory.createInput();
    }

    public void entryProcess() {
        display.displayMessage(SIMULATION_LET_BEGIN);
    }

    public void doProcess() {
        numberOfSeasons = input.userInput(SIMULATESEASON);
        display.displayMessage(SIMULATION_BEGIN);
        simulateState = stateFactory.createSimulateState(gameContext, leagueLOM, Integer.parseInt(numberOfSeasons));
        gameContext.setCurrentState(simulateState);
    }

    public void exitProcess() {

    }
}
