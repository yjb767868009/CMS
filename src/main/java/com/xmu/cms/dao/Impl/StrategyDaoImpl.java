package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.StrategyDao;
import com.xmu.cms.entity.strategy.Strategy;
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

    private Strategy setSubStrategy(Strategy strategy) {
        // TODO: 2018/12/22  
        return null;
    }

    @Override
    public Strategy getCourseStrategy(BigInteger courseId) {
        TeamStrategy teamStrategy = teamStrategyMapper.getCourseStrategy(courseId);
        return setSubStrategy(teamStrategy);
    }
}
