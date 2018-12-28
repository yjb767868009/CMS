package com.xmu.cms.dao;

import com.xmu.cms.entity.strategy.Strategy;
import com.xmu.cms.entity.strategy.TeamAndStrategy;
import com.xmu.cms.entity.strategy.TeamOrStrategy;
import com.xmu.cms.entity.strategy.TeamStrategy;
import com.xmu.cms.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

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
            case "TeamAndStrategy":
                strategy = teamAndStrategyMapper.getTeamAndStrategyById(strategyId);
                strategy = setSubStrategy(strategy);
                break;
            case "TeamOrStrategy":
                strategy = teamOrStrategyMapper.getTeamOrStrategyById(strategyId);
                strategy = setSubStrategy(strategy);
                break;
            case "MEMBER_LIMIT_STRATEGY":
                strategy = memberLimitStrategyMapper.getMemberLimitStrategyById(strategyId);
                break;
            case "CONFLICT_COURSE_STRATEGY":
                strategy = conflictCourseStrategyMapper.getConflictCourseStrategyById(strategyId);
                break;
            case "COURSE_MEMBER_LIMIT_STRATEGY":
                strategy = courseMemberLimitStrategyMapper.getCourseMemberLimitStrategyById(strategyId);
                break;
            default:
                break;
        }
        return strategy;
    }

    private Strategy setSubStrategy(Strategy strategy) {
        String subStrategyClass = strategy.getClass().getName();
        String subStrategyName = subStrategyClass.substring(subStrategyClass.lastIndexOf(".") + 1);
        Strategy subStrategy = null;
        Strategy subStrategyOne = null;
        Strategy subStrategyTwo = null;
        switch (subStrategyName) {
            case "TEAM_STRATEGY":
                TeamStrategy teamStrategy = (TeamStrategy) strategy;
                subStrategy = getStrategy(teamStrategy.getSubStrategyId(), teamStrategy.getSubStrategyName());
                teamStrategy.setSubStrategy(subStrategy);
                strategy = teamStrategy;
                break;
            case "TeamAndStrategy":
                TeamAndStrategy teamAndStrategy = (TeamAndStrategy) strategy;
                subStrategyOne = getStrategy(teamAndStrategy.getSubStrategyOneId(), teamAndStrategy.getSubStrategyOneName());
                teamAndStrategy.setSubStrategyOne(subStrategyOne);
                subStrategyTwo = getStrategy(teamAndStrategy.getSubStrategyTwoId(), teamAndStrategy.getSubStrategyTwoName());
                teamAndStrategy.setSubStrategyTwo(subStrategyTwo);
                strategy = teamAndStrategy;
                break;
            case "TeamOrStrategy":
                TeamOrStrategy teamOrStrategy = (TeamOrStrategy) strategy;
                subStrategyOne = getStrategy(teamOrStrategy.getSubStrategyOneId(), teamOrStrategy.getSubStrategyOneName());
                teamOrStrategy.setSubStrategyOne(subStrategyOne);
                subStrategyTwo = getStrategy(teamOrStrategy.getSubStrategyTwoId(), teamOrStrategy.getSubStrategyTwoName());
                teamOrStrategy.setSubStrategyTwo(subStrategyTwo);
                strategy = teamOrStrategy;
                break;
            case "MEMBER_LIMIT_STRATEGY":
                break;
            case "CONFLICT_COURSE_STRATEGY":
                break;
            case "COURSE_MEMBER_LIMIT_STRATEGY":
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

    public Strategy newStrategy(List<Strategy> strategies) {
        Strategy firstStrategy = strategies.get(0);
        if (firstStrategy.getType().equals(Strategy.TEAM_OR_STRATEGY_TYPE)) {
        }
        return null;
    }
}
