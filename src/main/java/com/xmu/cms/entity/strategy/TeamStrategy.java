package com.xmu.cms.entity.strategy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Team;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamStrategy implements Strategy {
    private Course course;
    private BigInteger subStrategyId;
    private String subStrategyName;
    private Strategy subStrategy;

    public TeamStrategy(Course course, BigInteger subStrategyId, String subStrategyName, Strategy subStrategy) {
        this.course = course;
        this.subStrategyId = subStrategyId;
        this.subStrategyName = subStrategyName;
        this.subStrategy = subStrategy;
    }

    public TeamStrategy() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Strategy getSubStrategy() {
        return subStrategy;
    }

    public BigInteger getSubStrategyId() {
        return subStrategyId;
    }

    public void setSubStrategyId(BigInteger subStrategyId) {
        this.subStrategyId = subStrategyId;
    }

    public String getSubStrategyName() {
        return subStrategyName;
    }

    public void setSubStrategyName(String subStrategyName) {
        this.subStrategyName = subStrategyName;
    }

    public void setSubStrategy(Strategy subStrategy) {
        this.subStrategy = subStrategy;
    }

    @Override
    public Boolean checkValid(Team team) {
        return subStrategy.checkValid(team);
    }

    @Override
    public List<Strategy> getStrategy(List<Strategy> strategies) {
        List<Strategy> newStrategies = subStrategy.getStrategy(strategies);
        String firstStrategyType = newStrategies.get(0).getType();
        if (!TEAM_OR_STRATEGY.equals(firstStrategyType) && !TEAM_AND_STRATEGY.equals(firstStrategyType)) {
            TeamOrStrategy teamOrStrategy = new TeamOrStrategy();
            teamOrStrategy.setType(TEAM_OR_STRATEGY);
            newStrategies.add(0, teamOrStrategy);
        }
        return newStrategies;
    }

    @Override
    public String getType() {
        return TEAM_STRATEGY;
    }

    @Override
    public void setStrategyId(BigInteger strategyId) {

    }
}
