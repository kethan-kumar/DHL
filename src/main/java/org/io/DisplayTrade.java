/* @Author: Yash Jaiswal */

package org.io;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.io.abstractfactory.IOAbstractFactory;
import org.io.interfaces.IDisplayTrade;
import org.io.interfaces.IUserInputTrade;
import org.leaguemodel.interfaces.ILeague;
import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.ITeam;
import org.trading.CheckPlayers;
import org.trading.interfaces.ICheckPlayers;

import java.util.List;

public class DisplayTrade implements IDisplayTrade {

    private final Logger logger;
    private final IOAbstractFactory ioFactory;

    public DisplayTrade() {
        logger = Logger.getLogger(DisplayTrade.class.getName());
        ioFactory = IOAbstractFactory.instance();
    }

    public Boolean displayUserTrade(List<IPlayers> weakPlayers, List<IPlayers> strongPlayers) {
        logger.debug("Displaying Trade offer for User's Team");
        System.out.println("Players Offered for Trade: ");
        System.out.println("Player Name" + "  " + "Position" + "  " + "Age" + "  " + "Skating" + "  " + "Shooting" + "  " + "Checking" + " " + "Saving");
        System.out.println("=============================================================================");
        for (IPlayers weakPlayer : weakPlayers) {
            System.out.print("" + weakPlayer.getPlayerName());
            System.out.print(" 	" + weakPlayer.getPosition());
            System.out.print("  " + weakPlayer.getAge());
            System.out.print("	" + weakPlayer.getSkating());
            System.out.print(" 	" + weakPlayer.getShooting());
            System.out.print(" 	" + weakPlayer.getChecking());
            System.out.print(" 	" + weakPlayer.getSaving() + "\n");
        }
        System.out.println("\nPlayers Requested for Trade from your Team:");
        System.out.println("Player Name" + "  " + "Position" + "  " + "Age" + "  " + "Skating" + "  " + "Shooting" + "  " + "Checking" + " " + "Saving");
        System.out.println("=============================================================================");
        for (int i = 0; i < strongPlayers.size(); i++) {
            System.out.print("" + strongPlayers.get(i).getPlayerName());
            System.out.print("      " + strongPlayers.get(i).getPosition());
            System.out.print("      " + strongPlayers.get(i).getAge());
            System.out.print("      " + strongPlayers.get(i).getSkating());
            System.out.print("      " + strongPlayers.get(i).getShooting());
            System.out.print("      " + strongPlayers.get(i).getChecking());
            System.out.print("      " + strongPlayers.get(i).getSaving() + "\n");
        }
        System.out.println("\nDo you accept the Trade Offer ? : 1.Yes 2.No");
        System.out.println("Enter your choice: ");
        IUserInputTrade userInput = ioFactory.createUserInputTrade();
        int userChoice = userInput.userInputInt();
        boolean correctOption = false;
        do {
            if (userChoice == 1) {
                correctOption = true;
                logger.debug("User accepted trade.");
                return true;
            } else if (userChoice == 2) {
                correctOption = true;
                logger.debug("User denied trade.");
                return false;
            } else {
                System.out.println("re-enter correct option!!!!");
                System.out.println("Enter your choice: ");
                userChoice = userInput.userInputInt();
            }
        } while (correctOption == false);
        return false;
    }

    public int displayFreeAgents(ITeam team, ILeague leagueObj) {
        ICheckPlayers check = new CheckPlayers();
        IUserInputTrade userInput = new UserInputTrade();
        System.out.println("Add players from Free Agents List to your Team: ");
        System.out.println("No." + "  " + "Player Name" + "  " + "Position" + "  " + "Age" + "  " + "Skating" + "  " + "Shooting" + "  " + "Checking" + " " + "Saving");
        System.out.println("=================================================================");
        for (int i = 0; i < leagueObj.getFreeAgents().size(); i++) {
            System.out.print((i + 1) + "        " + leagueObj.getFreeAgents().get(i).getPlayerName());
            System.out.print("      " + leagueObj.getFreeAgents().get(i).getPosition());
            System.out.print("      " + leagueObj.getFreeAgents().get(i).getAge());
            System.out.print("      " + leagueObj.getFreeAgents().get(i).getSkating());
            System.out.print("      " + leagueObj.getFreeAgents().get(i).getShooting());
            System.out.print("      " + leagueObj.getFreeAgents().get(i).getChecking());
            System.out.print("      " + leagueObj.getFreeAgents().get(i).getSaving() + "\n");
        }
        System.out.println("\nAdjust your roster to satisfy 16 Forwards, 10 Defense and 4 Goalies to proceed further!!!");
        System.out.println("Forwards Count: " + check.forwards(team));
        System.out.println("Defense Count: " + check.defense(team));
        System.out.println("Goalies Count : " + check.goalies(team));
        System.out.println("Select the Free agent to add to your Team: ");
        int freeAgentIndex = userInput.userInputInt();
        return freeAgentIndex;
    }

