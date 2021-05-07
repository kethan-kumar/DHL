package org.leaguesimulation.interfaces;

import java.util.List;

public interface IScheduler {
    List<IGame> schedule(int totalGamesPerTeam);
}
