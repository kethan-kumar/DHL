/* @Author: Kethan Kumar */
package org.leaguemodel;

import org.apache.log4j.Logger;
import org.leaguemodel.interfaces.IInjuries;
import org.leaguemodel.interfaces.IInjurySystem;
import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.ITeam;

public class InjurySystem implements IInjurySystem {


    public void checkTeamInjuries(ITeam team, IInjuries injuries) {
        Logger logger = Logger.getLogger(InjurySystem.class.getName());
        logger.info("Checking player injuries for Team: " + team.getTeamName());
        logger.debug("Initiating Check for player injuries for Team: " + team.getTeamName());
        for (int i = 0; i < team.getPlayers().size(); i++) {
            isPlayerInjured(injuries, team.getPlayers().get(i));
        }
    }

    public boolean isPlayerInjured(IInjuries injuries, IPlayers players) {
        int randomInjuryChance = (int) (injuries.getRandomInjuryChance() * 100);
        int injuryDaysLow = injuries.getInjuryDaysLow();
        int injuryDaysHigh = injuries.getInjuryDaysHigh();
        int range = injuryDaysHigh - injuryDaysLow + 1;
        int randomValue = (int) (Math.random() * 100 + 1);
        int injuredDays = (int) (Math.random() * range + injuryDaysLow);

        boolean injuryStatus = false;

        if (randomValue <= randomInjuryChance && players.getNoOfDaysInjured() == -1) {
            players.setNoOfDaysInjured(injuredDays);
            injuryStatus = true;
            Logger logger = Logger.getLogger(InjurySystem.class.getName());
            logger.debug("Player: " + players.getPlayerName() + " got injured");
        }
        return injuryStatus;
    }

    public void isPlayerRecovered(IPlayers players) {
        if (players.getNoOfDaysInjured() > 1) {
            players.setNoOfDaysInjured(players.getNoOfDaysInjured() - 1);
        } else {
            players.setNoOfDaysInjured(-1);
        }
    }
}
