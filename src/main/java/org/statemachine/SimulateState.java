/* @Author: Kethan Kumar */

package org.statemachine;

import org.io.abstractfactory.IOAbstractFactory;
import org.io.interfaces.IDisplayLeagueSimulation;
import org.leaguemodel.abstractfactory.LeagueModelAbstractFactory;
import org.leaguemodel.interfaces.ILeague;
import org.leaguemodel.interfaces.IPersistence;
import org.statemachine.abstractfactory.StateAbstractFactory;
import org.statemachine.interfaces.*;

public class SimulateState implements IGameState {
    IContext gameContext;
    int numberOfSeasons;
    ISimulation checkNumberOfSimulations;
    ILeague leagueLOM;
    IPersistence iPersistence;
    IDisplayLeagueSimulation display;
    IGameState trophySystem;
    private StateAbstractFactory stateFactory;
    private IOAbstractFactory ioFactory;
    private LeagueModelAbstractFactory leagueModelAbstractFactory;

    public SimulateState(IContext gameContext, ILeague leagueLOM, int numberOfSeasons) {
        stateFactory = StateAbstractFactory.instance();
        ioFactory = IOAbstractFactory.instance();
        leagueModelAbstractFactory = LeagueModelAbstractFactory.instance();
        this.gameContext = gameContext;
        this.numberOfSeasons = numberOfSeasons;
        this.leagueLOM = leagueLOM;
        display = ioFactory.createDisplayLeagueSimulation();
        checkNumberOfSimulations = stateFactory.createSimulate();
        iPersistence = leagueModelAbstractFactory.createPersistance();
    }

    public void entryProcess() {
        display.displayMessage("Let's begin the simulation ...");
    }

    public void doProcess() {
        for (int i = 0; i < numberOfSeasons; i++) {
            IStateMachine leagueSimulationStateMachine = new LeagueSimulationStateMachine(leagueLOM);
            leagueSimulationStateMachine.runStateMachine();
            display.printSaveSimulationMessage();
            iPersistence.saveTeam(leagueLOM);
            ILeagueDatePersist leagueStateForDate = (ILeagueDatePersist) leagueSimulationStateMachine;
            leagueLOM.setLeagueDate(leagueStateForDate.getCurrentDate());
            trophySystem = stateFactory.createTrophyState(gameContext, leagueStateForDate.getCurrentDate());
            gameContext.stateMachine(trophySystem);
        }
        gameContext.setCurrentState(null);
    }

    public void exitProcess() {
        display.displayMessage("Hockey league simulation ended!");
    }
}
