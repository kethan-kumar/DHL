/* @Author:  Siddhant Ashutosh */
package org.leaguemodel.interfaces;

import java.util.List;

public interface IConference {
    String getConferenceName();

    void setConferenceName(String conferenceName);

    List<IDivisions> getDivisions();

    void setDivisions(List<IDivisions> divisions);
}
