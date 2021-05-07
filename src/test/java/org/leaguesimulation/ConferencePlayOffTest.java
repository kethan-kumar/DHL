/* @Author: Vikram Singh */

package org.leaguesimulation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.leaguesimulation.abstractfactory.LeagueAbstractFactory;
import org.leaguesimulation.abstractfactory.LeagueSimulationFactory;
import org.leaguesimulation.interfaces.IConferenceScoreboard;
import org.leaguesimulation.interfaces.IScoreboard;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LeagueAbstractFactory.class, LeagueSimulationFactory.class})
public class ConferencePlayOffTest {

    @Mock
    IConferenceScoreboard conferenceScoreboard;
    @Mock
    List<IScoreboard> divisionScoreboards;
    @Mock
    IScoreboard divisionScoreboard;
    @Mock
    List<TeamOnScoreboard> teamOnScoreboards;
    @Mock
    TeamOnScoreboard teamOnScoreboard;
    @Mock
    TeamOnScoreboard teamOnScoreboardTwo;
    @Mock
    LeagueSimulationFactory factory;

    @Before
    public void setUp() {
        teamOnScoreboard.setScore(10);
        teamOnScoreboardTwo.setScore(20);
        divisionScoreboards = new ArrayList<>();
        teamOnScoreboards = new ArrayList<>();
        teamOnScoreboards.add(teamOnScoreboard);
        teamOnScoreboards.add(teamOnScoreboardTwo);
        divisionScoreboards.add(divisionScoreboard);
        Mockito.when(conferenceScoreboard.getDivisionScoreboards()).thenReturn(divisionScoreboards);
        PowerMockito.mockStatic(LeagueAbstractFactory.class);
        PowerMockito.when(LeagueAbstractFactory.instance()).thenReturn(factory);
        PowerMockito.when(factory.createDivisionPlayOff(divisionScoreboard)).thenReturn(divisionScoreboard);
        PowerMockito.when(divisionScoreboard.loadScoreboard()).thenReturn(teamOnScoreboards);
    }

    @Test
    public void loadScoreboard() {
        ConferencePlayOff obj = new ConferencePlayOff(conferenceScoreboard);
        List<TeamOnScoreboard> result = obj.loadScoreboard();
        assertEquals(obj.getScoreboard(), result);
    }

    @Test
    public void sort() {
        ConferencePlayOff obj = new ConferencePlayOff(conferenceScoreboard);
        obj.loadScoreboard();
        obj.sort();
        assertEquals(teamOnScoreboardTwo, teamOnScoreboards.get(1));
    }

    @Test
    public void checkWinner() {
        ConferencePlayOff obj = new ConferencePlayOff(conferenceScoreboard);
        obj.loadScoreboard();
        obj.setFinal();
        obj.checkWinner();
        assertEquals(obj.getWinner(), teamOnScoreboards.get(0));
    }
}
