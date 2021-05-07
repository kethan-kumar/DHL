package org.gamesimulation.interfaces;

public interface ITeamOnIce {
    IFormation getForwardOnIce();

    IFormation getDefenseOnIce();

    IFormation getGoalieOnIce();

    void buildLines();

    void shuffle();

    void setInitialTeamOnIce();
}
