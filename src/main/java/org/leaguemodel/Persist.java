/* @Author:  Siddhant Ashutosh */
package org.leaguemodel;

import org.apache.log4j.Logger;
import org.leaguemodel.interfaces.ILeague;
import org.leaguemodel.interfaces.IPersistence;
import org.leaguemodel.interfaces.ITeam;
import org.statemachine.Serialization;
import org.statemachine.interfaces.ISerialization;

public class Persist implements IPersistence {
    private static final String FILENAME = "SerializedLeague.json";

    public void saveTeam(ILeague league) {
        Logger logger = Logger.getLogger(Persist.class.getName());
        logger.info("Saving simulation progress..");
        ISerialization serial = new Serialization();
        serial.serialize(league);
    }

    public ILeague loadTeam(String leagueName, String teamName, ILeague league) {
        ISerialization serial = new Serialization();
        ILeague leagueObj = serial.deserialize(FILENAME);
        return leagueObj;
    }

    public ILeague appendTeam(String divisionName, String conferenceName, ITeam team, ILeague league) {
        for (int i = 0; i < league.getConferences().size(); i++) {
            if (league.getConferences().get(i).getConferenceName().equalsIgnoreCase(conferenceName)) {
                for (int j = 0; j < league.getConferences().get(i).getDivisions().size(); j++) {
                    if (league.getConferences().get(i).getDivisions().get(j).getDivisionName().equalsIgnoreCase(divisionName)) {
                        league.getConferences().get(i).getDivisions().get(j).getTeams().add(team);
                        return league;
                    }
                }
            }
        }
        return null;
    }

}
