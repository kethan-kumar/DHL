/* @Author: Kethan Kumar */
package org.leaguemodel.interfaces;

public interface IValidationsInLeague {

    boolean validateConference(String conferenceName, ILeague league);

    boolean validateDivisions(String conferenceName, String divisionName, ILeague league);

    boolean validateConferenceList(ILeague league);

    boolean validateDivisionList(ILeague league);

    boolean validateTeamList(ILeague league);

    boolean validateCaptain(ILeague league);

    boolean validateTeamName(String teamName, ILeague league);
}
