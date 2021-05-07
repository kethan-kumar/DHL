package org;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.statemachine.abstractfactory.StateAbstractFactory;
import org.statemachine.interfaces.IContext;
import org.statemachine.interfaces.IGameState;

public class Main {
    public static void main(String[] args) {
        String log4jConfigPath = "src/main/conf/log4j.properties";
        PropertyConfigurator.configure(log4jConfigPath);
        Logger logger = Logger.getLogger(Main.class.getName());
        logger.info("Application started.");
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(globalExceptionHandler);

        StateAbstractFactory stateFactory = StateAbstractFactory.instance();

        IContext gameContext = stateFactory.createGameContext();
        if (args.length == 1) {
            IGameState importState = stateFactory.createImportState(gameContext, args);
            gameContext.stateMachine(importState);
        } else {
            IGameState loadState = stateFactory.createLoadTeamState(gameContext);
            gameContext.stateMachine(loadState);
        }
    }
}