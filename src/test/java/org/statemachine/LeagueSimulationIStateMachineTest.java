package org.statemachine;

import org.junit.Test;
import org.mockito.Mockito;
import org.statemachine.interfaces.IStateMachine;

import static org.mockito.Mockito.doNothing;

public class LeagueSimulationIStateMachineTest {

    @Test
    public void runStateMachine() {
        IStateMachine leagueSimulationStateMachine = new LeagueSimulationStateMachine();
        IStateMachine test = Mockito.spy(leagueSimulationStateMachine);
        doNothing().when(test).runStateMachine();
        test.runStateMachine();
    }
}
