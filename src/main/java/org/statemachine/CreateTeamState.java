/* @Author: Kethan Kumar */

package org.statemachine;

import org.io.abstractfactory.IOAbstractFactory;
import org.io.interfaces.IDisplay;
import org.io.interfaces.IInput;
import org.leaguemodel.Persist;
import org.leaguemodel.Team;
import org.leaguemodel.ValidationsInLeague;
import org.leaguemodel.interfaces.*;
import org.statemachine.abstractfactory.StateAbstractFactory;
import org.statemachine.interfaces.*;

import java.util.List;

public class CreateTeamState implements IGameState {
    private static final String CONFERENCENAME = "conference name";
    private static final String DIVISIONNAME = "division name";
    private static final String TEAMNAME = "new team name to be created";
    private static final String MANAGERNAME = "general manager number you want";
    private static final String HEADCOACHNAME = "head coach number you want";
    private static final String LOADTEAMINTOMEMORY = "Please wait while we load the team into memory ...";
    private static final String TEAMSUCCESSFUL = "Team is created successfully!";
    private final IInput input;
    private final IContext gameContext;
    private final StateAbstractFactory stateFactory;
    private String conferenceName;
    private String divisionName;
    private String teamName;
    private IGeneralManager generalManagerObject;
    private IHeadCoach headCoachObject;
    private ILeague leagueLOM;
    private IValidationsInLeague iValidationsInLeague;
    private IPersistence iPersistence;
    private ITeam teamLOM;
    private IDisplay display;
    private IValidate validate;
    private ICreate createTeamLogic;
    private IGameState playerChoice;
    private IOAbstractFactory ioFactory;

    public CreateTeamState(IContext gameContext, ILeague leagueLOM) {
        this.gameContext = gameContext;
        this.leagueLOM = leagueLOM;
        stateFactory = StateAbstractFactory.instance();
        ioFactory = IOAbstractFactory.instance();
        iValidationsInLeague = new ValidationsInLeague();
        iPersistence = new Persist();
        teamLOM = new Team();
        createTeamLogic = stateFactory.createCreateTeam();
        display = ioFactory.createDisplay();
        input = ioFactory.createInput();
        validate = stateFactory.createValidation();
    }

    public void entryProcess() {
        createTeamInput(leagueLOM);
        teamGeneralManagerInput();
        teamHeadCoachInput();
    }

    public void doProcess() {
        List<IPlayers> playersList;
        ICreateTeamParameterObject ICreateTeamParameterObject = new CreateTeamParameterObject(conferenceName, divisionName, teamName, generalManagerObject, headCoachObject, leagueLOM);
        playersList = createTeamLogic.chooseTeamPlayers(ICreateTeamParameterObject.getLeagueLOM());
        display.displayMessage(LOADTEAMINTOMEMORY);
        teamLOM = createTeamLogic.createTeam(ICreateTeamParameterObject, playersList);
        display.displayMessage(TEAMSUCCESSFUL);
        playerChoice = stateFactory.createPlayerChoiceState(gameContext, leagueLOM);
        gameContext.setCurrentState(playerChoice);
    }

    public void exitProcess() {
        iPersistence.appendTeam(divisionName, conferenceName, teamLOM, leagueLOM);
    }

    public void createTeamInput(ILeague leagueLOM) {
        display.displayMessage("Let's begin the team creation ...");
        conferenceName = input.userInput(CONFERENCENAME);
        boolean proceed = iValidationsInLeague.validateConference(conferenceName, leagueLOM);
        if (proceed) {
            divisionName = input.userInput(DIVISIONNAME);
        }
        proceed = iValidationsInLeague.validateDivisions(conferenceName, divisionName, leagueLOM);
        if (proceed) {
            teamName = input.userInput(TEAMNAME);
        } else {
            display.displayMessage("Provided incorrect value, team creation aborted!");
            System.exit(1);
        }
    }

    public void teamGeneralManagerInput() {
        String generalManager;
        display.displayGeneralManagerList(leagueLOM);
        boolean flagManager;
        do {
            flagManager = false;
            generalManager = input.userInput(MANAGERNAME);
            if (validate.validateString(generalManager)) {
                display.displayMessage("Please enter digits only");
                flagManager = true;
            } else {
                generalManagerObject = validate.validateGeneralManager(leagueLOM, generalManager);
                if (null == generalManagerObject) {
                    display.displayMessage("Select option from the given list");
                    flagManager = true;
                }
            }
        } while (flagManager);
    }

    public void teamHeadCoachInput() {
        String headCoach;
        display.displayHeadCoach(leagueLOM);
        boolean flagHeadCoach;
        do {
            flagHeadCoach = false;
            headCoach = input.userInput(HEADCOACHNAME);
            if (validate.validateString(headCoach)) {
                display.displayMessage("Please enter digits only");
                flagHeadCoach = true;
            } else {
                headCoachObject = validate.validateHeadCoach(leagueLOM, headCoach);
                if (null == headCoachObject) {
                    display.displayMessage("Select option from the given list");
                    flagHeadCoach = true;
                }
            }
        } while (flagHeadCoach);
    }
}
