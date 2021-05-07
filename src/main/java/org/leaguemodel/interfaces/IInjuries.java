/* @Author:  Siddhant Ashutosh */
package org.leaguemodel.interfaces;

public interface IInjuries {
    Double getRandomInjuryChance();

    void setRandomInjuryChance(Double randomInjuryChance);

    Integer getInjuryDaysLow();

    void setInjuryDaysLow(Integer injuryDaysLow);

    Integer getInjuryDaysHigh();

    void setInjuryDaysHigh(Integer injuryDaysHigh);
}
