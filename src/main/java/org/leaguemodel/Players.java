/* @Author:  Siddhant Ashutosh */
package org.leaguemodel;

import org.leaguemodel.interfaces.IPlayers;
import org.leaguemodel.interfaces.IStrength;

public class Players implements IStrength, IPlayers {

    private String position;
    private String playerName;
    private boolean captain;
    private Integer age;
    private Integer skating;
    private Integer shooting;
    private Integer checking;
    private Integer saving;
    private Integer birthDay;
    private Integer birthMonth;
    private Integer birthYear;
    private boolean isRetired;
    private boolean isActive;
    private int noOfDaysInjured;


    public Players() {
        this.position = null;
        this.playerName = null;
        this.captain = false;
        this.isRetired = false;
        this.noOfDaysInjured = -1;
    }

    public Players(String playerPosition, String playerName, boolean captain) {

        this.position = playerPosition;
        this.playerName = playerName;
        this.captain = captain;
    }

    public Players(String playerPosition, String playerName, boolean isCaptain, Integer age, Integer skating, Integer shooting, Integer checking, Integer saving) {
        this(playerPosition, playerName, age, skating, shooting, checking, saving);
        this.captain = isCaptain;
    }

    public Players(String playerPosition, String playerName, Integer age, Integer skating, Integer shooting, Integer checking, Integer saving) {
        this.position = playerPosition;
        this.playerName = playerName;
        this.age = age;
        this.skating = skating;
        this.shooting = shooting;
        this.checking = checking;
        this.saving = saving;
        this.isRetired = false;
        this.noOfDaysInjured = -1;
    }

    public Players(String playerPosition, String playerName, Integer age, Integer skating, Integer shooting, Integer checking, Integer saving, Integer birthDay, Integer birthMonth, Integer birthYear) {
        this.position = playerPosition;
        this.playerName = playerName;
        this.age = age;
        this.skating = skating;
        this.shooting = shooting;
        this.checking = checking;
        this.saving = saving;
        this.isRetired = false;
        this.noOfDaysInjured = -1;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Integer getSaving() {
        return saving;
    }

    public void setSaving(Integer saving) {
        this.saving = saving;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isCaptain() {
        return captain;
    }

    public void setCaptain(boolean isCaptain) {
        this.captain = isCaptain;
    }

    public void setIsCaptain(boolean captain) {
        this.captain = captain;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSkating() {
        return skating;
    }

    public void setSkating(Integer skating) {
        this.skating = skating;
    }

    public Integer getShooting() {
        return shooting;
    }

    public void setShooting(Integer shooting) {
        this.shooting = shooting;
    }

    public Integer getChecking() {
        return checking;
    }

    public void setChecking(Integer checking) {
        this.checking = checking;
    }

    public boolean isRetired() {
        return isRetired;
    }

    public void setRetired(boolean retired) {
        isRetired = retired;
    }

    public int getNoOfDaysInjured() {
        return noOfDaysInjured;
    }

    public void setNoOfDaysInjured(int noOfDaysInjured) {
        this.noOfDaysInjured = noOfDaysInjured;
    }

    public Integer getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Integer birthDay) {
        this.birthDay = birthDay;
    }

    public Integer getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(Integer birthMonth) {
        this.birthMonth = birthMonth;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}