    public int displayPlayerList(ITeam team) {
        ICheckPlayers check = new CheckPlayers();
        System.out.println("Drop Players from your Team: ");
        System.out.println("No." + "  " + "Player Name" + "  " + "Position" + "  " + "Age" + "  " + "Skating" + "  " + "Shooting" + "  " + "Checking" + " " + "Saving");
        System.out.println("=================================================================");
        for (int i = 0; i < team.getPlayers().size(); i++) {
            System.out.print(i + 1 + "      " + team.getPlayers().get(i).getPlayerName());
            System.out.print("      " + team.getPlayers().get(i).getPosition());
            System.out.print("      " + team.getPlayers().get(i).getAge());
            System.out.print("      " + team.getPlayers().get(i).getSkating());
            System.out.print("      " + team.getPlayers().get(i).getShooting());
            System.out.print("      " + team.getPlayers().get(i).getChecking());
            System.out.print("      " + team.getPlayers().get(i).getSaving() + "\n");
        }
        System.out.println("\nAdjust your roster to satisfy 16 Forwards, 10 Defense and 4 Goalies to proceed further!!!");
        System.out.println("Forwards Count: " + check.forwards(team));
        System.out.println("Defense Count: " + check.defense(team));
        System.out.println("Goalies Count : " + check.goalies(team));
        System.out.println("Select the player to drop from your Team: ");
        IUserInputTrade userInput = ioFactory.createUserInputTrade();
        int playerDropIndex = userInput.userInputInt();
        return playerDropIndex;
    }

    public void displayAITradeStatus(String askingTeam, String oppositeTeam, Boolean tradeDecision) {
        logger.debug("Displaying Trade status with another AI Team");
        if (tradeDecision) {
            System.out.println("Team: " + askingTeam + " succesfully traded with " + oppositeTeam);
            logger.debug("Team: " + askingTeam + " succesfully traded with " + oppositeTeam);
        } else {
            System.out.println("Team: " + askingTeam + " Trade offer got rejected by " + oppositeTeam);
            logger.debug("Team: " + askingTeam + " Trade offer got rejected by " + oppositeTeam);
        }
    }

    public void displayUserTradeStatus(String askingTeam, String oppositeTeam, Boolean tradeDecision) {
        logger.debug("Displaying Trade status with User's Team");
        if (tradeDecision) {
            System.out.println("User's Team - " + oppositeTeam + " Trade with - " + askingTeam + " completed!!");
            logger.debug("Team: " + askingTeam + " succesfully traded with " + oppositeTeam);
        } else {
            System.out.println("You rejected Trade offer by - " + askingTeam + " for your Team: " + oppositeTeam);
            logger.debug("Team: " + askingTeam + " Trade offer got rejected by " + oppositeTeam);
        }
    }

    public void displayTradePickStatus(String askingTeam, String oppositeTeam, Boolean tradeDecision) {
        logger.debug("Displaying Trading Draft Pick status");
        if (tradeDecision) {
            System.out.println("Team: " + askingTeam + " traded draft picks with " + oppositeTeam);
            logger.debug("Team: " + askingTeam + " traded draft picks with " + oppositeTeam);
        } else {
            System.out.println("Team: " + askingTeam + " draft picks trade with " + oppositeTeam + " not successful!!!");
            logger.debug("Team: " + askingTeam + " draft picks trade with " + oppositeTeam + " not successful!!!");
        }
    }

    public void displayOutOfBound(String postion, String type) {
        logger.debug("Displaying Trading Draft Pick status");
        if (StringUtils.equalsIgnoreCase(postion, enumPosition.GOALIE.toString()) && StringUtils.equalsIgnoreCase(type, enumPickType.ADD.toString())) {
            System.out.println("Can't add a Goalie!!!!  Check Roster Limit");
            logger.debug("Can't add a Goalie!!!!  Check Roster Limit");
        }
        if (StringUtils.equalsIgnoreCase(postion, enumPosition.GOALIE.toString()) && StringUtils.equalsIgnoreCase(type, enumPickType.DROP.toString())) {
            System.out.println("Can't drop a Goalie!!!!  Check Roster Limit");
            logger.debug("Can't drop a Goalie!!!!  Check Roster Limit");
        }
        if (StringUtils.equalsIgnoreCase(postion, enumPosition.FORWARD.toString()) && StringUtils.equalsIgnoreCase(type, enumPickType.ADD.toString())) {
            System.out.println("Can't add a Forward!!!!  Check Roster Limit");
            logger.debug("Can't add a Forward!!!!  Check Roster Limit");
        }
        if (StringUtils.equalsIgnoreCase(postion, enumPosition.FORWARD.toString()) && StringUtils.equalsIgnoreCase(type, enumPickType.DROP.toString())) {
            System.out.println("Can't drop a Forward!!!!  Check Roster Limit");
            logger.debug("Can't drop a Forward!!!!  Check Roster Limit");
        }
        if (StringUtils.equalsIgnoreCase(postion, enumPosition.DEFENSE.toString()) && StringUtils.equalsIgnoreCase(type, enumPickType.ADD.toString())) {
            System.out.println("Can't add a Defense!!!!  Check Roster Limit");
            logger.debug("Can't drop a Defense!!!!  Check Roster Limit");
        }
        if (StringUtils.equalsIgnoreCase(postion, enumPosition.DEFENSE.toString()) && StringUtils.equalsIgnoreCase(type, enumPickType.DROP.toString())) {
            System.out.println("Can't drop a Defense!!!!  Check Roster Limit");
            logger.debug("Can't drop a Defense!!!!  Check Roster Limit");
        }

    }

    enum enumPosition {
        GOALIE, FORWARD, DEFENSE
    }

    enum enumPickType {
        ADD, DROP
    }

}
