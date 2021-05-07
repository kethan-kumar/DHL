package org.leaguemodel;

import junit.framework.TestCase;
import org.junit.Test;
import org.leaguemodel.interfaces.IPlayerReplacement;
import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.ITeam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerReplacementTest extends TestCase {

    @Test
    public void testReplacePlayers() {
        List<IPlayers> freeAgentsList = new ArrayList<>();
        IPlayers freeAgents = new FreeAgents();
        freeAgents.setPlayerName("Player One");
        freeAgents.setSkating(17);
        freeAgents.setShooting(15);
        freeAgents.setChecking(10);
        freeAgents.setSaving(20);
        freeAgents.setAge(25);
        freeAgents.setPosition("forward");
        IPlayers freeAgents1 = new FreeAgents();
        freeAgents1.setPlayerName("Player two");
        freeAgents1.setSkating(13);
        freeAgents1.setShooting(16);
        freeAgents1.setChecking(11);
        freeAgents1.setSaving(19);
        freeAgents1.setAge(24);
        freeAgents1.setPosition("defense");
        IPlayers freeAgents2 = new FreeAgents();
        freeAgents2.setPlayerName("Player Three");
        freeAgents2.setSkating(5);
        freeAgents2.setShooting(17);
        freeAgents2.setChecking(30);
        freeAgents2.setSaving(18);
        freeAgents2.setAge(26);
        freeAgents2.setPosition("goalie");
        IPlayers freeAgents3 = new FreeAgents();
        freeAgents3.setPlayerName("Player Four");
        freeAgents3.setSkating(5);
        freeAgents3.setShooting(17);
        freeAgents3.setChecking(30);
        freeAgents3.setSaving(18);
        freeAgents3.setAge(26);
        freeAgents3.setPosition("goalie");
        freeAgentsList.add(freeAgents);
        freeAgentsList.add(freeAgents1);
        freeAgentsList.add(freeAgents2);
        freeAgentsList.add(freeAgents3);
        Map<String, IPlayers> playersToRetire = new HashMap<>();
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
        playersToRetire.put(players.getPlayerName(), players);
        playersToRetire.put(players1.getPlayerName(), players1);
        playersToRetire.put(players2.getPlayerName(), players2);
        playersList.add(players);
        playersList.add(players1);
        playersList.add(players2);
        ITeam team = new Team();
        team.setPlayers(playersList);
        IPlayerReplacement playerReplacement = new PlayerReplacement();
        playerReplacement.replacePlayers(team, freeAgentsList);
        assertEquals("team one", team.getPlayers().get(0).getPlayerName());
    }
}