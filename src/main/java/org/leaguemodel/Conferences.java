/* @Author: Kethan Kumar */
package org.leaguemodel;

import org.leaguemodel.interfaces.IConference;
import org.leaguemodel.interfaces.IDivisions;

import java.util.List;

public class Conferences implements IConference {

    private String conferenceName;
    private List<IDivisions> divisions;

    public Conferences() {
        this.conferenceName = null;
        this.divisions = null;
    }

    public Conferences(String conferenceName, List<IDivisions> divisions) {

        this.conferenceName = conferenceName;
        this.divisions = divisions;
    }


    public String getConferenceName() {
        return conferenceName;
    }


    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }


    public List<IDivisions> getDivisions() {
        return divisions;
    }


    public void setDivisions(List<IDivisions> divisions) {
        this.divisions = divisions;
    }

}
