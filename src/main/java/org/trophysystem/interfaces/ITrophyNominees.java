/* @Author: Kethan Kumar */

package org.trophysystem.interfaces;

import org.leaguesimulation.interfaces.IScoreboard;

public interface ITrophyNominees {

    void teamNominees(IScoreboard leagueScoreboard);

    void draftNominee(String draftPlayerName, int draftPlayerPoints);

    void goalieNominee(String goalieName, int goaliePoints);

    void coachNominees(String coachName, int coachPoints);

    void goalScorerNominees(String goalScorerName, int goalScorerPoints);

    void defenseMenNominees(String defenseMenName, int defenseMenPoints);
}
