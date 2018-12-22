package com.xmu.cms.mapper;

import com.xmu.cms.entity.strategy.ConflictCourseStrategy;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
@Mapper
public interface ConflictCourseStrategyMapper {
    /**
     * 获取冲突课程限制规则
     *
     * @param strategyId 规则id
     * @return 冲突课程规则
     */
    ConflictCourseStrategy getConflictCourseStrategyById(BigInteger strategyId);// TODO: 2018/12/22  
}
