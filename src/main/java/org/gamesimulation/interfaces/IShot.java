package org.gamesimulation.interfaces;

import org.gamesimulation.TeamInGame;

public interface IShot {
    boolean execute(TeamInGame offense, TeamInGame defense);
}
