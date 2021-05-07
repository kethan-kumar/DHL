package org.gamesimulation.interfaces;

import org.gamesimulation.TeamInGame;

public interface IPenalty {
    void execute(TeamInGame offense, TeamInGame defense);
}
