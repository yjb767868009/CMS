package com.xmu.cms.dao;

import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.strategy.*;
import com.xmu.cms.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

import static com.xmu.cms.entity.strategy.Strategy.*;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
@Component
public class StrategyDao {
    @Autowired
    private TeamStrategyMapper teamStrategyMapper;

    @Autowired
    private TeamAndStrategyMapper teamAndStrategyMapper;

    @Autowired
    private TeamOrStrategyMapper teamOrStrategyMapper;

    @Autowired
    private ConflictCourseStrategyMapper conflictCourseStrategyMapper;

    @Autowired
    private MemberLimitStrategyMapper memberLimitStrategyMapper;

    @Autowired
    private CourseMemberLimitStrategyMapper courseMemberLimitStrategyMapper;

    private Strategy getStrategy(BigInteger strategyId, String subStrategy) {
        Strategy strategy = null;
        switch (subStrategy) {
            case TEAM_AND_STRATEGY:
                strategy = teamAndStrategyMapper.getTeamAndStrategyById(strategyId);
                strategy = setSubStrategy(strategy);
                break;
            case TEAM_OR_STRATEGY:
                strategy = teamOrStrategyMapper.getTeamOrStrategyById(strategyId);
                strategy = setSubStrategy(strategy);
                break;
            case MEMBER_LIMIT_STRATEGY:
                strategy = memberLimitStrategyMapper.getMemberLimitStrategyById(strategyId);
                break;
            case CONFLICT_COURSE_STRATEGY:
                strategy = new ConflictCourseStrategy();
                strategy.setStrategyId(strategyId);
                ((ConflictCourseStrategy) strategy).setCourses(conflictCourseStrategyMapper.getConflictCourseStrategyById(strategyId));
                break;
            case COURSE_MEMBER_LIMIT_STRATEGY:
                strategy = courseMemberLimitStrategyMapper.getCourseMemberLimitStrategyById(strategyId);
                break;
            default:
                break;
        }
        return strategy;
    }

    private Strategy setSubStrategy(Strategy strategy) {
        String subStrategyName = strategy.getType();
        Strategy subStrategy = null;
        Strategy subStrategyOne = null;
        Strategy subStrategyTwo = null;
        switch (subStrategyName) {
            case TEAM_STRATEGY:
                TeamStrategy teamStrategy = (TeamStrategy) strategy;
                subStrategy = getStrategy(teamStrategy.getSubStrategyId(), teamStrategy.getSubStrategyName());
                teamStrategy.setSubStrategy(subStrategy);
                strategy = teamStrategy;
                break;
            case TEAM_AND_STRATEGY:
                TeamAndStrategy teamAndStrategy = (TeamAndStrategy) strategy;
                subStrategyOne = getStrategy(teamAndStrategy.getSubStrategyOneId(), teamAndStrategy.getSubStrategyOneName());
                teamAndStrategy.setSubStrategyOne(subStrategyOne);
                subStrategyTwo = getStrategy(teamAndStrategy.getSubStrategyTwoId(), teamAndStrategy.getSubStrategyTwoName());
                teamAndStrategy.setSubStrategyTwo(subStrategyTwo);
                strategy = teamAndStrategy;
                break;
            case TEAM_OR_STRATEGY:
                TeamOrStrategy teamOrStrategy = (TeamOrStrategy) strategy;
                subStrategyOne = getStrategy(teamOrStrategy.getSubStrategyOneId(), teamOrStrategy.getSubStrategyOneName());
                teamOrStrategy.setSubStrategyOne(subStrategyOne);
                subStrategyTwo = getStrategy(teamOrStrategy.getSubStrategyTwoId(), teamOrStrategy.getSubStrategyTwoName());
                teamOrStrategy.setSubStrategyTwo(subStrategyTwo);
                strategy = teamOrStrategy;
                break;
            case MEMBER_LIMIT_STRATEGY:
                break;
            case CONFLICT_COURSE_STRATEGY:
                break;
            case COURSE_MEMBER_LIMIT_STRATEGY:
                break;
            default:
                break;
        }
        return strategy;
    }

    public Strategy getCourseStrategy(BigInteger courseId) {
        TeamStrategy teamStrategy = teamStrategyMapper.getCourseStrategy(courseId);
       return setSubStrategy(teamStrategy);
    }

