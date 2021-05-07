package resources.abstractfactory;

import org.leaguemodel.interfaces.ILeague;
import org.leaguemodel.interfaces.IPersistence;
import org.leaguemodel.interfaces.ITrading;
import resources.interfaces.IMockDataTrading;

public abstract class MockAbstractFactory {
    private static MockAbstractFactory uniqueInstance = null;

    public static MockAbstractFactory instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new MockFactory();
        }
        return uniqueInstance;
    }

    public static void setFactory(MockFactory trophyFactory) {
        uniqueInstance = trophyFactory;
    }

    public abstract IMockDataTrading createMockDataTrading();

    public abstract ITrading createMockTrading();

    public abstract ILeague createMockDataLeague();

    public abstract IPersistence createMockDataPersistence();

}
