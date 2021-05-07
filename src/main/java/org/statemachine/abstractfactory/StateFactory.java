/* @Author: Kethan Kumar */

package org.statemachine.abstractfactory;

import org.leaguemodel.interfaces.ILeague;
import org.statemachine.*;
import org.statemachine.interfaces.*;

import java.time.LocalDate;

public class StateFactory extends StateAbstractFactory {

    public IGameState createImportState(IContext gameContext, String[] args) {
        return new ImportState(gameContext, args);
    }

    public IGameState createCreateTeamState(IContext gameContext, ILeague leagueLOM) {
        return new CreateTeamState(gameContext, leagueLOM);
    }

    public IGameState createLoadTeamState(IContext gameContext) {
        return new LoadTeamState(gameContext);
    }

    public IGameState createPlayerChoiceState(IContext gameContext, ILeague leagueLOM) {
        return new PlayerChoiceState(gameContext, leagueLOM);
    }

    public IGameState createSimulateState(IContext gameContext, ILeague leagueLOM, int numberOfSeasons) {
        return new SimulateState(gameContext, leagueLOM, numberOfSeasons);
    }

    public IGameState createTrophyState(IContext gameContext, LocalDate leagueDate) {
        return new TrophyState(gameContext, leagueDate);
    }

    public IContext createGameContext() {
        return new GameContext();
    }

    public ICreate createCreateTeam() {
        return new CreateTeam();
    }

    public IValidate createValidation() {
        return new Validation();
    }

    public ITrainingSystem createTrainingSystem() {
        return new TrainingSystem();
    }

    public ISimulation createSimulate() {
        return new Simulate();
    }

    public IImportLeagueData createImportLeagueData() {
        return new ImportLeagueData();
    }

}
