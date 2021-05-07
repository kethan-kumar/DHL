package org.leaguemodel;

import org.leaguemodel.interfaces.ILeague;
import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.IRosterStatus;
import org.trading.abstractfactory.TradingAbstractFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RosterStatus implements IRosterStatus {
    private ILeague league;

    public RosterStatus(ILeague leagueLOM) {
        this.league = leagueLOM;
    }

    public void setStatusInactive() {
        for (int i = 0; i < league.getConferences().size(); i++) {
            for (int j = 0; j < league.getConferences().get(i).getDivisions().size(); j++) {
                for (int k = 0; k < league.getConferences().get(i).getDivisions().get(j).getTeams().size(); k++) {
                    for (int m = 0; m < league.getConferences().get(i).getDivisions().get(j).getTeams().get(k).getPlayers().size(); m++) {
                        league.getConferences().get(i).getDivisions().get(j).getTeams().get(k).getPlayers().get(m).setIsActive(false);
                    }
                }
            }
        }
    }

    public void setStatusActive() {
        int countGoalie = 0;
        int countSkaters = 0;
        TradingAbstractFactory tradingFactory = TradingAbstractFactory.instance();
        for (int i = 0; i < league.getConferences().size(); i++) {
            for (int j = 0; j < league.getConferences().get(i).getDivisions().size(); j++) {
                for (int k = 0; k < league.getConferences().get(i).getDivisions().get(j).getTeams().size(); k++) {
                    List<IPlayers> playersList = new ArrayList<>();
                    for (int m = 0; m < league.getConferences().get(i).getDivisions().get(j).getTeams().get(k).getPlayers().size(); m++) {
                        playersList.add(league.getConferences().get(i).getDivisions().get(j).getTeams().get(k).getPlayers().get(m));
                    }
                    Collections.sort(playersList, tradingFactory.createStatsComparator());
                    for (IPlayers iPlayers : playersList) {
                        if (countGoalie < 2) {
                            if (iPlayers.getPosition().equalsIgnoreCase(Position.GOALIE.toString())) {
                                countGoalie++;
                                iPlayers.setIsActive(true);
                            }
                        }
                        if (countSkaters < 18) {
                            if (iPlayers.getPosition().equalsIgnoreCase(Position.FORWARD.toString()) || iPlayers.getPosition().equalsIgnoreCase(Position.DEFENSE.toString())) {
                                countSkaters++;
                                iPlayers.setIsActive(true);
                            }
                        }
                    }
                }
            }
        }

    }

    private enum Position {
        GOALIE, FORWARD, DEFENSE
    }
}
