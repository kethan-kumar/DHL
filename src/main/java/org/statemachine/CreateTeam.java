/* @Author: Kethan Kumar */

package org.statemachine;

import org.io.abstractfactory.IOAbstractFactory;
import org.io.interfaces.IDisplay;
import org.io.interfaces.IInput;
import org.leaguemodel.Players;
import org.leaguemodel.Team;
import org.leaguemodel.interfaces.ILeague;
import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.ITeam;
import org.statemachine.abstractfactory.StateAbstractFactory;
import org.statemachine.interfaces.ICreate;
import org.statemachine.interfaces.ICreateTeamParameterObject;
import org.statemachine.interfaces.IValidate;

import java.util.ArrayList;
import java.util.List;

public class CreateTeam implements ICreate {
    private static final int PLAYERCOUNT = 30;
    private static final int GOALIECOUNT = 4;
    private static final int FORWARDCOUNT = 16;
    private static final int DEFENSECOUNT = 10;
    private static final String USER = "User";
    private static final String PLAYERNUMBER = "Player Number";
    ITeam teamLOM;
    IValidate validate;
    IInput input;
    IDisplay display;
    List<IPlayers> playersList;
    List<Integer> trackIndex;
    List<IPlayers> freeAgentsList;
    private StateAbstractFactory stateFactory;
    private IOAbstractFactory ioFactory;
    public CreateTeam() {
        stateFactory = StateAbstractFactory.instance();
        ioFactory = IOAbstractFactory.instance();
        teamLOM = new Team();
        playersList = new ArrayList<>();
        trackIndex = new ArrayList<>();
        freeAgentsList = new ArrayList<>();
        input = ioFactory.createInput();
        display = ioFactory.createDisplay();
        validate = stateFactory.createValidation();
    }

    public ITeam createTeam(ICreateTeamParameterObject createTeamParameterObject, List<IPlayers> playersList) {
        teamLOM.setTeamName(createTeamParameterObject.getTeamName());
        teamLOM.setGeneralManager(createTeamParameterObject.getGeneralManager());
        teamLOM.setHeadCoach(createTeamParameterObject.getTeamHeadCoach());
        teamLOM.setPlayers(playersList);
        teamLOM.setTeamType(USER);
        return teamLOM;
    }

    public List<IPlayers> chooseTeamPlayers(ILeague league) {
        int goalieCount = 0;
        int forwardCount = 0;
        int defenseCount = 0;
        int totalPlayer = 30;
        boolean flag;
        display.displayFreeAgents(league);
        do {
            flag = true;
            display.displayMessage("No of players left to be picked:" + (totalPlayer - playersList.size()));
            display.displayMessage("Provide the player number you want to pick");
            String number = input.userInput(PLAYERNUMBER);
            if (validate.validateString(number)) {
                display.displayMessage("Please enter digits only");
            } else {
                int playerNumber = Integer.parseInt(number);
                if (trackIndex.contains(playerNumber)) {
                    display.displayMessage("Cannot enter same player again");
                    flag = false;
                }
                if (playerNumber > league.getFreeAgents().size() || playerNumber <= 0) {
                    display.displayMessage("Enter correct number");
                } else {
                    if (league.getFreeAgents().get(playerNumber - 1).getPosition().equalsIgnoreCase(Position.GOALIE.toString())) {
                        goalieCount++;
                        if (goalieCount > GOALIECOUNT) {
                            display.displayMessage("You can have only 4 goalie");
                            flag = false;
                        }
                    }
                    if (league.getFreeAgents().get(playerNumber - 1).getPosition().equalsIgnoreCase(Position.FORWARD.toString())) {
                        forwardCount++;
                        if (forwardCount > FORWARDCOUNT) {
                            display.displayMessage("You can have only 16 forward");
                            flag = false;
                        }
                    }
                    if (league.getFreeAgents().get(playerNumber - 1).getPosition().equalsIgnoreCase(Position.DEFENSE.toString())) {
                        defenseCount++;
                        if (defenseCount > DEFENSECOUNT) {
                            display.displayMessage("You can have only 10 defense");
                            flag = false;
                        }
                    }
                    if (flag) {
                        trackIndex.add(playerNumber);
                        IPlayers player = new Players();
                        player.setPlayerName(league.getFreeAgents().get(playerNumber - 1).getPlayerName());
                        player.setPosition(league.getFreeAgents().get(playerNumber - 1).getPosition());
                        player.setAge(league.getFreeAgents().get(playerNumber - 1).getAge());
                        player.setSkating(league.getFreeAgents().get(playerNumber - 1).getSkating());
                        player.setShooting(league.getFreeAgents().get(playerNumber - 1).getShooting());
                        player.setChecking(league.getFreeAgents().get(playerNumber - 1).getChecking());
                        player.setSaving(league.getFreeAgents().get(playerNumber - 1).getSaving());
                        player.setIsCaptain(false);
                        player.setBirthDay(league.getFreeAgents().get(playerNumber - 1).getBirthDay());
                        player.setBirthMonth(league.getFreeAgents().get(playerNumber - 1).getBirthMonth());
                        player.setBirthYear(league.getFreeAgents().get(playerNumber - 1).getBirthYear());
                        playersList.add(player);
                    }
                }
            }
        } while (playersList.size() < PLAYERCOUNT);
        for (int value : trackIndex) {
            freeAgentsList.add(league.getFreeAgents().get(value - 1));
        }
        league.getFreeAgents().removeAll(freeAgentsList);
        return playersList;
    }

    private enum Position {
        GOALIE, FORWARD, DEFENSE
    }
}
