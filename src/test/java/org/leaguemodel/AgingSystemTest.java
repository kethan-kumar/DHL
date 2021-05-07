package org.leaguemodel;

import junit.framework.TestCase;
import org.junit.Test;
import org.leaguemodel.interfaces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AgingSystemTest extends TestCase {

    @Test
    public void testRetirementAlgorithm() {
        List<IPlayers> playersList = new ArrayList<>();
        IPlayers players = new Players();
        players.setPosition("goalie");
        players.setSkating(15);
        players.setShooting(18);
        players.setChecking(13);
        players.setSaving(0);
        players.setAge(70);
        players.setPlayerName("team one");
        IPlayers players1 = new Players();
        players1.setPosition("defense");
        players1.setSkating(15);
        players1.setShooting(18);
        players1.setChecking(13);
        players1.setSaving(0);
        players1.setAge(20);
        players1.setPlayerName("team two");
        IPlayers players2 = new Players();
        players2.setPosition("forward");
        players2.setSkating(15);
        players2.setShooting(18);
        players2.setChecking(13);
        players2.setSaving(0);
        players2.setAge(50);
        players2.setPlayerName("team three");
        playersList.add(players);
        playersList.add(players1);
        playersList.add(players2);
        ITeam team = new Team();
        team.setPlayers(playersList);
        IAgingSystem agingSystem = new AgingSystem();
        IAging aging = new Aging();
        aging.setMaximumAge(50);
        aging.setAverageRetirementAge(35);
        Map<String, IPlayers> map;
        List<Object> agingInputList = new ArrayList<>();
        IPlayerReplacement playerReplacement = new PlayerReplacement();
        IInjurySystem injurySystem = new InjurySystem();
        List<IPlayers> freeAgentsList = new ArrayList<>();
        IPlayers freeAgents = new FreeAgents();
        freeAgents.setPlayerName("Player One");
        freeAgents.setSkating(17);
        freeAgents.setShooting(15);
        freeAgents.setChecking(10);
        freeAgents.setSaving(20);
        freeAgents.setAge(25);
        freeAgents.setPosition("forward");
        freeAgentsList.add(freeAgents);
        agingInputList.add(team);
        agingInputList.add(playerReplacement);
        agingInputList.add(freeAgents);
        agingSystem.agePlayer(players, injurySystem, 365, (float) 0.5);
        agingSystem.retireTeamPlayers(agingInputList, freeAgentsList, aging);
        assertEquals(71, (int) players.getAge());
    }

    @Test
    public void testAgeAPlayer() {
        IPlayers players = new Players();
        players.setAge(25);
        players.setSkating(21);
        players.setSaving(20);
        players.setShooting(19);
        players.setChecking(10);
        IAgingSystem agingSystem = new AgingSystem();
        IInjurySystem injurySystem = new InjurySystem();
        agingSystem.agePlayer(players, injurySystem, 365, (float) 0.2);
        assertEquals(26, (int) players.getAge());
    }

    @Test
    public void testAgeAPlayerFalse() {
        IPlayers players = new Players();
        players.setAge(25);
        players.setSkating(21);
        players.setSaving(20);
        players.setShooting(19);
        players.setChecking(10);
        IAgingSystem agingSystem = new AgingSystem();
        IInjurySystem injurySystem = new InjurySystem();
        agingSystem.agePlayer(players, injurySystem, 300, (float) 0.2);
        assertEquals(26, (int) players.getAge());
    }
}