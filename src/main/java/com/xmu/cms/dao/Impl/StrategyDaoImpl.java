package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.StrategyDao;
import com.xmu.cms.entity.strategy.Strategy;
import com.xmu.cms.entity.strategy.TeamAndStrategy;
import com.xmu.cms.entity.strategy.TeamOrStrategy;
import com.xmu.cms.entity.strategy.TeamStrategy;
import com.xmu.cms.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
public class StrategyDaoImpl implements StrategyDao {
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
            case "team_and_strategy":
                strategy = teamAndStrategyMapper.getTeamAndStrategyById(strategyId);
                strategy = setSubStrategy(strategy);
                break;
            case "team_or_strategy":
                strategy = teamOrStrategyMapper.getTeamOrStrategyById(strategyId);
                strategy = setSubStrategy(strategy);
                break;
            case "MemberLimitStrategy":
                strategy = memberLimitStrategyMapper.getMemberLimitStrategyById(strategyId);
                break;
            case "ConflictCourseStrategy":
                strategy = conflictCourseStrategyMapper.getConflictCourseStrategyById(strategyId);
                break;
            case "CourseMemberLimitStrategy":
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
            case "TeamStrategy":
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
            case "MemberLimitStrategy":
                break;
            case "ConflictCourseStrategy":
                break;
            case "CourseMemberLimitStrategy":
                break;
            default:
                break;
        }
        return strategy;
    }

    @Override
    public Strategy getCourseStrategy(BigInteger courseId) {
        TeamStrategy teamStrategy = teamStrategyMapper.getCourseStrategy(courseId);
        return setSubStrategy(teamStrategy);
    }
}
