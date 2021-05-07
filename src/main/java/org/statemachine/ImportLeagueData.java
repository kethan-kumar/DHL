/* @Author: Kethan Kumar */

package org.statemachine;

import org.io.abstractfactory.IOAbstractFactory;
import org.io.interfaces.IInput;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.leaguemodel.abstractfactory.LeagueModelAbstractFactory;
import org.leaguemodel.interfaces.*;
import org.statemachine.abstractfactory.StateAbstractFactory;
import org.statemachine.interfaces.IImportLeagueData;
import org.statemachine.interfaces.IValidate;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImportLeagueData implements IImportLeagueData {
    LeagueDataKeys leagueDataKeys;
    IValidate validate;
    IInput input;
    LocalDate leagueDate;
    LocalDate playerBday;
    Period actualAge;
    private StateAbstractFactory stateFactory;
    private IOAbstractFactory ioFactory;
    private LeagueModelAbstractFactory leagueAbstractFactory;

    public ImportLeagueData() {
        stateFactory = StateAbstractFactory.instance();
        leagueAbstractFactory = LeagueModelAbstractFactory.instance();
        ioFactory = IOAbstractFactory.instance();
        leagueDataKeys = leagueAbstractFactory.createLeagueDatakeys();
        validate = stateFactory.createValidation();
        input = ioFactory.createInput();
    }

    public ILeague loadLeagueMemory(String jsonFileLocation) {
        JSONParser jsonParser = new JSONParser();
        String leagueName, conferenceName, divisionName, teamName, playerName, position, coachName;
        int injuryDaysLow, injuryDaysHigh, daysUntilStatIncreaseCheck, lossPoint, maxPlayersPerTrade, age;
        double penaltyChance, shotToGoalChance, penaltyToGoalChance, randomInjuryChance, randomTradeOfferChance, randomAcceptanceChance, coachSkating, coachShooting, coachChecking, coachSaving;
        boolean captain;
        ILeague leagueLOM = null;
        try {
            Reader reader = new FileReader(jsonFileLocation);
            JSONObject leagueJSONObject = (JSONObject) jsonParser.parse(reader);
            JSONArray conferencesJSONArray = (JSONArray) leagueJSONObject.get(leagueDataKeys.getConferences());
            leagueLOM = leagueAbstractFactory.createLeague();
            IGameplayConfig gameplayConfig = leagueAbstractFactory.createGameplayConfig();
            JSONObject gameplayConfigJSONObject = (JSONObject) leagueJSONObject.get(leagueDataKeys.getGameplayConfig());

            IAging aging = leagueAbstractFactory.createAging();
            JSONObject ageingObject = (JSONObject) gameplayConfigJSONObject.get(leagueDataKeys.getAging());
            int averageRetirementAge = (int) (long) ageingObject.get(leagueDataKeys.getAverageRetirementAge());
            int maximumAge = (int) (long) ageingObject.get(leagueDataKeys.getMaximumAge());
            aging.setAverageRetirementAge(averageRetirementAge);
            aging.setMaximumAge(maximumAge);
            gameplayConfig.setAging(aging);

            IGameResolver gameResolver = leagueAbstractFactory.createGameResolver();
            JSONObject gameResolverJSONObject = (JSONObject) gameplayConfigJSONObject.get(leagueDataKeys.getGameResolver());
            penaltyChance = (double) gameResolverJSONObject.get(leagueDataKeys.getPenaltyChance());
            shotToGoalChance = (double) gameResolverJSONObject.get(leagueDataKeys.getShotToGoalChance());
            penaltyToGoalChance = (double) gameResolverJSONObject.get(leagueDataKeys.getPenaltyToGoalChance());
            gameResolver.setPenaltyChance(penaltyChance);
            gameResolver.setShotToGoalChance(shotToGoalChance);
            gameResolver.setPenaltyToGoalChance(penaltyToGoalChance);
            gameplayConfig.setGameResolver(gameResolver);

            IInjuries injuries = leagueAbstractFactory.createInjuries();
            JSONObject injuriesJSONObject = (JSONObject) gameplayConfigJSONObject.get(leagueDataKeys.getInjuries());
            randomInjuryChance = (double) injuriesJSONObject.get(leagueDataKeys.getRandomInjuryChance());
            injuryDaysLow = (int) (long) injuriesJSONObject.get(leagueDataKeys.getInjuryDaysLow());
            injuryDaysHigh = (int) (long) injuriesJSONObject.get(leagueDataKeys.getInjuryDaysHigh());
            injuries.setRandomInjuryChance(randomInjuryChance);
            injuries.setInjuryDaysLow(injuryDaysLow);
            injuries.setInjuryDaysHigh(injuryDaysHigh);
            gameplayConfig.setInjuries(injuries);

            ITraining training = leagueAbstractFactory.createTraining();
            JSONObject trainingJSONObject = (JSONObject) gameplayConfigJSONObject.get(leagueDataKeys.getTraining());
            daysUntilStatIncreaseCheck = (int) (long) trainingJSONObject.get(leagueDataKeys.getDaysUntilStatIncreaseCheck());
            training.setDaysUntilStatIncreaseCheck(daysUntilStatIncreaseCheck);
            gameplayConfig.setTraining(training);

            ITrading trading = leagueAbstractFactory.createTrading();
            JSONObject tradingJSONObject = (JSONObject) gameplayConfigJSONObject.get(leagueDataKeys.getTrading());
            lossPoint = (int) (long) tradingJSONObject.get(leagueDataKeys.getLossPoint());
            randomTradeOfferChance = (double) tradingJSONObject.get(leagueDataKeys.getRandomTradeOfferChance());
            maxPlayersPerTrade = (int) (long) tradingJSONObject.get(leagueDataKeys.getMaxPlayersPerTrade());
            randomAcceptanceChance = (double) tradingJSONObject.get(leagueDataKeys.getRandomAcceptanceChance());
            Map<String, Double> gmTable = (Map<String, Double>) tradingJSONObject.get(leagueDataKeys.getGmTable());
            trading.setLossPoint(lossPoint);
            trading.setMaxPlayersPerTrade(maxPlayersPerTrade);
            trading.setRandomAcceptanceChance(randomAcceptanceChance);
            trading.setRandomTradeOfferChance(randomTradeOfferChance);
            trading.setGmTable(gmTable);
            gameplayConfig.setTrading(trading);

            List<IConference> conferenceArrayListLOM = new ArrayList<>();
            leagueName = (String) leagueJSONObject.get(leagueDataKeys.getLeagueName());
            leagueLOM.setLeagueName(leagueName);
            leagueLOM.setGameplayConfig(gameplayConfig);
            leagueLOM.setConferences(conferenceArrayListLOM);

            for (Object o : conferencesJSONArray) {
                JSONObject conferencesJSONObject = (JSONObject) o;
                JSONArray divisionsJSONArray = (JSONArray) conferencesJSONObject.get(leagueDataKeys.getDivisions());

                IConference conferenceLOM = leagueAbstractFactory.createConferences();
                List<IDivisions> divisionsArrayList = new ArrayList<>();
                conferenceName = (String) conferencesJSONObject.get(leagueDataKeys.getConferenceName());

                conferenceLOM.setConferenceName(conferenceName);
                conferenceLOM.setDivisions(divisionsArrayList);
                conferenceArrayListLOM.add(conferenceLOM);

                for (Object value : divisionsJSONArray) {
                    JSONObject divisionsJSONObject = (JSONObject) value;
                    JSONArray teamsJSONArray = (JSONArray) divisionsJSONObject.get(leagueDataKeys.getTeams());

                    IDivisions divisionsLOM = leagueAbstractFactory.createDivision();
                    List<ITeam> teamArrayList = new ArrayList<>();
                    divisionName = (String) divisionsJSONObject.get(leagueDataKeys.getDivisionName());

                    divisionsLOM.setDivisionName(divisionName);
                    divisionsLOM.setTeams(teamArrayList);
                    divisionsArrayList.add(divisionsLOM);

                    for (Object item : teamsJSONArray) {
                        JSONObject teamsJSONObject = (JSONObject) item;
                        JSONArray playersJSONArray = (JSONArray) teamsJSONObject.get(leagueDataKeys.getPlayers());

                        ITeam teamLOM = leagueAbstractFactory.createTeam();
                        List<IPlayers> playersArrayListLOM = new ArrayList<>();
                        teamName = (String) teamsJSONObject.get(leagueDataKeys.getTeamName());

                        IHeadCoach headCoach = leagueAbstractFactory.createHeadCoach();
                        JSONObject headCoachJSONObject = (JSONObject) teamsJSONObject.get(leagueDataKeys.getHeadCoach());
                        coachName = (String) headCoachJSONObject.get(leagueDataKeys.getName());
                        double headcoachSkating = (double) headCoachJSONObject.get(leagueDataKeys.getSkating());
                        double headcoachShooting = (double) headCoachJSONObject.get(leagueDataKeys.getShooting());
                        double headcoachChecking = (double) headCoachJSONObject.get(leagueDataKeys.getChecking());
                        double headcoachSaving = (double) headCoachJSONObject.get(leagueDataKeys.getSaving());
                        headCoach.setName(coachName);
                        headCoach.setSkating(headcoachSkating);
                        headCoach.setShooting(headcoachShooting);
                        headCoach.setChecking(headcoachChecking);
                        headCoach.setSaving(headcoachSaving);
                        teamLOM.setTeamName(teamName);

                        JSONObject generalManagerJSONObject = (JSONObject) teamsJSONObject.get(leagueDataKeys.getGeneralManager());
                        IGeneralManager generalManager = leagueAbstractFactory.createGeneralManager();
                        String managerName = (String) generalManagerJSONObject.get(leagueDataKeys.getName());
                        String personality = (String) generalManagerJSONObject.get(leagueDataKeys.getPersonality());
                        generalManager.setName(managerName);
                        generalManager.setPersonality(personality);

                        headCoach.setName(coachName);
                        teamLOM.setGeneralManager(generalManager);
                        teamLOM.setHeadCoach(headCoach);
                        teamLOM.setPlayers(playersArrayListLOM);
                        teamArrayList.add(teamLOM);

                        for (Object element : playersJSONArray) {
                            JSONObject playersJSONObject = (JSONObject) element;
                            IPlayers playersLOM = leagueAbstractFactory.createPlayers();
                            playerName = (String) playersJSONObject.get(leagueDataKeys.getPlayerName());
                            position = (String) playersJSONObject.get(leagueDataKeys.getPosition());
                            captain = (boolean) playersJSONObject.get(leagueDataKeys.getCaptain());
                            int skating = (int) (long) playersJSONObject.get(leagueDataKeys.getSkating());
                            int shooting = (int) (long) playersJSONObject.get(leagueDataKeys.getShooting());
                            int checking = (int) (long) playersJSONObject.get(leagueDataKeys.getChecking());
                            int saving = (int) (long) playersJSONObject.get(leagueDataKeys.getSaving());
                            int birthDay = (int) (long) playersJSONObject.get(leagueDataKeys.getBirthDay());
                            int birthMonth = (int) (long) playersJSONObject.get(leagueDataKeys.getBirthMonth());
                            int birthYear = (int) (long) playersJSONObject.get(leagueDataKeys.getBirthYear());
                            leagueDate = leagueLOM.getLeagueDate();
                            playerBday = LocalDate.of(birthYear, birthMonth, birthDay);
                            actualAge = Period.between(playerBday, leagueDate);
                            playersLOM.setPlayerName(playerName);
                            playersLOM.setPosition(position);
                            playersLOM.setIsCaptain(captain);
                            playersLOM.setBirthDay(birthDay);
                            playersLOM.setBirthYear(birthYear);
                            playersLOM.setAge(actualAge.getYears());
                            playersLOM.setBirthMonth(birthMonth);
                            playersLOM.setSkating(skating);
                            playersLOM.setShooting(shooting);
                            playersLOM.setChecking(checking);
                            playersLOM.setSaving(saving);
                            playersArrayListLOM.add(playersLOM);
                        }
                    }
                }
            }
            JSONArray freeAgentsJSONArray = (JSONArray) leagueJSONObject.get(leagueDataKeys.getFreeAgents());
            List<IPlayers> freeAgentsArrayListLOM = new ArrayList<>();
            leagueLOM.setFreeAgents(freeAgentsArrayListLOM);
            for (int i = 0; i < freeAgentsJSONArray.size(); i++) {
                JSONObject freeAgentsJSONObject = (JSONObject) freeAgentsJSONArray.get(i);

                IPlayers freeAgentsLOM = leagueAbstractFactory.createPlayers();
                playerName = (String) freeAgentsJSONObject.get(leagueDataKeys.getPlayerName());
                position = (String) freeAgentsJSONObject.get(leagueDataKeys.getPosition());
                Integer skating = (int) (long) freeAgentsJSONObject.get(leagueDataKeys.getSkating());
                Integer shooting = (int) (long) freeAgentsJSONObject.get(leagueDataKeys.getShooting());
                Integer checking = (int) (long) freeAgentsJSONObject.get(leagueDataKeys.getChecking());
                Integer saving = (int) (long) freeAgentsJSONObject.get(leagueDataKeys.getSaving());
                Integer birthDay = (int) (long) freeAgentsJSONObject.get(leagueDataKeys.getBirthDay());
                Integer birthMonth = (int) (long) freeAgentsJSONObject.get(leagueDataKeys.getBirthMonth());
                Integer birthYear = (int) (long) freeAgentsJSONObject.get(leagueDataKeys.getBirthYear());
                leagueDate = leagueLOM.getLeagueDate();
                playerBday = LocalDate.of(birthYear, birthMonth, birthDay);
                actualAge = Period.between(playerBday, leagueDate);
                freeAgentsLOM.setPlayerName(playerName);
                freeAgentsLOM.setPosition(position);
                freeAgentsLOM.setSkating(skating);
                freeAgentsLOM.setShooting(shooting);
                freeAgentsLOM.setChecking(checking);
                freeAgentsLOM.setSaving(saving);
                freeAgentsLOM.setAge(actualAge.getYears());
                freeAgentsLOM.setBirthDay(birthDay);
                freeAgentsLOM.setBirthMonth(birthMonth);
                freeAgentsLOM.setBirthYear(birthYear);
                freeAgentsArrayListLOM.add(freeAgentsLOM);
            }
            JSONArray coachJsonArray = (JSONArray) leagueJSONObject.get(leagueDataKeys.getCoaches());
            List<IHeadCoach> coachArrayList = new ArrayList<>();
            leagueLOM.setCoaches(coachArrayList);
            for (Object o : coachJsonArray) {
                JSONObject coachJSONObject = (JSONObject) o;
                IHeadCoach coach = leagueAbstractFactory.createHeadCoach();
                coachName = (String) coachJSONObject.get(leagueDataKeys.getName());
                coachSkating = (double) coachJSONObject.get(leagueDataKeys.getSkating());
                coachShooting = (double) coachJSONObject.get(leagueDataKeys.getShooting());
                coachChecking = (double) coachJSONObject.get(leagueDataKeys.getChecking());
                coachSaving = (double) coachJSONObject.get(leagueDataKeys.getSaving());
                coach.setName(coachName);
                coach.setSkating(coachSkating);
                coach.setShooting(coachShooting);
                coach.setChecking(coachChecking);
                coach.setSaving(coachSaving);
                coachArrayList.add(coach);
            }
            JSONArray generalManagerJSONArray = (JSONArray) leagueJSONObject.get(leagueDataKeys.getGeneralManagers());
            List<IGeneralManager> generalManagersArrayList = new ArrayList<>();
            leagueLOM.setGeneralManagers(generalManagersArrayList);
            for (Object o : generalManagerJSONArray) {
                JSONObject generalManagerJSONObject = (JSONObject) o;
                IGeneralManager generalManager = leagueAbstractFactory.createGeneralManager();
                String managerName = (String) generalManagerJSONObject.get(leagueDataKeys.getManagerName());
                String personality = (String) generalManagerJSONObject.get(leagueDataKeys.getPersonality());
                generalManager.setName(managerName);
                generalManager.setPersonality(personality);
                generalManagersArrayList.add(generalManager);
            }
            return leagueLOM;
        } catch (FileNotFoundException e) {
            System.out.println("File not found exception!" + e);
        } catch (ParseException e) {
            System.out.println("Parse exception has occurred!" + e);
        } catch (IOException e) {
            System.out.println("An IO exception has occurred!" + e);
        } catch (Exception e) {
            System.out.println("An exception has occurred!" + e);
        }
        return leagueLOM;
    }

    public boolean leagueJSONValidation(String[] args, String jsonSchemaLocation) {
        boolean schemaValStatus;
        String leagueData;
        String jsonSchema;
        String inputJsonLocation = args[0];
        leagueData = input.readJSON(inputJsonLocation);
        jsonSchema = input.readJSON(jsonSchemaLocation);
        schemaValStatus = validate.schemaValidation(leagueData, jsonSchema);
        return schemaValStatus;
    }
}
