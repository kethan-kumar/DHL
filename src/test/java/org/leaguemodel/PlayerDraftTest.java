package org.leaguemodel;


import org.junit.Test;
import org.leaguemodel.interfaces.*;
import org.leaguesimulation.LeagueScoreboard;
import org.leaguesimulation.TeamOnScoreboard;
import org.trading.TradingDraftPicks;
import org.trading.interfaces.IDraftPickSlots;
import org.trading.interfaces.ITradingDraftPicks;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlayerDraftTest {
    @Test
    public void draftingTest() {
        ILeague league;
        IConference conference;
        IDivisions division;
        List<ITeam> teams;
        List<IDivisions> divisions;
        List<IPlayers> playerList = new ArrayList<>();
        List<IConference> conferences;
        conference = new Conferences();
        division = new Divisions();
        league = new League();
        ITeam teamOne = new Team();
        ITeam teamTwo = new Team();
        teamOne.setTeamName("teamOne");
        teamTwo.setTeamName("teamTwo");
        IPlayers playerThree = new Players("Goalie", "Player three", true);
        teams = new ArrayList<>();
        teams.add(teamOne);
        teams.add(teamTwo);
        playerList.add(playerThree);
        teamOne.setPlayers(playerList);
        teamTwo.setPlayers(playerList);
        division.setTeams(teams);
        divisions = new ArrayList<>();
        conferences = new ArrayList<>();
        divisions.add(division);
        conference.setDivisions(divisions);
        conferences.add(conference);
        league.setConferences(conferences);
        LeagueScoreboard leagueScoreboard = new LeagueScoreboard(league);
        List<TeamOnScoreboard> scoreboard = leagueScoreboard.loadScoreboard();
        ITradingDraftPicks tradingDraftPicksObj = new TradingDraftPicks();
        IDraftPickSlots draftPickSlotsObj = tradingDraftPicksObj.draftPickSlotsInitializer(league);
        IPlayerDraft playerDraft = new PlayerDraft(null, null, draftPickSlotsObj);
        playerDraft.drafting(scoreboard);
        assertEquals(teamOne.getPlayers().size(), 15);
    }

    @Test
    public void balanceTeamTest() {
        ILeague league;
        IConference conference;
        IDivisions division;
        List<ITeam> teams;
        List<IDivisions> divisions;
        List<IPlayers> playerList = new ArrayList<>();
        List<IConference> conferences;
        conference = new Conferences();
        division = new Divisions();
        league = new League();
        ITeam teamOne = new Team();
        teamOne.setTeamName("teamOne");
        IPlayers playerThree = new Players("Goalie", "Player three", true);
        teams = new ArrayList<>();
        teams.add(teamOne);
        playerList.add(playerThree);
        teamOne.setPlayers(playerList);
        division.setTeams(teams);
        divisions = new ArrayList<>();
        conferences = new ArrayList<>();
        divisions.add(division);
        conference.setDivisions(divisions);
        conferences.add(conference);
        league.setConferences(conferences);
        LeagueScoreboard leagueScoreboard = new LeagueScoreboard(league);
        List<TeamOnScoreboard> scoreboard = leagueScoreboard.loadScoreboard();
        ITradingDraftPicks tradingDraftPicksObj = new TradingDraftPicks();
        IDraftPickSlots draftPickSlotsObj = tradingDraftPicksObj.draftPickSlotsInitializer(league);
        IPlayerDraft playerDraft = new PlayerDraft(null, null, draftPickSlotsObj);
        playerDraft.maintainTeam(scoreboard);
        assertEquals(teamOne.getPlayers().size(), 1);
    }
}