package org.statemachine.interfaces;

public interface IContext {
    void setCurrentState(IGameState currentState);

    void stateMachine(IGameState initialState);

    void run();

    void changeState(IGameState nextState);
}
