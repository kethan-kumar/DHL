package org.leaguesimulation.interfaces;

import java.time.LocalDate;

public interface ILeagueTimeline {
    LocalDate getTradeDeadline();

    LocalDate getEndOfRegularSeason();

    LocalDate getEndOfSeasonDate();

    LocalDate getPlayOffStartDate();

    void setPlayOffStartDate(LocalDate playOffStartDate);

    boolean isTradePossible();

    boolean isRegularSeason();

    int daysLeftOfRegularSeason();

    int daysLeftOfPlayOffSeason();

    int daysBetweenDates(LocalDate dateFrom, LocalDate dateUpto);

    int daysOfPlayOff();

    LocalDate getCurrentDate();

    void setCurrentDate(LocalDate currentDate);

    LocalDate incrementDateByOne();

    int daysPassedInLeague();

    void setCurrentDateFromDB(LocalDate leagueDate);
}
