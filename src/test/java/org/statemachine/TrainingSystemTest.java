package org.statemachine;

import junit.framework.TestCase;
import org.junit.Test;
import org.leaguemodel.HeadCoach;
import org.leaguemodel.Injuries;
import org.leaguemodel.Players;
import org.leaguemodel.Team;
import org.leaguemodel.interfaces.IHeadCoach;
import org.leaguemodel.interfaces.IInjuries;
import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.ITeam;
import org.statemachine.interfaces.ITrainingSystem;

import java.util.ArrayList;
import java.util.List;

public class TrainingSystemTest extends TestCase {

    @Test
    public void testCheckStatistics() {
        ITrainingSystem training = new TrainingSystem();
        IInjuries injury = new Injuries();
        ITeam team = new Team();
        List<IPlayers> playersList = new ArrayList<>();
        IPlayers forwardPlayers = new Players();
        forwardPlayers.setPosition("forward");
        forwardPlayers.setPlayerName("HelloB");
        forwardPlayers.setSkating(15);
        forwardPlayers.setShooting(18);
        forwardPlayers.setChecking(13);
        forwardPlayers.setSaving(0);
        playersList.add(forwardPlayers);
        team.setPlayers(playersList);
        IHeadCoach coachA = new HeadCoach();
        coachA.setName("A");
        coachA.setChecking(2.4);
        coachA.setSaving(3.1);
        coachA.setShooting(2.2);
        coachA.setSkating(2.5);
        team.setHeadCoach(coachA);
        training.checkStatistics(injury, team);
        assertEquals((double) team.getPlayers().get(0).getSkating(), 16.0);
    }

    @Test
    public void testCheckSkatingStatistics() {
        TrainingSystem training = new TrainingSystem();
        IInjuries injury = new Injuries();
        ITeam team = new Team();
        List<IPlayers> playersList = new ArrayList<>();
        IPlayers forwardPlayers = new Players();
        forwardPlayers.setPlayerName("HelloR");
        forwardPlayers.setPosition("forward");
        forwardPlayers.setSkating(15);
        forwardPlayers.setShooting(18);
        forwardPlayers.setChecking(13);
        forwardPlayers.setSaving(0);
        playersList.add(forwardPlayers);
        team.setPlayers(playersList);
        IHeadCoach coachA = new HeadCoach();
        coachA.setName("A");
        coachA.setChecking(2.4);
        coachA.setSaving(3.1);
        coachA.setShooting(2.2);
        coachA.setSkating(2.5);
        team.setHeadCoach(coachA);
        training.checkSkatingStatistics(coachA.getSkating(), forwardPlayers, null);
        assertEquals((double) team.getPlayers().get(0).getSkating(), 16.0);
    }

    @Test
    public void testCheckCheckingStatistics() {
        TrainingSystem training = new TrainingSystem();
        IInjuries injury = new Injuries();
        ITeam team = new Team();
        List<IPlayers> playersList = new ArrayList<>();
        IPlayers forwardPlayers = new Players();
        forwardPlayers.setPosition("forward");
        forwardPlayers.setPlayerName("HelloS");
        forwardPlayers.setSkating(15);
        forwardPlayers.setShooting(18);
        forwardPlayers.setChecking(13);
        forwardPlayers.setSaving(0);
        playersList.add(forwardPlayers);
        team.setPlayers(playersList);
        IHeadCoach coachA = new HeadCoach();
        coachA.setName("A");
        coachA.setChecking(2.4);
        coachA.setSaving(3.1);
        coachA.setShooting(2.2);
        coachA.setSkating(2.5);
        team.setHeadCoach(coachA);
        training.checkCheckingStatistics(coachA.getChecking(), forwardPlayers, null);
        assertEquals((double) team.getPlayers().get(0).getChecking(), 14.0);
    }

    @Test
    public void testCheckSavingStatistics() {
        TrainingSystem training = new TrainingSystem();
        IInjuries injury = new Injuries();
        ITeam team = new Team();
        List<IPlayers> playersList = new ArrayList<>();
        IPlayers forwardPlayers = new Players();
        forwardPlayers.setPosition("forward");
        forwardPlayers.setPlayerName("HelloT");
        forwardPlayers.setSkating(15);
        forwardPlayers.setShooting(18);
        forwardPlayers.setChecking(13);
        forwardPlayers.setSaving(0);
        playersList.add(forwardPlayers);
        team.setPlayers(playersList);
        IHeadCoach coachA = new HeadCoach();
        coachA.setName("A");
        coachA.setChecking(2.4);
        coachA.setSaving(3.1);
        coachA.setShooting(2.2);
        coachA.setSkating(2.5);
        team.setHeadCoach(coachA);
        training.checkSavingStatistics(coachA.getSkating(), forwardPlayers, null);
        assertEquals((double) team.getPlayers().get(0).getSaving(), 1.0);
    }

    @Test
    public void testCheckShootingStatistics() {
        TrainingSystem training = new TrainingSystem();
        IInjuries injury = new Injuries();
        ITeam team = new Team();
        List<IPlayers> playersList = new ArrayList<>();
        IPlayers forwardPlayers = new Players();
        forwardPlayers.setPosition("forward");
        forwardPlayers.setPlayerName("HelloV");
        forwardPlayers.setSkating(15);
        forwardPlayers.setShooting(18);
        forwardPlayers.setChecking(13);
        forwardPlayers.setSaving(0);
        playersList.add(forwardPlayers);
        team.setPlayers(playersList);
        IHeadCoach coachA = new HeadCoach();
        coachA.setName("A");
        coachA.setChecking(2.4);
        coachA.setSaving(3.1);
        coachA.setShooting(2.2);
        coachA.setSkating(2.5);
        team.setHeadCoach(coachA);
        training.checkShootingStatistics(coachA.getSkating(), forwardPlayers, null);
        assertEquals((double) team.getPlayers().get(0).getShooting(), 19.0);
    }
}