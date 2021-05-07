package org.statemachine;

import org.junit.Test;
import org.statemachine.interfaces.IValidate;
import resources.MockData;

import static org.junit.Assert.*;

public class ValidationTest {

    @Test
    public void validateGeneralManagerTest() {
        MockData mock = new MockData();
        IValidate validate = new Validation();
        assertNull(validate.validateGeneralManager(mock.leagueOne, "0"));
    }

    @Test
    public void validateHeadCoachTest() {
        MockData mock = new MockData();
        IValidate validate = new Validation();
        assertNull((validate.validateHeadCoach(mock.leagueOne, "4")));
    }

    @Test
    public void validateStringTest() {
        IValidate validate = new Validation();
        assertFalse(validate.validateString("9"));
        assertTrue(validate.validateString("test"));
    }
}
