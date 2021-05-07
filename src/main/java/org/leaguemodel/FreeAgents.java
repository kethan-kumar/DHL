/* @Author:  Siddhant Ashutosh */
package org.leaguemodel;

import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.IStrength;

public class FreeAgents extends Players implements IStrength, IPlayers {

    public FreeAgents() {
        super();
    }

    public FreeAgents(String playerPosition, String playerName, Integer age, Integer skating, Integer shooting, Integer checking, Integer saving) {
        super(playerPosition, playerName, age, skating, shooting, checking, saving);
    }

}
