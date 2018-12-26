package com.xmu.cms.entity.strategy;

import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Team;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
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
        return subStrategy.getStrategy(strategies);
    }
}
