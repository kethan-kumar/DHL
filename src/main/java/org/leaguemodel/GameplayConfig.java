/* @Author: Kethan Kumar */
package org.leaguemodel;

import org.leaguemodel.interfaces.*;

public class GameplayConfig implements IGameplayConfig {

    private IAging aging;
    private IGameResolver gameResolver;
    private IInjuries injuries;
    private ITraining training;
    private ITrading trading;


    public IAging getAging() {
        return aging;
    }


    public void setAging(IAging aging) {
        this.aging = aging;
    }


    public IGameResolver getGameResolver() {
        return gameResolver;
    }


    public void setGameResolver(IGameResolver gameResolver) {
        this.gameResolver = gameResolver;
    }


    public IInjuries getInjuries() {
        return injuries;
    }


    public void setInjuries(IInjuries injuries) {
        this.injuries = injuries;
    }


    public ITraining getTraining() {
        return training;
    }


    public void setTraining(ITraining training) {
        this.training = training;
    }


    public ITrading getTrading() {
        return trading;
    }


    public void setTrading(ITrading trading) {
        this.trading = trading;
    }
}
