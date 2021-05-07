package org.statemachine;

import org.io.abstractfactory.IOAbstractFactory;
import org.io.interfaces.IAwardCeremony;
import org.io.interfaces.IDisplay;
import org.statemachine.interfaces.IContext;
import org.statemachine.interfaces.IGameState;
import org.statemachine.interfaces.ISerialization;
import org.trophysystem.abstractfactory.TrophyAbstractFactory;
import org.trophysystem.interfaces.IAwardWinners;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrophyState implements IGameState {
    private static final String HISTORICAL_DATA = "HistoricalData.json";
    private static final String AWARD_DISTRIBUTION = "Awards distribution:";
    IDisplay display;
    IAwardCeremony awardDisplay;
    IAwardWinners awardWinner;
    LocalDate leagueDate;
    ISerialization serialization;
    List<String> awardWinnersList;
    private IContext gameContext;
    private TrophyAbstractFactory trophyStateFactory;
    private IOAbstractFactory ioFactory;

    public TrophyState(IContext gameContext, LocalDate leagueDate) {
        trophyStateFactory = TrophyAbstractFactory.instance();
        ioFactory = IOAbstractFactory.instance();
        this.gameContext = gameContext;
        this.leagueDate = leagueDate;
        display = ioFactory.createDisplay();
        awardDisplay = ioFactory.createDisplayAwards();
        awardWinner = trophyStateFactory.createAwardWinners();
        serialization = new Serialization();
        awardWinnersList = new ArrayList<>();
    }

    public void entryProcess() {
        awardDisplay.beginTrophyDistribution();
        awardWinnersList.add(awardWinner.getLowestPointsTeam());
        awardWinnersList.add(awardWinner.getBestDefenseMen());
        awardWinnersList.add(awardWinner.getTopGoalScorer());
        awardWinnersList.add(awardWinner.getBestCoach());
        awardWinnersList.add(awardWinner.getBestGoalie());
        awardWinnersList.add(awardWinner.getBestDraftedPlayer());
        awardWinnersList.add(awardWinner.getHighestPointsTeam());
        awardWinnersList.add(leagueDate.toString());
    }

    public void doProcess() {
        for (int i = awardWinnersList.size() - 1; i >= 0; i--) {
            display.displayMessage(AWARD_DISTRIBUTION + awardWinnersList.get(i));
            awardDisplay.presidentTrophy(awardWinnersList.get(i + 1));
            awardDisplay.calderMemorialTrophy(awardWinnersList.get(i + 2));
            awardDisplay.vezinaTrophy(awardWinnersList.get(i + 3));
            awardDisplay.jackAdamAward(awardWinnersList.get(i + 4));
            awardDisplay.mauriceRichardTrophy(awardWinnersList.get(i + 5));
            awardDisplay.robHawkeyMemorialCup(awardWinnersList.get(i + 6));
            awardDisplay.participationAward(awardWinnersList.get(i + 7));
        }
        gameContext.setCurrentState(null);
    }

    public void exitProcess() {
        awardDisplay.endTrophyDistribution();
    }
}
