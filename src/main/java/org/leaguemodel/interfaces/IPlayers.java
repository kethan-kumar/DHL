/* @Author:  Siddhant Ashutosh */
package org.leaguemodel.interfaces;

public interface IPlayers {
    Integer getSaving();

    void setSaving(Integer saving);

    String getPosition();

    void setPosition(String position);

    String getPlayerName();

    void setPlayerName(String playerName);

    boolean isCaptain();

    void setCaptain(boolean isCaptain);

    void setIsCaptain(boolean captain);

    Integer getAge();

    void setAge(Integer age);

    Integer getSkating();

    void setSkating(Integer skating);

    Integer getShooting();

    void setShooting(Integer shooting);

    Integer getChecking();

    void setChecking(Integer checking);

    int getNoOfDaysInjured();

    void setNoOfDaysInjured(int noOfDaysInjured);

    boolean isRetired();

    void setRetired(boolean retired);

    Integer getBirthDay();

    void setBirthDay(Integer birthDay);

    Integer getBirthMonth();

    void setBirthMonth(Integer birthMonth);

    Integer getBirthYear();

    void setBirthYear(Integer birthYear);

    boolean getIsActive();

    void setIsActive(boolean isActive);

}
