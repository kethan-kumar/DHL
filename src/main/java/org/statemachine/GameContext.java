/* @Author: Kethan Kumar */

package org.statemachine;

import org.statemachine.interfaces.IContext;
import org.statemachine.interfaces.IGameState;

public class GameContext implements IContext {

    private IGameState currentState;
    private IGameState previousState;

    public GameContext() {
        currentState = null;
    }

    public void setCurrentState(IGameState currentState) {
        this.currentState = currentState;
    }

    public void stateMachine(IGameState initialState) {
        currentState = initialState;
        currentState.entryProcess();
        this.run();
    }

    public void run() {
        while ((currentState == null) == false) {
            previousState = currentState;
            currentState.doProcess();
            IGameState nextState = currentState;
            if (((previousState == nextState) == false) && ((nextState == null) == false)) {
                changeState(nextState);
            }
        }
    }

    public void changeState(IGameState nextState) {
        previousState.exitProcess();
        currentState = nextState;
        currentState.entryProcess();
    }
}