    private Strategy insertStrategy(Strategy strategy) {
        BigInteger strategyId;
        switch (strategy.getType()) {
            case TEAM_AND_STRATEGY:
                strategyId = BigInteger.valueOf(teamAndStrategyMapper.insertTeamAndStrategy((TeamAndStrategy) strategy));
                strategy.setStrategyId(strategyId);
                return strategy;
            case TEAM_OR_STRATEGY:
                strategyId = BigInteger.valueOf(teamOrStrategyMapper.insertTeamOrStrategy((TeamOrStrategy) strategy));
                strategy.setStrategyId(strategyId);
                return strategy;
            case MEMBER_LIMIT_STRATEGY:
                strategyId = BigInteger.valueOf(memberLimitStrategyMapper.insertMemberLimitStrategy((MemberLimitStrategy) strategy));
                strategy.setStrategyId(strategyId);
                return strategy;
            case CONFLICT_COURSE_STRATEGY:
                ConflictCourseStrategy conflictCourseStrategy = (ConflictCourseStrategy) strategy;
                strategyId = conflictCourseStrategyMapper.getMaxId().add(new BigInteger("1"));
                strategy.setStrategyId(strategyId);
                for (Course course : conflictCourseStrategy.getCourses()) {
                    conflictCourseStrategyMapper.insertConflictCourseStrategy(strategyId, course.getCourseId());
                }
                return strategy;
            case COURSE_MEMBER_LIMIT_STRATEGY:
                strategyId = BigInteger.valueOf(courseMemberLimitStrategyMapper.insertCourseMemberLimitStrategy((CourseMemberLimitStrategyMapper) strategy));
                strategy.setStrategyId(strategyId);
                return strategy;
            default:
                return null;
        }
    }

    public void newStrategy(BigInteger courseId, List<Strategy> strategies) throws Exception {
        Strategy firstStrategy = strategies.get(0);
        Strategy beforeStrategy = null;
        if (firstStrategy.getType().equals(TEAM_OR_STRATEGY)) {
            strategies.remove(0);
            TeamOrStrategy teamOrStrategy = null;
            for (Strategy strategy : strategies) {
                if (strategy.getType().equals(COURSE_MEMBER_LIMIT_STRATEGY)) {
                    if (beforeStrategy == null) {
                        beforeStrategy = insertStrategy(strategy);
                    } else {
                        strategy = insertStrategy(strategy);
                        TeamOrStrategy newTeamOrStrategy = new TeamOrStrategy();
                        if (teamOrStrategy == null) {
                            newTeamOrStrategy.setSubStrategyOne(beforeStrategy);
                        } else {
                            newTeamOrStrategy.setSubStrategyOne(teamOrStrategy);
                        }
                        newTeamOrStrategy.setSubStrategyTwo(strategy);
                        newTeamOrStrategy = (TeamOrStrategy) insertStrategy(newTeamOrStrategy);
                        teamOrStrategy = newTeamOrStrategy;
                    }
                    strategies.remove(strategy);
                }
            }
            if (teamOrStrategy != null) {
                beforeStrategy = teamOrStrategy;
            }
        }
        TeamAndStrategy teamAndStrategy = null;
        for (Strategy strategy : strategies) {
            if (beforeStrategy == null) {
                beforeStrategy = insertStrategy(strategy);
            } else {
                strategy = insertStrategy(strategy);
                TeamAndStrategy newTeamAndStrategy = new TeamAndStrategy();
                if (teamAndStrategy == null) {
                    newTeamAndStrategy.setSubStrategyOne(beforeStrategy);
                } else {
                    newTeamAndStrategy.setSubStrategyOne(teamAndStrategy);
                }
                newTeamAndStrategy.setSubStrategyTwo(strategy);
                newTeamAndStrategy = (TeamAndStrategy) insertStrategy(newTeamAndStrategy);
                teamAndStrategy = newTeamAndStrategy;
            }
        }
        if (teamAndStrategy != null) {
            beforeStrategy = teamAndStrategy;
        }
        TeamStrategy teamStrategy = new TeamStrategy();
        teamStrategy.setCourse(new Course(courseId));
        teamStrategy.setSubStrategy(beforeStrategy);
        teamStrategyMapper.insertTeamStrategy(teamStrategy);
    }
}
