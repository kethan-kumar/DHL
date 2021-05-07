package org.leaguemodel;

import junit.framework.TestCase;
import org.junit.Test;
import org.leaguemodel.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class AgingEngineTest extends TestCase {

    @Test
    public void testInitAging() {
        IInjurySystem injurySystem = new InjurySystem();
        IGameplayConfig gameplayConfig = new GameplayConfig();
        List<ITeam> teamsList = new ArrayList<>();
        List<IDivisions> divisionList = new ArrayList<>();
        List<IPlayers> playerList = new ArrayList<>();
        List<IPlayers> freeAgents = new ArrayList<>();
        List<IConference> conferenceList = new ArrayList<>();
        IPlayers playerOne = new Players("Forward", "Player one", 25, 27, 15, 18, 12, 10, 11, 1992);
        IPlayers playerTwo = new Players("Forward", "Player one", 25, 27, 15, 18, 12, 5, 10, 1994);
        IPlayers agent1 = new FreeAgents("defense", "A1", 20, 10, 30, 10, 20);
        IPlayers agent2 = new FreeAgents("goalie", "Agent Two", 30, 10, 40, 10, 80);
        playerList.add(playerOne);
        playerList.add(playerTwo);
        freeAgents.add(agent1);
        freeAgents.add(agent2);
        IAging aging = new Aging();
        aging.setAverageRetirementAge(30);
        aging.setMaximumAge(40);
        aging.setStatDecayChance(0);
        gameplayConfig.setAging(aging);
        IAgingSystem agingSystem = new AgingSystem();
        IPlayerReplacement playerReplacement = new PlayerReplacement();
        ITeam teamOne = new Team("Team 12", "AI", null, null, playerList);
        teamsList.add(teamOne);
        IDivisions divisionOne = new Divisions("Atlantic", teamsList);
        divisionList.add(divisionOne);
        IConference conferenceOne = new Conferences("Eastern Conference", divisionList);
        conferenceList.add(conferenceOne);
        ILeague leagueOne = new League("Dalhousie Hockey League", conferenceList, playerList);
        leagueOne.setGameplayConfig(gameplayConfig);
        leagueOne.setFreeAgents(freeAgents);
        IAgingEngine agingEngine = new AgingEngine(injurySystem, aging, agingSystem, playerReplacement);
        agingEngine.initAging(leagueOne, 365);
        assertEquals(leagueOne.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getAge(), 25, 25);
    }
}