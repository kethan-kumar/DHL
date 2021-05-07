/* @Author: Kethan Kumar */

package org.statemachine.abstractfactory;

import org.leaguemodel.interfaces.ILeague;
import org.statemachine.interfaces.*;

import java.time.LocalDate;

public abstract class StateAbstractFactory {

    private static StateAbstractFactory uniqueInstance = null;

    public static StateAbstractFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new StateFactory();
        }
        return uniqueInstance;
    }

    public static void setFactory(StateFactory stateFactory) {
        uniqueInstance = stateFactory;
    }

    public abstract IContext createGameContext();

    public abstract ICreate createCreateTeam();

    public abstract IValidate createValidation();

    public abstract ISimulation createSimulate();

    public abstract ITrainingSystem createTrainingSystem();

    public abstract IImportLeagueData createImportLeagueData();

    public abstract IGameState createImportState(IContext gameContext, String[] args);

    public abstract IGameState createCreateTeamState(IContext gameContext, ILeague leagueLOM);

    public abstract IGameState createLoadTeamState(IContext gameContext);

    public abstract IGameState createPlayerChoiceState(IContext gameContext, ILeague leagueLOM);

    public abstract IGameState createSimulateState(IContext gameContext, ILeague leagueLOM, int numberOfSeasons);

    public abstract IGameState createTrophyState(IContext gameContext, LocalDate leagueDate);

}
