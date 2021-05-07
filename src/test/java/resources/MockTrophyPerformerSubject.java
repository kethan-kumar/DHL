package resources;

import org.trophysystem.BestPerformanceSubject;
import org.trophysystem.interfaces.IPerformanceObserver;

public class MockTrophyPerformerSubject extends BestPerformanceSubject {
    String testPerformerName;
    int testScore;
    IPerformanceObserver observer;

    public MockTrophyPerformerSubject() {
        testPerformerName = "Performer Name";
        testScore = 100;
    }

    public void testPerformanceSubject() {
        this.performerName = testPerformerName;
        this.score = testScore;
        this.attachPlayerObserver(observer);
        notifyObserver();
        this.detachPlayerObserver(observer);
    }
}
