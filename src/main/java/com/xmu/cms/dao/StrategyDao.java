package com.xmu.cms.dao;

import com.xmu.cms.entity.strategy.Strategy;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
public interface StrategyDao {
    Strategy getCourseStrategy(BigInteger courseId);
}
