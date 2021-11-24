/* @Author: Kethan Kumar */
package org.leaguemodel;

import org.apache.commons.lang3.StringUtils;
import org.leaguemodel.interfaces.ILeague;
import org.leaguemodel.interfaces.IValidationsInLeague;

import java.util.ArrayList;
import java.util.List;

public class ValidationsInLeague implements IValidationsInLeague {

    public boolean validateConference(String conferenceName, ILeague league) {
        for (int i = 0; i < league.getConferences().size(); i++) {
            if (StringUtils.equalsIgnoreCase(league.getConferences().get(i).getConferenceName(), conferenceName)) {
                return true;
            }
        }
        return false;
    }

    public boolean validateDivisions(String conferenceName, String divisionName, ILeague league) {
        for (int i = 0; i < league.getConferences().size(); i++) {
            if (league.getConferences().get(i).getConferenceName().equalsIgnoreCase((conferenceName))) {
                for (int j = 0; j < league.getConferences().get(i).getDivisions().size(); j++) {
                    if (league.getConferences().get(i).getDivisions().get(j).getDivisionName().equalsIgnoreCase(divisionName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean validateConferenceList(ILeague league) {
        List<String> conferenceNames = new ArrayList<>();
        for (int i = 0; i < league.getConferences().size(); i++) {
            if (conferenceNames.contains(league.getConferences().get(i).getConferenceName())) {
                conferenceNames.clear();
                return true;
            } else {
                conferenceNames.add(league.getConferences().get(i).getConferenceName());
            }
        }
        return false;
    }

    public boolean validateDivisionList(ILeague league) {
        List<String> divisionNames = new ArrayList<>();
        for (int i = 0; i < league.getConferences().size(); i++) {
            for (int j = 0; j < league.getConferences().get(i).getDivisions().size(); j++) {
                if (divisionNames.contains(league.getConferences().get(i).getDivisions().get(j).getDivisionName())) {
                    divisionNames.clear();
                    return true;
                } else {
                    divisionNames.add(league.getConferences().get(i).getDivisions().get(j).getDivisionName());
                }
            }
            divisionNames.clear();
        }
        return false;
    }

    public boolean validateTeamList(ILeague league) {
        List<String> teamNames = new ArrayList<>();
        for (int i = 0; i < league.getConferences().size(); i++) {
            for (int j = 0; j < league.getConferences().get(i).getDivisions().size(); j++) {
                for (int k = 0; k < league.getConferences().get(i).getDivisions().get(j).getTeams().size(); k++) {
                    if (teamNames.contains(league.getConferences().get(i).getDivisions().get(j).getTeams().get(k).getTeamName())) {
                        teamNames.clear();
                        return true;
                    } else {
                        teamNames.add(league.getConferences().get(i).getDivisions().get(j).getTeams().get(k).getTeamName());
                    }
                }
            }
        }
        return false;
    }

    public boolean validateCaptain(ILeague league) {
        int countCaptain = 0;
        for (int i = 0; i < league.getConferences().size(); i++) {
            for (int j = 0; j < league.getConferences().get(i).getDivisions().size(); j++) {
                for (int k = 0; k < league.getConferences().get(i).getDivisions().get(j).getTeams().size(); k++) {
                    for (int m = 0; m < league.getConferences().get(i).getDivisions().get(j).getTeams().get(k).getPlayers().size(); m++) {
                        if (league.getConferences().get(i).getDivisions().get(j).getTeams().get(k).getPlayers().get(m).isCaptain()) {
                            countCaptain++;
                        }
                    }
                    if (countCaptain == 1) {
                        countCaptain = 0;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean validateTeamName(String teamName, ILeague league) {
        for (int i = 0; i < league.getConferences().size(); i++) {
            for (int j = 0; j < league.getConferences().get(i).getDivisions().size(); j++) {
                for (int k = 0; k < league.getConferences().get(i).getDivisions().get(j).getTeams().size(); k++) {
                    if (league.getConferences().get(i).getDivisions().get(j).getTeams().get(k).getTeamName().equalsIgnoreCase(teamName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
