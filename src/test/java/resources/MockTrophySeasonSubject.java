package resources;

import org.leaguesimulation.LeagueScoreboard;
import org.leaguesimulation.interfaces.IScoreboard;
import org.trophysystem.RegularSeasonSubject;

public class MockTrophySeasonSubject extends RegularSeasonSubject {
    public String testPerformerName;
    int testScore;
    IScoreboard leagueScoreboard;

    public MockTrophySeasonSubject() {
        testPerformerName = "Performer Name";
        testScore = 100;
        MockDataTrading md = new MockDataTrading();
        this.leagueScoreboard = new LeagueScoreboard(md.leagueOne);
        this.leagueScoreboard.getScoreboard().get(0).setScore(80);
        this.leagueScoreboard.getScoreboard().get(1).setScore(40);
        this.leagueScoreboard.loadScoreboard();
    }

    public void testPerformanceSubject() {

    }
}
