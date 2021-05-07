/* @Author: Vikram Singh */

package org.leaguesimulation;

import org.junit.Before;
import org.junit.Test;
import org.leaguemodel.Team;
import org.leaguemodel.interfaces.ITeam;

import static org.junit.Assert.assertEquals;

public class TeamOnScoreboardTest {

    private final int score = 10;
    private ITeam team;

    @Before
    public void setUp() {
        team = new Team();
    }

    @Test
    public void getScore() {
        TeamOnScoreboard teamOnScoreboard = new TeamOnScoreboard(new Team());
        teamOnScoreboard.setScore(10);
        int returnedScore = teamOnScoreboard.getScore();
        assertEquals(score, returnedScore);
    }

    @Test
    public void setScore() {
        TeamOnScoreboard testTeam = new TeamOnScoreboard(team);
        testTeam.setScore(20);
        assertEquals(20, testTeam.getScore());
    }

    @Test
    public void compareTo() {
        TeamOnScoreboard teamOnScoreboard = new TeamOnScoreboard(new Team());
        teamOnScoreboard.setScore(10);
        int compare = teamOnScoreboard.compareTo(new TeamOnScoreboard(team));
        assertEquals(-10, compare);
    }

    @Test
    public void getTeam() {
        ITeam team = new Team();
        team.setTeamName("one");
        TeamOnScoreboard teamOnScoreboard = new TeamOnScoreboard(team);
        assertEquals("one", teamOnScoreboard.getTeam().getTeamName());
    }

    @Test
    public void setTeam() {
        ITeam team = new Team();
        team.setTeamName("one");
        TeamOnScoreboard teamOnScoreboard = new TeamOnScoreboard(team);
        teamOnScoreboard.setTeam(team);
        assertEquals("one", teamOnScoreboard.getTeam().getTeamName());
    }

    @Test
    public void setLossPoints() {
        TeamOnScoreboard teamOnScoreboard = new TeamOnScoreboard(team);
        teamOnScoreboard.setLossPointsForTrade(10);
        assertEquals(10, teamOnScoreboard.getLossPointsForTrade());
    }
}
