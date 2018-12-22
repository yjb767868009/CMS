package com.xmu.cms.entity.strategy;

import com.xmu.cms.entity.Team;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
public class TeamOrStrategy implements Strategy {
    private BigInteger strategyId;
    private BigInteger subStrategyOneId;
    private String subStrategyOneName;
    private Strategy subStrategyOne;
    private BigInteger subStrategyTwoId;
    private String subStrategyTwoName;
    private Strategy subStrategyTwo;

    public TeamOrStrategy(BigInteger strategyId, BigInteger subStrategyOneId, String subStrategyOneName, Strategy subStrategyOne, BigInteger subStrategyTwoId, String subStrategyTwoName, Strategy subStrategyTwo) {
        this.strategyId = strategyId;
        this.subStrategyOneId = subStrategyOneId;
        this.subStrategyOneName = subStrategyOneName;
        this.subStrategyOne = subStrategyOne;
        this.subStrategyTwoId = subStrategyTwoId;
        this.subStrategyTwoName = subStrategyTwoName;
        this.subStrategyTwo = subStrategyTwo;
    }

    public TeamOrStrategy() {
    }

    public BigInteger getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(BigInteger strategyId) {
        this.strategyId = strategyId;
    }

    public BigInteger getSubStrategyOneId() {
        return subStrategyOneId;
    }

    public void setSubStrategyOneId(BigInteger subStrategyOneId) {
        this.subStrategyOneId = subStrategyOneId;
    }

    public String getSubStrategyOneName() {
        return subStrategyOneName;
    }

    public void setSubStrategyOneName(String subStrategyOneName) {
        this.subStrategyOneName = subStrategyOneName;
    }

    public Strategy getSubStrategyOne() {
        return subStrategyOne;
    }

    public void setSubStrategyOne(Strategy subStrategyOne) {
        this.subStrategyOne = subStrategyOne;
    }

    public BigInteger getSubStrategyTwoId() {
        return subStrategyTwoId;
    }

    public void setSubStrategyTwoId(BigInteger subStrategyTwoId) {
        this.subStrategyTwoId = subStrategyTwoId;
    }

    public String getSubStrategyTwoName() {
        return subStrategyTwoName;
    }

    public void setSubStrategyTwoName(String subStrategyTwoName) {
        this.subStrategyTwoName = subStrategyTwoName;
    }

    public Strategy getSubStrategyTwo() {
        return subStrategyTwo;
    }

    public void setSubStrategyTwo(Strategy subStrategyTwo) {
        this.subStrategyTwo = subStrategyTwo;
    }

    @Override
    public Boolean checkValid(Team team) {
        return subStrategyOne.checkValid(team) || subStrategyTwo.checkValid(team);
    }

    @Override
    public List<Strategy> findSubStrategy() {
        List<Strategy> strategies = new ArrayList<Strategy>();
        strategies.add(subStrategyOne);
        strategies.add(subStrategyTwo);
        return strategies;
    }

    @Override
    public List<Strategy> getStrategy() {
        return null;
    }
}