/* @Author:  Siddhant Ashutosh */
package org.leaguemodel;

import org.leaguemodel.interfaces.IInjuries;

public class Injuries implements IInjuries {

    private Double randomInjuryChance;
    private Integer injuryDaysLow;
    private Integer injuryDaysHigh;


    public Double getRandomInjuryChance() {
        return randomInjuryChance;
    }


    public void setRandomInjuryChance(Double randomInjuryChance) {
        this.randomInjuryChance = randomInjuryChance;
    }


    public Integer getInjuryDaysLow() {
        return injuryDaysLow;
    }


    public void setInjuryDaysLow(Integer injuryDaysLow) {
        this.injuryDaysLow = injuryDaysLow;
    }


    public Integer getInjuryDaysHigh() {
        return injuryDaysHigh;
    }


    public void setInjuryDaysHigh(Integer injuryDaysHigh) {
        this.injuryDaysHigh = injuryDaysHigh;
    }

}
