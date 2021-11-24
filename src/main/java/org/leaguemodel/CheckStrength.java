/* @Author: Kethan Kumar */
package org.leaguemodel;

import org.apache.commons.lang3.StringUtils;
import org.leaguemodel.interfaces.ICheckStrength;
import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.IStrength;
import org.leaguemodel.interfaces.ITeam;

import java.util.List;

public class CheckStrength implements ICheckStrength {
    public double playerStrength(IStrength players) {
        int skating = players.getSkating();
        int shooting = players.getShooting();
        int checking = players.getChecking();
        int saving = players.getSaving();
        double strength = 0.0;
        String playersPosition = players.getPosition();

        if (StringUtils.equalsIgnoreCase(playersPosition, Position.forward.toString())) {
            strength = skating + shooting + (checking / 2.0);
        }
        if (StringUtils.equalsIgnoreCase(playersPosition, Position.defense.toString())) {
            strength = skating + checking + (shooting / 2.0);
        }
        if (StringUtils.equalsIgnoreCase(playersPosition, Position.goalie.toString())) {
            strength = skating + saving;
        }
        return strength;
    }

    public double teamStrength(ITeam team) {
        double strength = 0.0;
        List<IPlayers> playersList = team.getPlayers();
        for (int i = 0; i < team.getPlayers().size(); i++) {
            if (team.getPlayers().get(i).getNoOfDaysInjured() > 0) {
                strength += playerStrength((IStrength) playersList.get(i)) / 2.0;
            } else {
                strength += playerStrength((IStrength) playersList.get(i));
            }
        }
        return strength;
    }

    enum Position {
        goalie, forward, defense
    }
}
