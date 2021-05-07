/* @Author:  Siddhant Ashutosh */
package org.leaguemodel.interfaces;

public interface IGameplayConfig {
    IAging getAging();

    void setAging(IAging aging);

    IGameResolver getGameResolver();

    void setGameResolver(IGameResolver gameResolver);

    IInjuries getInjuries();

    void setInjuries(IInjuries injuries);

    ITraining getTraining();

    void setTraining(ITraining training);

    ITrading getTrading();

    void setTrading(ITrading trading);
}
