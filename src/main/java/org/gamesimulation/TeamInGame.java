/* @Author: Vikram Singh */

package org.gamesimulation;

import org.apache.log4j.Logger;
import org.gamesimulation.abstractfactory.GameAbstractFactory;
import org.gamesimulation.interfaces.IFormation;
import org.gamesimulation.interfaces.IPlayerInGame;
import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.ITeam;

import java.util.ArrayList;
import java.util.List;

public abstract class TeamInGame {
    public static final int PLAYERS_IN_FORWARD_LINE = 3;
    public static final int PLAYERS_IN_DEFENSE_LINE = 2;
    public static final int PLAYERS_IN_GOALIE_LINE = 1;
    private final GameAbstractFactory factory;
    private final Logger logger;
    protected ITeam team;
    private List<IFormation> forwardLines;
    private List<IFormation> defenseLines;
    private List<IFormation> goalies;
    private int goals = 0;
    private int shots = 0;
    private int penalties = 0;
    private int saves = 0;

    public TeamInGame(ITeam team) {
        this.logger = Logger.getLogger(TeamInGame.class.getName());
        this.team = team;
        this.factory = GameAbstractFactory.instance();
    }

    public List<IFormation> getForwardLines() {
        return forwardLines;
    }

    public List<IFormation> getDefenseLines() {
        return defenseLines;
    }

    public List<IFormation> getGoalies() {
        return goalies;
    }

    public ITeam getTeam() {
        return team;
    }


    public int getGoals() {
        return goals;
    }


    public void incrementGoals() {
        this.goals += 1;
    }


    public int getShots() {
        return shots;
    }


    public void incrementShots() {
        this.shots += 1;
    }


    public int getPenalties() {
        return penalties;
    }


    public void incrementPenalties() {
        this.penalties += 1;
    }


    public int getSaves() {
        return saves;
    }


    public void incrementSaves() {
        this.saves += 1;
    }

    public boolean buildLines() {
        logger.info("Building forward, defense, and goalie lines for shifts");
        generateForwardLines();
        generateDefenseLines();
        generateGoalies();
        return true;
    }

    private List<IFormation> generateForwardLines() {
        this.forwardLines = groupPlayers(GamePosition.position.FORWARD, PLAYERS_IN_FORWARD_LINE);
        return forwardLines;
    }

    private List<IFormation> generateDefenseLines() {
        this.defenseLines = groupPlayers(GamePosition.position.DEFENSE, PLAYERS_IN_DEFENSE_LINE);
        return defenseLines;
    }

    private List<IFormation> generateGoalies() {
        this.goalies = groupPlayers(GamePosition.position.GOALIE, PLAYERS_IN_GOALIE_LINE);
        return defenseLines;
    }

    private List<IFormation> groupPlayers(GamePosition.position position, int num) {
        List<IFormation> formationLines = new ArrayList<>();
        IFormation line = factory.createFormation(position);
        int i = 1;
        for (IPlayers player : team.getPlayers()) {
            if (player.getPosition().equalsIgnoreCase(position.toString())) {
                line.addPlayer(factory.createPlayerInGame(player));
                if (i % num == 0) {
                    formationLines.add(line);
                    line = factory.createFormation(position);
                }
                i++;
            }
        }
        return formationLines;
    }

    public List<IPlayerInGame> getPlayers() {
        List<IPlayerInGame> collection = new ArrayList<>();
        forwardLines.stream().map(IFormation::getPlayers).forEach(collection::addAll);
        defenseLines.stream().map(IFormation::getPlayers).forEach(collection::addAll);
        goalies.stream().map(IFormation::getPlayers).forEach(collection::addAll);
        return collection;
    }

    public abstract IFormation getForwardOnIce();

    public abstract IFormation getDefenseOnIce();

    public abstract IFormation getGoalieOnIce();

    public abstract void shuffle();

    public abstract void setInitialTeamOnIce();
}
