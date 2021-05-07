/* @Author: Kethan Kumar */

package org.io;

import org.io.abstractfactory.IOAbstractFactory;
import org.io.interfaces.IAwardCeremony;
import org.io.interfaces.IDisplay;

public class DisplayAwards implements IAwardCeremony {
    IDisplay display;
    private IOAbstractFactory ioFactory;

    public DisplayAwards() {
        ioFactory = IOAbstractFactory.instance();
        display = ioFactory.createDisplay();
    }

    public void beginTrophyDistribution() {
        display.displayMessage("*************** Trophy awards for the season ***************");
    }

    public void presidentTrophy(String highestPointsTeam) {
        display.displayMessage("The President Trophy award goes to *** " + highestPointsTeam + " ***");
    }

    public void calderMemorialTrophy(String bestDraftedPlayer) {
        display.displayMessage("The Calder Memorial Trophy award goes to *** " + bestDraftedPlayer + " ***");
    }

    public void vezinaTrophy(String bestGoalie) {
        display.displayMessage("The Vezina Trophy award goes to *** " + bestGoalie + " ***");
    }

    public void jackAdamAward(String bestCoach) {
        display.displayMessage("The Jack Adam's Award goes to *** " + bestCoach + " ***");
    }

    public void mauriceRichardTrophy(String topGoalScorer) {
        display.displayMessage("The Maurice Richard Trophy award goes to *** " + topGoalScorer + " ***");
    }

    public void robHawkeyMemorialCup(String bestDefenseMen) {
        display.displayMessage("The Rob Hawkey Memorial Cup goes to *** " + bestDefenseMen + " ***");
    }

    public void participationAward(String lowestPointsTeam) {
        display.displayMessage("The Participation Award goes to *** " + lowestPointsTeam + " ***");
    }

    public void endTrophyDistribution() {
        display.displayMessage("*************** Once again, congratulations to all the winners ***************");
    }
}
