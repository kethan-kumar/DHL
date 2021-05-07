package org.io.interfaces;

public interface IAwardCeremony {
    void beginTrophyDistribution();

    void presidentTrophy(String highestPointsTeam);

    void calderMemorialTrophy(String bestDraftedPlayer);

    void vezinaTrophy(String bestGoalie);

    void jackAdamAward(String bestCoach);

    void mauriceRichardTrophy(String topGoalScorer);

    void robHawkeyMemorialCup(String bestDefenseMen);

    void participationAward(String lowestPointsTeam);

    void endTrophyDistribution();
}
