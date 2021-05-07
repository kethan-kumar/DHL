/* @Author: Siddhant Ashutosh */

package org.io;

import org.io.interfaces.IDisplay;
import org.leaguemodel.interfaces.IGeneralManager;
import org.leaguemodel.interfaces.ILeague;

import java.util.List;

public class Display implements IDisplay {

    public void displayLeagueList(ILeague league) {
        String leagueName = league.getLeagueName();
        if (leagueName == null) {
            System.out.println("No League in the db to load");
            System.exit(1);
        }
        displayMessage(leagueName);
    }

    public void displayTeamList(String leagueName, ILeague league) {
        int count = 0;
        String teamName;
        if (leagueName == null) {
            System.out.println("No League in the db to load");
            System.exit(1);
        } else {
            for (int i = 0; i < league.getConferences().size(); i++) {
                for (int j = 0; j < league.getConferences().get(i).getDivisions().size(); j++) {
                    for (int k = 0; k < league.getConferences().get(i).getDivisions().get(j).getTeams().size(); k++) {
                        count++;
                        teamName = league.getConferences().get(i).getDivisions().get(j).getTeams().get(k).getTeamName();
                        displayMessage(count + ". " + teamName);
                    }
                }
            }
        }
    }

    public void displayTeam(ILeague league) {
        System.out.println("\n==============================");
        for (int i = 0; i < league.getConferences().size(); i++) {
            System.out.println("Conference Name : " + league.getConferences().get(i).getConferenceName());
            for (int j = 0; j < league.getConferences().get(i).getDivisions().size(); j++) {
                System.out.println("Division Name : " + league.getConferences().get(i).getDivisions().get(j).getDivisionName());
                for (int k = 0; k < league.getConferences().get(i).getDivisions().get(j).getTeams().size(); k++) {
                    System.out.println("Team Name : " + league.getConferences().get(i).getDivisions().get(j).getTeams().get(k).getTeamName());
                }
            }
        }
    }

    public void displayGeneralManagerList(ILeague league) {
        List<IGeneralManager> managers = league.getGeneralManagers();
        System.out.println("Number" + "\t" + "Name" + "\t" + "Personality");
        for (int i = 0; i < managers.size(); i++) {
            System.out.print(i + 1);
            System.out.print("\t\t" + managers.get(i).getName());
            System.out.print("\t\t" + managers.get(i).getPersonality());
            System.out.print("\n");

        }
    }

    public void displayHeadCoach(ILeague league) {
        System.out.println("Number" + "\t" + "Name" + "\t" + "Skating" + "\t\t" + "Shooting" + "\t" + "Checking" + "\t" + "Saving");
        for (int i = 0; i < league.getCoaches().size(); i++) {
            System.out.print(i + 1);
            System.out.print("\t\t" + league.getCoaches().get(i).getName());
            System.out.print("\t\t" + league.getCoaches().get(i).getSkating());
            System.out.print("\t\t\t" + league.getCoaches().get(i).getShooting());
            System.out.print("\t\t\t" + league.getCoaches().get(i).getChecking());
            System.out.print("\t\t\t" + league.getCoaches().get(i).getSaving());
            System.out.println();
        }
    }

    public void displayFreeAgents(ILeague league) {
        System.out.println("Number" + " \t" + "Name" + " \t" + "Position" + " \t" + "Age" + " \t" + "Skating" + "\t" + "Shooting" + "\t" + "Checking" + "\t" + "Saving");
        for (int i = 0; i < league.getFreeAgents().size(); i++) {
            System.out.println();
            System.out.print(i + 1);
            System.out.print("\t\t" + league.getFreeAgents().get(i).getPlayerName());
            System.out.print("\t\t" + league.getFreeAgents().get(i).getPosition());
            System.out.print("\t\t" + league.getFreeAgents().get(i).getAge());
            System.out.print("\t\t" + league.getFreeAgents().get(i).getSkating());
            System.out.print("\t\t\t" + league.getFreeAgents().get(i).getShooting());
            System.out.print("\t\t\t" + league.getFreeAgents().get(i).getChecking());
            System.out.print("\t\t\t" + league.getFreeAgents().get(i).getSaving());
        }
        System.out.println();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
