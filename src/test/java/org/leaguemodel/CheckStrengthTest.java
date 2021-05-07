package org.leaguemodel;

import junit.framework.TestCase;
import org.junit.Test;
import org.leaguemodel.interfaces.ICheckStrength;
import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.IStrength;
import org.leaguemodel.interfaces.ITeam;

import java.util.ArrayList;
import java.util.List;

public class CheckStrengthTest extends TestCase {
    ICheckStrength iCheckStrength = new CheckStrength();

    @Test
    public void testTeamStrength() {
        ITeam team = new Team();
        IPlayers forwardPlayers = new Players();
        IPlayers defensePlayers = new Players();
        IPlayers goaliePlayers = new Players();
        forwardPlayers.setPosition("forward");
        forwardPlayers.setSkating(15);
        forwardPlayers.setShooting(18);
        forwardPlayers.setChecking(13);
        forwardPlayers.setSaving(0);
        defensePlayers.setPosition("defense");
        defensePlayers.setSkating(15);
        defensePlayers.setShooting(19);
        defensePlayers.setChecking(13);
        defensePlayers.setSaving(0);
        goaliePlayers.setPosition("goalie");
        goaliePlayers.setSkating(15);
        goaliePlayers.setShooting(18);
        goaliePlayers.setChecking(12);
        goaliePlayers.setSaving(18);
        List<IPlayers> playersList = new ArrayList<>();
        playersList.add(forwardPlayers);
        playersList.add(defensePlayers);
        playersList.add(goaliePlayers);
        team.setPlayers(playersList);
        assertEquals(110.0, iCheckStrength.teamStrength(team));
    }

    @Test
    public void testForwardPlayerStrength() {
        IPlayers players = new Players();
        players.setPosition("forward");
        players.setSkating(15);
        players.setShooting(18);
        players.setChecking(13);
        players.setSaving(0);
        assertEquals(39.5, iCheckStrength.playerStrength((IStrength) players));
    }

    @Test
    public void testDefensePlayerStrength() {
        IPlayers players = new Players();
        players.setPosition("defense");
        players.setSkating(15);
        players.setShooting(19);
        players.setChecking(13);
        players.setSaving(0);
        assertEquals(37.5, iCheckStrength.playerStrength((IStrength) players));
    }

    @Test
    public void testGoaliePlayerStrength() {
        IPlayers players = new Players();
        players.setPosition("goalie");
        players.setSkating(15);
        players.setShooting(18);
        players.setChecking(12);
        players.setSaving(18);
        assertEquals(33.0, iCheckStrength.playerStrength((IStrength) players));
    }
}