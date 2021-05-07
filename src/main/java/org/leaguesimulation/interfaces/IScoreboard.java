package org.leaguesimulation.interfaces;

import org.leaguesimulation.TeamOnScoreboard;

import java.util.List;

public interface IScoreboard {
    List<TeamOnScoreboard> loadScoreboard();

    List<TeamOnScoreboard> getScoreboard();

    List<TeamOnScoreboard> sort();
}
