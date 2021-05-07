package org.statemachine.interfaces;

import org.leaguemodel.interfaces.IInjuries;
import org.leaguemodel.interfaces.ITeam;

public interface ITrainingSystem {

    void checkStatistics(IInjuries injuries, ITeam team);
}
