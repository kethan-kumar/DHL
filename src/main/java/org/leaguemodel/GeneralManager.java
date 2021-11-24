/* @Author: Kethan Kumar */
package org.leaguemodel;

import org.leaguemodel.interfaces.IGeneralManager;

public class GeneralManager implements IGeneralManager {

    private String name;
    private String personality;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPersonality() {
        return personality;
    }


    public void setPersonality(String personality) {
        this.personality = personality;
    }
}
