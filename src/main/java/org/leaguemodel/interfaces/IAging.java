/* @Author:  Siddhant Ashutosh */
package org.leaguemodel.interfaces;

public interface IAging {
    Integer getAverageRetirementAge();

    void setAverageRetirementAge(Integer averageRetirementAge);

    Integer getMaximumAge();

    void setMaximumAge(Integer maximumAge);

    float getStatDecayChance();

    void setStatDecayChance(float statDecayChance);
}
