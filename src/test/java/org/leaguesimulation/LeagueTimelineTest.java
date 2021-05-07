/* @Author: Vikram Singh */

package org.leaguesimulation;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;

public class LeagueTimelineTest {
    private LocalDate seasonStart;
    private LeagueTimeline timeline;

    @Before
    public void setUp() throws Exception {
        seasonStart = LocalDate.of(2020, Month.SEPTEMBER, 30);
        timeline = new LeagueTimeline(seasonStart);
    }

    @Test
    public void getTradeDeadline() {
        LocalDate tradeDeadline = timeline.getTradeDeadline();
        LocalDate expectedTradeDeadline = LocalDate.of(2021, Month.FEBRUARY, 22);
        assertEquals(expectedTradeDeadline, tradeDeadline);
    }

    @Test
    public void getEndOfRegularSeason() {
        LocalDate tradeDeadline = timeline.getEndOfRegularSeason();
        LocalDate expectedTradeDeadline = LocalDate.of(2021, Month.APRIL, 3);
        assertEquals(expectedTradeDeadline, tradeDeadline);
    }

    @Test
    public void getEndOfSeasonDate() {
        LocalDate endOfSeasonDate = timeline.getEndOfSeasonDate();
        LocalDate expectedEndOfSeasonDate = LocalDate.of(2021, Month.JUNE, 1);
        assertEquals(expectedEndOfSeasonDate, endOfSeasonDate);
    }

    @Test
    public void getPlayOffStartDate() {
        LocalDate playOffStartDate = timeline.getPlayOffStartDate();
        LocalDate expectedPlayOffStartDate = LocalDate.of(2021, Month.APRIL, 14);
        assertEquals(expectedPlayOffStartDate, playOffStartDate);
    }

    @Test
    public void isTradePossible() {
        LocalDate currentDate = LocalDate.of(2021, Month.JANUARY, 1);
        timeline.setCurrentDate(currentDate);
        Boolean isTradePossible = timeline.isTradePossible();
        assertEquals(true, isTradePossible);
    }

    @Test
    public void isRegularSeason() {
        LocalDate currentDate = LocalDate.of(2021, Month.JANUARY, 1);
        timeline.setCurrentDate(currentDate);
        Boolean isRegularSeason = timeline.isRegularSeason();
        assertEquals(true, isRegularSeason);
    }

    @Test
    public void daysLeftOfRegularSeason() {
        LocalDate currentDate = LocalDate.of(2021, Month.APRIL, 1);
        timeline.setCurrentDate(currentDate);
        int daysLeftOfRegularSeason = timeline.daysLeftOfRegularSeason();
        assertEquals(3, daysLeftOfRegularSeason);
    }

    @Test
    public void daysBetweenDates() {
        LocalDate dateFrom = LocalDate.of(2021, Month.APRIL, 1);
        LocalDate dateTo = LocalDate.of(2021, Month.APRIL, 3);
        int daysLeftOfRegularSeason = timeline.daysBetweenDates(dateFrom, dateTo);
        assertEquals(3, daysLeftOfRegularSeason);
    }

    @Test
    public void daysOfPlayOff() {
        LocalDate expectedPlayOffStartDate = LocalDate.of(2021, Month.APRIL, 14);
        timeline.setPlayOffStartDate(expectedPlayOffStartDate);
        int daysOfPlayOffs = timeline.daysOfPlayOff();
        assertEquals(49, daysOfPlayOffs);
    }

    @Test
    public void getCurrentDate() {
        LocalDate currentDate = LocalDate.of(2021, Month.APRIL, 1);
        timeline.setCurrentDate(currentDate);
        LocalDate expectedCurrentDate = timeline.getCurrentDate();
        assertEquals(expectedCurrentDate, currentDate);
    }

    @Test
    public void incrementDateByOne() {
        LocalDate currentDate = LocalDate.of(2021, Month.APRIL, 1);
        timeline.setCurrentDate(currentDate);
        LocalDate nextDate = timeline.incrementDateByOne();
        assertEquals(currentDate.plusDays(1), nextDate);
    }
}
