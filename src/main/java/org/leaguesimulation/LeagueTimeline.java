/* @Author: Vikram Singh */

package org.leaguesimulation;

import org.leaguesimulation.interfaces.ILeagueTimeline;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.ChronoUnit.DAYS;

public class LeagueTimeline implements ILeagueTimeline {
    public static final int NUM_ONE = 1;
    public static final int NUM_TWO = 2;
    LocalDate seasonStart;
    LocalDate playOffStartDate;
    LocalDate seasonEnd;
    LocalDate currentDate;

    public LeagueTimeline(LocalDate seasonStart) {
        this.seasonStart = seasonStart;
        this.currentDate = seasonStart;
        this.seasonEnd = getEndOfSeasonDate();
    }

    public LocalDate getTradeDeadline() {
        LocalDate temporalDate = LocalDate.of(seasonStart.getYear() + NUM_ONE, Month.FEBRUARY, NUM_ONE);
        LocalDate tradeDeadline = temporalDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY));
        return tradeDeadline;
    }

    public LocalDate getEndOfRegularSeason() {
        LocalDate temporalDate = LocalDate.of(seasonStart.getYear() + NUM_ONE, Month.APRIL, NUM_ONE);
        LocalDate endOfRegularSeasonDate = temporalDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY));
        return endOfRegularSeasonDate;
    }

    public LocalDate getEndOfSeasonDate() {
        return LocalDate.of(seasonStart.plusYears(NUM_ONE).getYear(), Month.JUNE, NUM_ONE);
    }

    public LocalDate getPlayOffStartDate() {
        LocalDate temporalDate = LocalDate.of(seasonStart.getYear() + NUM_ONE, Month.APRIL, NUM_ONE);
        playOffStartDate = temporalDate.with(TemporalAdjusters.dayOfWeekInMonth(NUM_TWO, DayOfWeek.WEDNESDAY));
        return playOffStartDate;
    }

    public void setPlayOffStartDate(LocalDate playOffStartDate) {
        this.playOffStartDate = playOffStartDate;
    }

    public boolean isTradePossible() {
        return getTradeDeadline().isAfter(currentDate) || getTradeDeadline().equals(currentDate);
    }

    public boolean isRegularSeason() {
        return getEndOfRegularSeason().isAfter(currentDate) || getEndOfRegularSeason().equals(currentDate);
    }

    public int daysLeftOfRegularSeason() {
        return daysBetweenDates(currentDate, getEndOfRegularSeason());
    }

    public int daysLeftOfPlayOffSeason() {
        return daysBetweenDates(currentDate, getEndOfSeasonDate());
    }

    public int daysBetweenDates(LocalDate dateFrom, LocalDate dateUpto) {
        return (int) DAYS.between(dateFrom, dateUpto) + NUM_ONE;
    }

    public int daysOfPlayOff() {
        return daysBetweenDates(playOffStartDate, getEndOfSeasonDate());
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public LocalDate incrementDateByOne() {
        currentDate = currentDate.plusDays(NUM_ONE);
        return currentDate;
    }

    public int daysPassedInLeague() {
        return daysBetweenDates(seasonStart, currentDate);
    }

    public void setCurrentDateFromDB(LocalDate leagueDate) {
        currentDate = leagueDate;
    }

}
