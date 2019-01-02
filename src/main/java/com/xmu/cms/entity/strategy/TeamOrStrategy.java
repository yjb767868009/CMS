package com.xmu.cms.entity.strategy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xmu.cms.entity.Team;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamOrStrategy implements Strategy {
    private BigInteger strategyId;
    private BigInteger subStrategyOneId;
    private String subStrategyOneName;
    private Strategy subStrategyOne;
    private BigInteger subStrategyTwoId;
    private String subStrategyTwoName;
    private Strategy subStrategyTwo;
    private String type;

    public TeamOrStrategy(BigInteger strategyId, BigInteger subStrategyOneId, String subStrategyOneName, Strategy subStrategyOne, BigInteger subStrategyTwoId, String subStrategyTwoName, Strategy subStrategyTwo, String type) {
        this.strategyId = strategyId;
        this.subStrategyOneId = subStrategyOneId;
        this.subStrategyOneName = subStrategyOneName;
        this.subStrategyOne = subStrategyOne;
        this.subStrategyTwoId = subStrategyTwoId;
        this.subStrategyTwoName = subStrategyTwoName;
        this.subStrategyTwo = subStrategyTwo;
        this.type = type;
    }

    public TeamOrStrategy() {
    }

    public BigInteger getStrategyId() {
        return strategyId;
    }

    @Override
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
    public List<Strategy> getStrategy(List<Strategy> strategies) {
        if (subStrategyOne != null) {
            strategies = subStrategyOne.getStrategy(strategies);
        }
        if (subStrategyTwo != null) {
            strategies = subStrategyTwo.getStrategy(strategies);
        }
        TeamOrStrategy strategy = new TeamOrStrategy();
        strategy.setType(TEAM_OR_STRATEGY);
        String firstStrategyType = strategies.get(0).getType();
        if (!TEAM_OR_STRATEGY.equals(firstStrategyType) && !TEAM_AND_STRATEGY.equals(firstStrategyType)) {
            TeamOrStrategy teamOrStrategy = new TeamOrStrategy();
            teamOrStrategy.setType(TEAM_OR_STRATEGY);
            strategies.add(0, teamOrStrategy);
        }
        return strategies;
    }

    @Override
    public String getType() {
        return TEAM_OR_STRATEGY;
    }

    public void setType(String type) {
        this.type = type;
    }
}
