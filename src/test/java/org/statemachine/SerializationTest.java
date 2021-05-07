package org.statemachine;

import junit.framework.TestCase;
import org.junit.Test;
import org.leaguemodel.interfaces.ILeague;
import org.statemachine.interfaces.ISerialization;
import resources.MockData;

public class SerializationTest extends TestCase {

    @Test
    public void testSerialize() {
        ISerialization serializeObj = new Serialization();
        MockData mockObj = new MockData();
        serializeObj.serialize(mockObj.leagueOne);
    }

    @Test
    public void testDeserializeTest() {
        ISerialization serializeObj = new Serialization();
        ILeague league = serializeObj.deserialize("leagueobj");
    }

}
