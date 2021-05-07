/* @Author:  Siddhant Ashutosh */
package org.leaguemodel;

import org.leaguemodel.interfaces.IHeadCoach;

public class Coach implements IHeadCoach {

    private String name;
    private Double skating;
    private Double shooting;
    private Double checking;
    private Double saving;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Double getSkating() {
        return skating;
    }


    public void setSkating(Double skating) {
        this.skating = skating;
    }


    public Double getShooting() {
        return shooting;
    }


    public void setShooting(Double shooting) {
        this.shooting = shooting;
    }


    public Double getChecking() {
        return checking;
    }


    public void setChecking(Double checking) {
        this.checking = checking;
    }


    public Double getSaving() {
        return saving;
    }


    public void setSaving(Double saving) {
        this.saving = saving;
    }

}
