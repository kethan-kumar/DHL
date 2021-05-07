/* @Author:  Siddhant Ashutosh */
package org.leaguemodel.interfaces;

import org.leaguesimulation.TeamOnScoreboard;

import java.util.List;

public interface IPlayerDraft {

    void sortTeam();

    void drafting(List<TeamOnScoreboard> teamsList);

    void maintainTeam(List<TeamOnScoreboard> teamsList);

    void startDrafting();
}
