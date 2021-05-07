/* @Author: Kethan Kumar */

package org.statemachine;

import org.apache.log4j.Logger;
import org.io.abstractfactory.IOAbstractFactory;
import org.io.interfaces.IDisplay;
import org.leaguemodel.InjurySystem;
import org.leaguemodel.interfaces.IInjuries;
import org.leaguemodel.interfaces.IInjurySystem;
import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.ITeam;
import org.statemachine.interfaces.ITrainingSystem;
import org.trophysystem.BestPerformanceSubject;
import org.trophysystem.abstractfactory.TrophyAbstractFactory;
import org.trophysystem.interfaces.IPerformanceObserver;

public class TrainingSystem extends BestPerformanceSubject implements ITrainingSystem {
    private final IDisplay display;
    private final Logger logger;
    protected IInjurySystem playerInjury = new InjurySystem();
    private IPerformanceObserver headCoachObserver;
    private TrophyAbstractFactory trophyState;
    private IOAbstractFactory ioFactory;

    public TrainingSystem() {
        trophyState = TrophyAbstractFactory.instance();
        ioFactory = IOAbstractFactory.instance();
        display = ioFactory.createDisplay();
        logger = Logger.getLogger(TrainingSystem.class.getName());
        headCoachObserver = trophyState.createHeadCoachObserver();
    }

    public void checkStatistics(IInjuries injuries, ITeam team) {
        logger.info("Training players of Team: " + team.getTeamName());
        logger.debug("Initiating Training for players of Team: " + team.getTeamName());
        double headCoachSkating = team.getHeadCoach().getSkating();
        double headCoachChecking = team.getHeadCoach().getChecking();
        double headCoachSaving = team.getHeadCoach().getSaving();
        double headCoachShooting = team.getHeadCoach().getShooting();
        for (int i = 0; i < team.getPlayers().size(); i++) {
            if (team.getPlayers().get(i).getNoOfDaysInjured() == -1) {
                checkSkatingStatistics(headCoachSkating, team.getPlayers().get(i), injuries);
                checkCheckingStatistics(headCoachChecking, team.getPlayers().get(i), injuries);
                checkSavingStatistics(headCoachSaving, team.getPlayers().get(i), injuries);
                checkShootingStatistics(headCoachShooting, team.getPlayers().get(i), injuries);
            }
        }
        this.performerName = team.getHeadCoach().getName();
        this.attachPlayerObserver(headCoachObserver);
        notifyObserver();
    }

    public void checkSkatingStatistics(double headCoachSkating, IPlayers player, IInjuries injury) {
        logger.debug("Checking if skating skill for player: " + player.getPlayerName() + " has improved");
        if (player.getNoOfDaysInjured() == -1) {
            double valueSkating = Math.random();
            if (valueSkating < headCoachSkating) {
                this.score++;
                player.setSkating(player.getSkating() + 1);
                display.displayMessage("Skating stats increased for:" + player.getPlayerName());
            } else {
                playerInjury.isPlayerInjured(injury, player);
            }
        }
    }

    public void checkCheckingStatistics(double headCoachChecking, IPlayers player, IInjuries injury) {
        logger.debug("Checking if checking skill for player: " + player.getPlayerName() + " has improved");
        if (player.getNoOfDaysInjured() == -1) {
            double valueChecking = Math.random();
            if (valueChecking < headCoachChecking) {
                this.score++;
                player.setChecking(player.getChecking() + 1);
                display.displayMessage("Checking stats increased for:" + player.getPlayerName());
            } else {
                playerInjury.isPlayerInjured(injury, player);
            }
        }
    }

    public void checkSavingStatistics(double headCoachSkating, IPlayers player, IInjuries injury) {
        logger.debug("Checking if saving skill for player: " + player.getPlayerName() + " has improved");
        if (player.getNoOfDaysInjured() == -1) {
            double valueSaving = Math.random();
            if (valueSaving < headCoachSkating) {
                this.score++;
                player.setSaving(player.getSaving() + 1);
                display.displayMessage("Saving stats increased for:" + player.getPlayerName());
            } else {
                playerInjury.isPlayerInjured(injury, player);
            }
        }
    }

    public void checkShootingStatistics(double headCoachShooting, IPlayers player, IInjuries injury) {
        logger.debug("Checking if shooting skill for player: " + player.getPlayerName() + " has improved");
        if (player.getNoOfDaysInjured() == -1) {
            double valueShooting = Math.random();
            if (valueShooting < headCoachShooting) {
                this.score++;
                player.setShooting(player.getShooting() + 1);
                display.displayMessage("Shooting stats increased for:" + player.getPlayerName());
            } else {
                playerInjury.isPlayerInjured(injury, player);
            }
        }
    }
}
