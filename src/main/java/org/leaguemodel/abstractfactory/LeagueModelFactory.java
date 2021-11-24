/* @Author:  Kethan Kumar */
package org.leaguemodel.abstractfactory;

import org.leaguemodel.*;
import org.leaguemodel.interfaces.*;
import org.leaguesimulation.interfaces.IScoreboard;
import org.statemachine.LeagueDataKeys;
import org.trading.interfaces.IDraftPickSlots;

public class LeagueModelFactory extends LeagueModelAbstractFactory {


    public ICheckStrength createCheckStrength() {
        return new CheckStrength();
    }


    public ITeam createTeam() {
        return new Team();
    }


    public IAging createAging() {
        return new Aging();
    }


    public IAgingEngine createAgingEngine(IInjurySystem injurySystem, IAging aging, IAgingSystem agingSystem, IPlayerReplacement playerReplacement) {
        return new AgingEngine(injurySystem, aging, agingSystem, playerReplacement);
    }


    public IAgingSystem createAgingSystem() {
        return new AgingSystem();
    }


    public IConference createConferences() {
        return new Conferences();
    }


    public IDivisions createDivision() {
        return new Divisions();
    }


    public IGameplayConfig createGameplayConfig() {
        return new GameplayConfig();
    }


    public IGameResolver createGameResolver() {
        return new GameResolver();
    }


    public IGeneralManager createGeneralManager() {
        return new GeneralManager();
    }


    public IGeneratePlayers createGeneratePlayers() {
        return new GeneratePlayers();
    }


    public IHeadCoach createHeadCoach() {
        return new HeadCoach();
    }


    public IInjuries createInjuries() {
        return new Injuries();
    }


    public IInjurySystem createInjurySystem() {
        return new InjurySystem();
    }


    public ILeague createLeague() {
        return new League();
    }


    public IPlayerDraft createPlayerDraft(IScoreboard leagueScoreboard, IScoreboard leaguePlayOff, IDraftPickSlots draftPickSlotsObj) {
        return new PlayerDraft(leagueScoreboard, leaguePlayOff, draftPickSlotsObj);
    }


    public IPlayerReplacement createPlayerReplacement() {
        return new PlayerReplacement();
    }


    public IPlayers createPlayers() {
        return new Players();
    }


    public IRosterStatus createRosterStatus(ILeague leagueLOM) {
        return new RosterStatus(leagueLOM);
    }


    public ITrading createTrading() {
        return new Trading();
    }


    public ITraining createTraining() {
        return new Training();
    }

    public LeagueDataKeys createLeagueDatakeys() {
        return new LeagueDataKeys();
    }


    public IValidationsInLeague createValidationsInLeague() {
        return new ValidationsInLeague();
    }

    public IPersistence createPersistance() {
        return new Persist();
    }
}
