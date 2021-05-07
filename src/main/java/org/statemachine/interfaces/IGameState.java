package org.statemachine.interfaces;

public interface IGameState {
    void entryProcess();

    void doProcess();

    void exitProcess();
}
