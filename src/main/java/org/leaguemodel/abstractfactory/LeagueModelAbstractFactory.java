/* @Author: Kethan Kumar */
package org.leaguemodel.abstractfactory;

import org.leaguemodel.interfaces.*;
import org.leaguesimulation.interfaces.IScoreboard;
import org.statemachine.LeagueDataKeys;
import org.trading.interfaces.IDraftPickSlots;

public abstract class LeagueModelAbstractFactory {

    private static LeagueModelAbstractFactory uniqueInstance = null;

    public static LeagueModelAbstractFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new LeagueModelFactory();
        }
        return uniqueInstance;
    }

    public abstract ICheckStrength createCheckStrength();

    public abstract ITeam createTeam();

    public abstract IAging createAging();

    public abstract IAgingEngine createAgingEngine(IInjurySystem injurySystem, IAging aging, IAgingSystem agingSystem, IPlayerReplacement playerReplacement);

    public abstract IAgingSystem createAgingSystem();

    public abstract IConference createConferences();

    public abstract IDivisions createDivision();

    public abstract IGameplayConfig createGameplayConfig();

    public abstract IGameResolver createGameResolver();

    public abstract IGeneralManager createGeneralManager();

    public abstract IGeneratePlayers createGeneratePlayers();

    public abstract IHeadCoach createHeadCoach();

    public abstract IInjuries createInjuries();

    public abstract IInjurySystem createInjurySystem();

    public abstract ILeague createLeague();

    public abstract IPlayerDraft createPlayerDraft(IScoreboard leagueScoreboard, IScoreboard leaguePlayOff, IDraftPickSlots draftPickSlotsObj);

    public abstract IPlayerReplacement createPlayerReplacement();

    public abstract IPlayers createPlayers();

    public abstract IRosterStatus createRosterStatus(ILeague leagueLOM);

    public abstract ITrading createTrading();

    public abstract ITraining createTraining();

    public abstract LeagueDataKeys createLeagueDatakeys();

    public abstract IValidationsInLeague createValidationsInLeague();

    public abstract IPersistence createPersistance();
}
