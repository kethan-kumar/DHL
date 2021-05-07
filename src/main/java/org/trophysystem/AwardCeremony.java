/* @Author: Kethan Kumar */

package org.trophysystem;

import org.io.abstractfactory.IOAbstractFactory;
import org.io.interfaces.IDisplay;
import org.leaguesimulation.interfaces.IScoreboard;
import org.leaguesimulation.interfaces.ITeamOnScoreboard;
import org.trophysystem.interfaces.IAwardWinners;
import org.trophysystem.interfaces.ITrophyNominees;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AwardCeremony implements ITrophyNominees, IAwardWinners {
    private static IDisplay display;
    private static Map<String, Integer> bestHeadCoaches;
    private static Map<String, Integer> bestGoalies;
    private static Map<String, Integer> bestDraftPlayers;
    private static Map<String, Integer> bestGoalScorers;
    private static Map<String, Integer> bestDefenseMens;
    private static IOAbstractFactory ioFactory;
    private static AwardCeremony uniqueInstance = null;
    String highestPointsTeam;
    String bestDraftedPlayer;
    String bestGoalie;
    String bestCoach;
    String topGoalScorer;
    String bestDefenseMen;
    String lowestPointsTeam;
    int bestCoachStats = 0;
    int bestGoaliePoints = 0;
    int bestDraftPlayerPoints = 0;
    int bestGoalScorerPoints = 0;
    int bestDefenseMenPoints = 0;

    public static AwardCeremony instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new AwardCeremony();
        }
        ioFactory = IOAbstractFactory.instance();
        display = ioFactory.createDisplay();
        bestHeadCoaches = new HashMap<>();
        bestGoalies = new HashMap<>();
        bestDraftPlayers = new HashMap<>();
        bestGoalScorers = new HashMap<>();
        bestDefenseMens = new HashMap<>();
        return uniqueInstance;
    }

    public String getHighestPointsTeam() {
        return highestPointsTeam;
    }

    public String getBestDraftedPlayer() {
        return bestDraftedPlayer;
    }

    public String getBestGoalie() {
        return bestGoalie;
    }

    public String getBestCoach() {
        return bestCoach;
    }

    public String getTopGoalScorer() {
        return topGoalScorer;
    }

    public String getBestDefenseMen() {
        return bestDefenseMen;
    }

    public String getLowestPointsTeam() {
        return lowestPointsTeam;
    }

    public void teamNominees(IScoreboard leagueScoreboard) {
        String teamName;
        int score;
        Map<String, Integer> teams = new HashMap<>();

        for (ITeamOnScoreboard team : leagueScoreboard.getScoreboard()) {
            teamName = team.getTeam().getTeamName();
            score = team.getScore();
            teams.put(teamName, score);
        }
        List<Map.Entry<String, Integer>> teamSorted = teams.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        highestPointsTeam = teamSorted.get(0).getKey();
        lowestPointsTeam = teamSorted.get(teamSorted.size() - 1).getKey();
    }

    public void draftNominee(String draftPlayerName, int draftPlayerPoints) {
        String nominee = nominees(bestDraftPlayers, draftPlayerName, draftPlayerPoints);
        String[] draftPlayerDetails = nominee.split(",");
        draftPlayerName = draftPlayerDetails[0];
        draftPlayerPoints = Integer.parseInt(draftPlayerDetails[1]);
        if (bestDraftPlayerPoints < draftPlayerPoints) {
            bestDraftedPlayer = draftPlayerName;
            bestDraftPlayerPoints = draftPlayerPoints;
        }
    }

    public void goalieNominee(String goalieName, int goaliePoints) {
        String nominee = nominees(bestGoalies, goalieName, goaliePoints);
        String[] goalieDetails = nominee.split(",");
        goalieName = goalieDetails[0];
        goaliePoints = Integer.parseInt(goalieDetails[1]);
        if (bestGoaliePoints < goaliePoints) {
            bestGoalie = goalieName;
            bestGoaliePoints = goaliePoints;
        }
    }

    public void coachNominees(String coachName, int coachPoints) {
        String nominee = nominees(bestHeadCoaches, coachName, coachPoints);
        String[] coachDetails = nominee.split(",");
        coachName = coachDetails[0];
        coachPoints = Integer.parseInt(coachDetails[1]);
        if (bestCoachStats < coachPoints) {
            bestCoach = coachName;
            bestCoachStats = coachPoints;
        }
    }

    public void goalScorerNominees(String goalScorerName, int goalScorerPoints) {
        String nominee = nominees(bestGoalScorers, goalScorerName, goalScorerPoints);
        String[] goalScorerDetails = nominee.split(",");
        goalScorerName = goalScorerDetails[0];
        goalScorerPoints = Integer.parseInt(goalScorerDetails[1]);
        if (bestGoalScorerPoints < goalScorerPoints) {
            topGoalScorer = goalScorerName;
            bestGoalScorerPoints = goalScorerPoints;
        }
    }

    public void defenseMenNominees(String defenseMenName, int defenseMenPoints) {
        String nominee = nominees(bestDefenseMens, defenseMenName, defenseMenPoints);
        String[] defenseMenDetails = nominee.split(",");
        defenseMenName = defenseMenDetails[0];
        defenseMenPoints = Integer.parseInt(defenseMenDetails[1]);
        if (bestDefenseMenPoints < defenseMenPoints) {
            bestDefenseMen = defenseMenName;
            bestDefenseMenPoints = defenseMenPoints;
        }
    }

    public String nominees(Map<String, Integer> nominatedPerformers, String nomineeName, int points) {
        String nomineeDetails;
        if (nominatedPerformers.containsKey(nomineeName)) {
            nominatedPerformers.put(nomineeName, nominatedPerformers.get(nomineeName) + points);
        } else {
            nominatedPerformers.put(nomineeName, points);
        }
        List<Map.Entry<String, Integer>> sortedNominees = nominatedPerformers.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        nomineeName = sortedNominees.get(sortedNominees.size() - 1).getKey();
        points = sortedNominees.get(sortedNominees.size() - 1).getValue();
        nomineeDetails = nomineeName + "," + points;
        return nomineeDetails;
    }
}
