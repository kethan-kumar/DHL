package org.statemachine.interfaces;

import org.leaguemodel.interfaces.ILeague;

public interface ISerialization {

    void serialize(ILeague leagueLOM);

    ILeague deserialize(String filename);
}

