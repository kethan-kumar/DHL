/* @Author:  Kethan Kumar */
package org.leaguemodel;

import org.leaguemodel.interfaces.IAging;

public class Aging implements IAging {

    private Integer averageRetirementAge;
    private Integer maximumAge;
    private float statDecayChance;

    public float getStatDecayChance() {
        return statDecayChance;
    }

    public void setStatDecayChance(float statDecayChance) {
        this.statDecayChance = statDecayChance;
    }


    public Integer getAverageRetirementAge() {
        return averageRetirementAge;
    }


    public void setAverageRetirementAge(Integer averageRetirementAge) {
        this.averageRetirementAge = averageRetirementAge;
    }


    public Integer getMaximumAge() {
        return maximumAge;
    }


    public void setMaximumAge(Integer maximumAge) {
        this.maximumAge = maximumAge;
    }

}
