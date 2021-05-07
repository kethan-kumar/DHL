/* @Author:  Siddhant Ashutosh */
package org.leaguemodel;

import org.leaguemodel.interfaces.IGeneratePlayers;

import java.util.ArrayList;
import java.util.List;

public class GeneratePlayers implements IGeneratePlayers {
    public List<Players> generate(int teamPlayer) {
        Players player = new Players();
        List<Players> playersList = new ArrayList<>();
        String playerPosition;
        int i = 0;
        int forward = teamPlayer / 2;
        int defence = 2 * teamPlayer / 5;
        int goalies = teamPlayer / 10;
        while (i < teamPlayer) {
            String playerName = "Player" + i;
            player.setPlayerName(playerName);
            if (forward > 0) {
                playerPosition = "forward";
                player.setPosition(playerPosition);
                player.setAge(26);
                int forwardSkating = (int) (Math.random() * (20 - 12)) + 12;
                player.setSkating(forwardSkating);
                int forwardShooting = (int) (Math.random() * (20 - 12)) + 12;
                player.setShooting(forwardShooting);
                int forwardChecking = (int) (Math.random() * (18 - 9)) + 9;
                player.setChecking(forwardChecking);
                int forwardSaving = (int) (Math.random() * (7 - 1)) + 1;
                player.setSaving(forwardSaving);
                playersList.add(player);
                forward--;
            } else if (defence > 0) {
                playerPosition = "defense";
                player.setPosition(playerPosition);
                player.setAge(26);
                int defenseSkating = (int) (Math.random() * (19 - 10)) + 10;
                player.setSkating(defenseSkating);
                int defenseShooting = (int) (Math.random() * (18 - 9)) + 9;
                player.setShooting(defenseShooting);
                int defenseChecking = (int) (Math.random() * (20 - 12)) + 12;
                player.setChecking(defenseChecking);
                int defenseSaving = (int) (Math.random() * (12 - 1)) + 1;
                player.setSaving(defenseSaving);
                playersList.add(player);
                defence--;
            } else if (goalies > 0) {
                playerPosition = "goalie";
                player.setPosition(playerPosition);
                player.setAge(26);
                int goalieSkating = (int) (Math.random() * (15 - 8)) + 8;
                player.setSkating(goalieSkating);
                int goalieShooting = (int) (Math.random() * (10 - 1)) + 1;
                player.setShooting(goalieShooting);
                int goalieChecking = (int) (Math.random() * (12 - 1)) + 1;
                player.setChecking(goalieChecking);
                int goalieSaving = (int) (Math.random() * (20 - 12)) + 12;
                player.setSaving(goalieSaving);
                playersList.add(player);
                goalies--;
            }
            i++;
        }
        return playersList;
    }
}
