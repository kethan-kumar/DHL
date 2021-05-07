package resources;

import org.leaguemodel.*;
import org.leaguemodel.interfaces.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockData implements ILeague, IPersistence {

    public IPlayers player1 = new Players("Forward", "1", 20, 20, 28, 10, 2);
    public IPlayers player2 = new Players("Forward", "2", 20, 25, 22, 12, 3);
    public IPlayers player3 = new Players("Forward", "3", 20, 22, 29, 15, 4);
    public IPlayers player4 = new Players("Forward", "4", 20, 25, 25, 10, 5);
    public IPlayers player5 = new Players("Forward", "5", 20, 20, 28, 10, 2);
    public IPlayers player6 = new Players("Forward", "6", 20, 25, 22, 12, 3);
    public IPlayers player7 = new Players("Forward", "7", 20, 22, 29, 15, 4);
    public IPlayers player8 = new Players("Forward", "8", 20, 25, 25, 10, 5);
    public IPlayers player9 = new Players("Forward", "9", 20, 20, 28, 10, 2);
    public IPlayers player10 = new Players("Forward", "10", 20, 25, 22, 12, 3);
    public IPlayers player11 = new Players("Forward", "11", 20, 22, 29, 15, 4);
    public IPlayers player12 = new Players("Forward", "12", 20, 25, 25, 10, 5);
    public IPlayers player13 = new Players("Defense", "13", 20, 17, 15, 26, 17);
    public IPlayers player14 = new Players("Defense", "14", 20, 14, 18, 29, 22);
    public IPlayers player15 = new Players("Defense", "15", 20, 15, 10, 25, 24);
    public IPlayers player16 = new Players("Defense", "16", 20, 11, 15, 28, 25);
    public IPlayers player17 = new Players("Defense", "17", 20, 17, 15, 26, 17);
    public IPlayers player18 = new Players("Defense", "18", 20, 14, 18, 29, 22);
    public IPlayers player19 = new Players("Goalie", "19", 20, 10, 5, 29, 30);
    public IPlayers player20 = new Players("Goalie", "20", 20, 9, 7, 29, 30);
    public List<IPlayers> playerList = new ArrayList<>();
    public List<ITeam> teamsList = new ArrayList<>();
    public List<IDivisions> divisionList = new ArrayList<>();
    public List<IDivisions> divisionListTwo = new ArrayList<>();
    public List<IConference> conferenceList = new ArrayList<>();
    public List<IGeneralManager> managersList = new ArrayList<>();
    public IHeadCoach headCoach = new HeadCoach();
    public IGeneralManager generalManager = new GeneralManager();
    public ITeam teamOne = new Team();
    public Divisions divisionOne = new Divisions();
    public IConference conferenceOne = new Conferences();
    public ILeague leagueOne;
    public List<IPlayers> freeAgents = new ArrayList<>();
    public IGameplayConfig gameConfig = new GameplayConfig();

    public MockData() {
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);
        playerList.add(player5);
        playerList.add(player6);
        playerList.add(player7);
        playerList.add(player8);
        playerList.add(player9);
        playerList.add(player10);
        playerList.add(player11);
        playerList.add(player12);
        playerList.add(player13);
        playerList.add(player14);
        playerList.add(player15);
        playerList.add(player16);
        playerList.add(player17);
        playerList.add(player18);
        playerList.add(player19);
        playerList.add(player20);

        headCoach.setName("HeadCoach");
        headCoach.setChecking(2.4);
        headCoach.setSaving(3.1);
        headCoach.setShooting(1.2);
        headCoach.setSkating(0.5);

        IGeneralManager generalManager = new GeneralManager();
        generalManager.setName("generalGM");
        generalManager.setPersonality("shrewd");
        ITeam teamOne = new Team("Team 12", "AI", generalManager, headCoach, playerList);
        teamsList.add(teamOne);
        IDivisions divisionOne = new Divisions("Atlantic", teamsList);
        divisionList.add(divisionOne);
        IDivisions divisionTwo = new Divisions("Metro", teamsList);
        divisionList.add(divisionTwo);
        IDivisions divisionThree = new Divisions("Pacific", teamsList);
        divisionListTwo.add(divisionThree);
        IDivisions divisionFour = new Divisions("Central", teamsList);
        divisionListTwo.add(divisionFour);
        IConference conferenceOne = new Conferences("Eastern Conference", divisionList);
        conferenceList.add(conferenceOne);
        IConference conferenceTwo = new Conferences("Western Conference", divisionListTwo);
        conferenceList.add(conferenceOne);
        conferenceList.add(conferenceTwo);
        leagueOne = new League("Dalhousie Hockey League", conferenceList, playerList);

        GeneralManager generalManager1 = new GeneralManager();
        generalManager1.setName("Siddhant");
        generalManager1.setPersonality("gambler");

        GeneralManager generalManager2 = new GeneralManager();
        generalManager2.setName("Vikram");
        generalManager2.setPersonality("shrewd");

        GeneralManager generalManager3 = new GeneralManager();
        generalManager3.setName("Kethan");
        generalManager3.setPersonality("normal");

        Coach coachA = new Coach();
        coachA.setName("A");
        coachA.setChecking(2.4);
        coachA.setSaving(3.1);
        coachA.setShooting(1.2);
        coachA.setSkating(0.5);

        IHeadCoach coachB = new Coach();
        coachB.setName("B");
        coachB.setChecking(1.3);
        coachB.setSaving(1.4);
        coachB.setShooting(8.2);
        coachB.setSkating(4.5);

        List<IHeadCoach> coachList = new ArrayList<>();
        coachList.add(coachA);
        coachList.add(coachB);

        List<IGeneralManager> managersList = new ArrayList<>();
        managersList.add(generalManager1);
        managersList.add(generalManager2);
        managersList.add(generalManager3);

        ITraining training = new Training();
        training.setDaysUntilStatIncreaseCheck(0);
        gameConfig.setTraining(training);
        IAging age = new Aging();
        age.setAverageRetirementAge(0);
        age.setMaximumAge(0);
        gameConfig.setAging(age);
        IGameResolver resolver = new GameResolver();
        resolver.setPenaltyChance(0.0);
        resolver.setShotToGoalChance(0.0);
        resolver.setPenaltyToGoalChance(0.0);
        IInjuries injury = new Injuries();
        injury.setInjuryDaysHigh(0);
        injury.setInjuryDaysLow(0);
        injury.setRandomInjuryChance(0.0);
        ITrading trading = new Trading();
        trading.setLossPoint(0);
        trading.setMaxPlayersPerTrade(0);
        trading.setRandomAcceptanceChance(0.0);
        trading.setRandomTradeOfferChance(0.0);
        Map<String, Double> gmTable = new HashMap<>();
        gmTable.put("shrewd", -0.1);
        gmTable.put("gambler", 0.1);
        gmTable.put("normal", 0.0);
        trading.setGmTable(gmTable);
        gameConfig.setInjuries(injury);
        gameConfig.setGameResolver(resolver);
        gameConfig.setTrading(trading);
        leagueOne.setGameplayConfig(gameConfig);

        IPlayers agent1 = new FreeAgents("defence", "A1", 20, 10, 30, 10, 20);
        IPlayers agent2 = new FreeAgents("goalie", "A2", 30, 10, 40, 10, 80);
        IPlayers agent3 = new FreeAgents("forward", "A3", 20, 10, 30, 10, 20);
        IPlayers agent4 = new FreeAgents("goalie", "A4", 20, 60, 40, 10, 80);
        IPlayers agent5 = new FreeAgents("defence", "A5", 10, 10, 30, 10, 20);
        IPlayers agent6 = new FreeAgents("goalie", "A6", 20, 10, 40, 10, 80);
        IPlayers agent7 = new FreeAgents("defence", "A7", 20, 10, 30, 10, 20);
        IPlayers agent8 = new FreeAgents("defence", "A8", 20, 70, 40, 10, 80);
        IPlayers agent9 = new FreeAgents("defence", "A9", 10, 10, 30, 10, 20);
        IPlayers agent10 = new FreeAgents("defence", "A10", 20, 10, 90, 10, 80);
        IPlayers agent11 = new FreeAgents("defence", "A11", 20, 10, 30, 10, 20);
        IPlayers agent12 = new FreeAgents("defence", "A12", 20, 10, 40, 60, 80);
        IPlayers agent13 = new FreeAgents("defence", "A13", 20, 30, 30, 10, 20);
        IPlayers agent14 = new FreeAgents("defence", "A14", 20, 10, 40, 10, 80);
        IPlayers agent15 = new FreeAgents("defence", "A15", 20, 10, 30, 10, 20);
        IPlayers agent16 = new FreeAgents("defence", "A16", 80, 10, 40, 10, 80);
        IPlayers agent17 = new FreeAgents("defence", "A17", 20, 10, 90, 10, 20);
        IPlayers agent18 = new FreeAgents("defence", "A18", 20, 10, 40, 10, 80);
        IPlayers agent19 = new FreeAgents("defence", "A19", 20, 10, 30, 10, 20);
        IPlayers agent20 = new FreeAgents("defence", "A20", 40, 10, 40, 10, 80);
        IPlayers agent21 = new FreeAgents("defence", "A21", 20, 10, 50, 10, 20);
        IPlayers agent22 = new FreeAgents("defence", "A22", 30, 10, 40, 10, 80);
        IPlayers agent23 = new FreeAgents("defence", "A23", 20, 10, 30, 10, 20);
        IPlayers agent24 = new FreeAgents("defence", "A24", 20, 0, 40, 10, 80);
        IPlayers agent25 = new FreeAgents("defence", "A25", 20, 30, 30, 10, 20);
        IPlayers agent26 = new FreeAgents("goalie", "A26", 20, 10, 40, 10, 80);
        freeAgents.add(agent1);
        freeAgents.add(agent2);
        freeAgents.add(agent3);
        freeAgents.add(agent4);
        freeAgents.add(agent5);
        freeAgents.add(agent6);
        freeAgents.add(agent7);
        freeAgents.add(agent8);
        freeAgents.add(agent9);
        freeAgents.add(agent10);
        freeAgents.add(agent11);
        freeAgents.add(agent12);
        freeAgents.add(agent13);
        freeAgents.add(agent14);
        freeAgents.add(agent15);
        freeAgents.add(agent16);
        freeAgents.add(agent17);
        freeAgents.add(agent18);
        freeAgents.add(agent19);
        freeAgents.add(agent20);
        freeAgents.add(agent21);
        freeAgents.add(agent22);
        freeAgents.add(agent23);
        freeAgents.add(agent24);
        freeAgents.add(agent25);
        freeAgents.add(agent26);

        leagueOne.setFreeAgents(freeAgents);
        leagueOne.setGeneralManagers(managersList);
        leagueOne.setCoaches(coachList);
        leagueOne.setFreeAgents(freeAgents);
    }

    public ArrayList<HashMap<String, Object>> getLeagueNameDB() {
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("league_name", leagueOne.getLeagueName());
        list.add(map);
        return list;
    }

    public List<HashMap<String, Object>> getTeamNameDB(String leagueName) {
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("team_name", leagueOne.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getTeamName());
        list.add(map);
        return list;
    }

    public void setPlayerData(List<Object> playerList) {
        IPlayers agent1 = new FreeAgents("defence", "A1", 20, 10, 30, 10, 20);
    }

    public List<HashMap<String, Object>> getTeamName(String leagueName) {
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put(leagueOne.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getTeamName(), null);
        list.add(map);
        return list;
    }


    public void saveTeam(ILeague league) {

    }


    public ILeague loadTeam(String leagueName, String teamName, ILeague league) {
        MockData mockData = new MockData();
        mockData.leagueOne.setLeagueName("Dalhousie Hockey League");
        return mockData.leagueOne;
    }


    public ILeague appendTeam(String divisionName, String conferenceName, ITeam team, ILeague league) {
        return null;
    }


    public String getLeagueName() {
        return leagueOne.getLeagueName();
    }


    public void setLeagueName(String leagueName) {

    }


    public IGameplayConfig getGameplayConfig() {
        return null;
    }


    public void setGameplayConfig(IGameplayConfig gameplayConfig) {

    }


    public List<IConference> getConferences() {
        return null;
    }


    public void setConferences(List<IConference> conferences) {

    }


    public List<IPlayers> getFreeAgents() {
        return null;
    }


    public void setFreeAgents(List<IPlayers> freeAgents) {

    }


    public List<IHeadCoach> getCoaches() {
        return leagueOne.getCoaches();
    }


    public void setCoaches(List<IHeadCoach> coaches) {

    }


    public List<IGeneralManager> getGeneralManagers() {
        return managersList;
    }


    public void setGeneralManagers(List<IGeneralManager> generalManagers) {

    }


    public LocalDate getLeagueDate() {
        return null;
    }


    public void setLeagueDate(LocalDate leagueDate) {

    }
}
