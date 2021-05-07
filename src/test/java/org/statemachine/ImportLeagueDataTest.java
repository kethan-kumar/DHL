package org.statemachine;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ImportLeagueDataTest {
    ImportLeagueData importLeagueData = new ImportLeagueData();
    private TemporaryFolder temporaryFolder = new TemporaryFolder();
    private String leagueData = "{\n" +
            "  \"leagueName\":\"Dalhousie Hockey League\",\n" +
            "  \"gameplayConfig\":{\n" +
            "    \"aging\":{\n" +
            "      \"averageRetirementAge\":35,\n" +
            "      \"maximumAge\":50\n" +
            "    },\n" +
            "    \"gameResolver\":{\n" +
            "      \"penaltyChance\":0.1\n" +
            "      \"shotToGoalChance\":0.1\n" +
            "      \"penaltyToGoalChance\":0.1\n" +
            "    },\n" +
            "    \"injuries\":{\n" +
            "      \"randomInjuryChance\":0.05,\n" +
            "      \"injuryDaysLow\":1,\n" +
            "      \"injuryDaysHigh\":260\n" +
            "    },\n" +
            "    \"training\":{\n" +
            "      \"daysUntilStatIncreaseCheck\":100\n" +
            "    },\n" +
            "    \"trading\":{\n" +
            "      \"lossPoint\":8,\n" +
            "      \"randomTradeOfferChance\":0.05,\n" +
            "      \"maxPlayersPerTrade\":2,\n" +
            "      \"randomAcceptanceChance\":0.05,\n" +
            "           \"gmTable\":{\n" +
            "                \"shrewd\":-0.1,\n" +
            "                \"gambler\":0.1,\n" +
            "                \"normal\":0.1\n" +
            "                       }\n" +
            "    }\n" +
            "  },\n" +
            "  \"conferences\":[\n" +
            "    {\n" +
            "      \"conferenceName\":\"Eastern Conference\",\n" +
            "      \"divisions\":[\n" +
            "        {\n" +
            "          \"divisionName\":\"Atlantic\",\n" +
            "          \"teams\":[\n" +
            "            {\n" +
            "              \"teamName\":\"Halifax\",\n" +
            "              \"generalManager\":{\n" +
            "                \"name\":\"Mister Fred\",\n" +
            "                \"personality\":\"gambler\"\n" +
            "              },\n" +
            "              \"headCoach\":{\n" +
            "                \"name\":\"Mary Smith\",\n" +
            "                \"skating\":0.5,\n" +
            "                \"shooting\":0.8,\n" +
            "                \"checking\":0.3,\n" +
            "                \"saving\":0.5\n" +
            "              },\n" +
            "              \"players\":[\n" +
            "                {\n" +
            "                  \"playerName\":\"Player One\",\n" +
            "                  \"position\":\"forward\",\n" +
            "                  \"captain\":true,\n" +
            "                  \"age\":27,\n" +
            "                  \"skating\":15,\n" +
            "                  \"shooting\":18,\n" +
            "                  \"checking\":12,\n" +
            "                  \"saving\":0\n" +
            "                  \"birthDay\":11\n" +
            "                  \"birthMonth\":06\n" +
            "                  \"birthYear\":1992\n" +
            "                },\n" +
            "                {\n" +
            "                  \"playerName\":\"Player Two\",\n" +
            "                  \"position\":\"defense\",\n" +
            "                  \"captain\":false,\n" +
            "                  \"age\":20,\n" +
            "                  \"skating\":10,\n" +
            "                  \"shooting\":10,\n" +
            "                  \"checking\":10,\n" +
            "                  \"saving\":0\n" +
            "                   \"birthDay\":11\n" +
            "                  \"birthMonth\":06\n" +
            "                  \"birthYear\":1992\n" +
            "                },\n" +
            "                {\n" +
            "                  \"playerName\":\"Player Three\",\n" +
            "                  \"position\":\"goalie\",\n" +
            "                  \"captain\":false,\n" +
            "                  \"age\":33,\n" +
            "                  \"skating\":10,\n" +
            "                  \"shooting\":4,\n" +
            "                  \"checking\":9,\n" +
            "                  \"saving\":18\n" +
            "                   \"birthDay\":11\n" +
            "                  \"birthMonth\":06\n" +
            "                  \"birthYear\":1992\n" +
            "                }\n" +
            "              ]\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ],\n" +
            "  \"freeAgents\":[\n" +
            "    {\n" +
            "      \"playerName\":\"Agent One\",\n" +
            "      \"position\":\"forward\",\n" +
            "      \"age\":25,\n" +
            "      \"skating\":10,\n" +
            "      \"shooting\":10,\n" +
            "      \"checking\":10,\n" +
            "      \"saving\":0\n" +
            "      \"birthDay\":11\n" +
            "      \"birthMonth\":06\n" +
            "      \"birthYear\":1992\n" +
            "    },\n" +
            "    {\n" +
            "      \"playerName\":\"Agent Two\",\n" +
            "      \"position\":\"defense\",\n" +
            "      \"age\":25,\n" +
            "      \"skating\":10,\n" +
            "      \"shooting\":10,\n" +
            "      \"checking\":10,\n" +
            "      \"saving\":0\n" +
            "      \"birthDay\":11\n" +
            "      \"birthMonth\":06\n" +
            "      \"birthYear\":1992\n" +
            "    },\n" +
            "    {\n" +
            "      \"playerName\":\"Agent Three\",\n" +
            "      \"position\":\"goalie\",\n" +
            "      \"age\":25,\n" +
            "      \"skating\":10,\n" +
            "      \"shooting\":5,\n" +
            "      \"checking\":5,\n" +
            "      \"saving\":10\n" +
            "      \"birthDay\":11\n" +
            "      \"birthMonth\":06\n" +
            "      \"birthYear\":1992\n" +
            "    }\n" +
            "  ],\n" +
            "  \"coaches\":[\n" +
            "    {\n" +
            "      \"name\":\"Joe Smith\",\n" +
            "      \"skating\":0.5,\n" +
            "      \"shooting\":0.8,\n" +
            "      \"checking\":0.3,\n" +
            "      \"saving\":1.0\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\":\"Frank Smith\",\n" +
            "      \"skating\":0.5,\n" +
            "      \"shooting\":0.8,\n" +
            "      \"checking\":0.3,\n" +
            "      \"saving\":0.5\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\":\"Samantha Smith\",\n" +
            "      \"skating\":1.0,\n" +
            "      \"shooting\":0.5,\n" +
            "      \"checking\":0.5,\n" +
            "      \"saving\":0.0\n" +
            "    }\n" +
            "  ],\n" +
            "  \"generalManagers\":[\n" +
            "    {\n" +
            "      \"name\":\"Karen Potam\",\n" +
            "      \"personality\":\"shrewd\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\":\"Joseph Squidly\",\n" +
            "      \"personality\":\"gambler\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\":\"Tom Spaghetti\",\n" +
            "      \"personality\":\"normal\"\n" +
            "    }\n" +
            "  ],\n" +
            "}";
    private String jsonSchema = "{\n" +
            "\"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
            "\"required\": [\n" +
            "\t\t\"leagueName\"\n" +
            "\t],\n" +
            "\"properties\": {\n" +
            "\t\"leagueName\": {\n" +
            "\t\t\"id\": \"#/properties/leagueName\",\n" +
            "\t\t\"type\": \"string\",\n" +
            "\t\t\"title\": \"League Name\",\n" +
            "\t\t\"description\": \"Name of the league\",\n" +
            "\t\t\"minLength\": 1\n" +
            "\t}\n" +
            "}\t\n" +
            "}";

    @Test
    public void testSchemaValidationTrue() {
        String leagueData = "{\n" +
                "\t\"leagueName\":\"Dalhousie Hockey League\"\n" +
                "}\n";
        Validation validation = new Validation();
        Assert.assertTrue(validation.schemaValidation(leagueData, jsonSchema));
    }

    @Test
    public void testSchemaValidationFalse() {
        String leagueData = "{\n" +
                "\t\"leagueName\":\"\"\n" +
                "}\n";
        Validation validation = new Validation();
        Assert.assertFalse(validation.schemaValidation(leagueData, jsonSchema));
    }

    @Test
    public void testLoadMemory() {
        try {
            temporaryFolder.create();
            File newFile = temporaryFolder.newFile("leagueJSON.json");

            FileWriter fileWriter = new FileWriter(newFile);
            fileWriter.write(leagueData);
            fileWriter.close();
            Assert.assertEquals("Dalhousie Hockey League", importLeagueData.loadLeagueMemory(newFile.getPath()).getLeagueName());
            temporaryFolder.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoadConference() {
        try {
            temporaryFolder.create();
            File newFile = temporaryFolder.newFile("leagueJSON.json");
            FileWriter fileWriter = new FileWriter(newFile);
            fileWriter.write(leagueData);
            fileWriter.close();
            Assert.assertEquals("Eastern Conference", importLeagueData.loadLeagueMemory(newFile.getPath()).getConferences().get(0).getConferenceName());
            temporaryFolder.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoadDivision() {
        try {
            temporaryFolder.create();
            File newFile = temporaryFolder.newFile("leagueJSON.json");
            FileWriter fileWriter = new FileWriter(newFile);
            fileWriter.write(leagueData);
            fileWriter.close();
            Assert.assertEquals("Atlantic", importLeagueData.loadLeagueMemory(newFile.getPath()).getConferences().get(0).getDivisions().get(0).getDivisionName());
            temporaryFolder.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoadTeam() {
        try {
            temporaryFolder.create();
            File newFile = temporaryFolder.newFile("leagueJSON.json");
            FileWriter fileWriter = new FileWriter(newFile);
            fileWriter.write(leagueData);
            fileWriter.close();
            Assert.assertEquals("Halifax", importLeagueData.loadLeagueMemory(newFile.getPath()).getConferences().get(0).getDivisions().get(0).getTeams().get(0).getTeamName());
            temporaryFolder.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoadPlayer() {
        try {
            temporaryFolder.create();
            File newFile = temporaryFolder.newFile("leagueJSON.json");
            FileWriter fileWriter = new FileWriter(newFile);
            fileWriter.write(leagueData);
            fileWriter.close();
            Assert.assertEquals("Player One", importLeagueData.loadLeagueMemory(newFile.getPath()).getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().get(0).getPlayerName());
            temporaryFolder.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}