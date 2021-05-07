/* @Author: Kethan Kumar */

package org.statemachine;

import org.io.abstractfactory.IOAbstractFactory;
import org.io.interfaces.IDisplay;
import org.io.interfaces.IInput;
import org.statemachine.interfaces.ISimulation;

public class Simulate implements ISimulation {
    private static final String SIMULATIONS = "number of seasons to simulate";
    IDisplay display;
    IInput input;
    private IOAbstractFactory ioFactory;

    public Simulate() {
        ioFactory = IOAbstractFactory.instance();
        display = ioFactory.createDisplay();
        input = ioFactory.createInput();
    }

    public int simulateSeasons() {
        String numOfSeasons = input.userInput(SIMULATIONS);
        display.displayMessage("Simulation begins:");
        return Integer.parseInt(numOfSeasons);
    }

    public boolean abortSimulation() {
        display.displayMessage("Hockey league simulation ended!");
        return true;
    }
}
