/* @Author:  Siddhant Ashutosh */
package org.leaguemodel;

import org.leaguemodel.interfaces.ITraining;

public class Training implements ITraining {

    private Integer daysUntilStatIncreaseCheck;


    public Integer getDaysUntilStatIncreaseCheck() {
        return daysUntilStatIncreaseCheck;
    }


    public void setDaysUntilStatIncreaseCheck(Integer daysUntilStatIncreaseCheck) {
        this.daysUntilStatIncreaseCheck = daysUntilStatIncreaseCheck;
    }

}
