package org.leaguesimulation.interfaces;

import org.leaguesimulation.TeamOnScoreboard;

public interface ISeed {
    void seed(int numberOfWildCards);

    void seed(TeamOnScoreboard wildCard);
}
