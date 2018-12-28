package com.xmu.cms.mapper;

import com.xmu.cms.entity.strategy.ConflictCourseStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    ConflictCourseStrategy getConflictCourseStrategyById(@Param("conflictCourseId") BigInteger strategyId);

    /**
     * 新建冲突课程规则
     *
     * @param strategy 规则
     */
    void insertConflictCourseStrategy(@Param("strategy") ConflictCourseStrategy strategy);

    /**
     * 获取最大的id
     *
     * @return 最大id
     */
    BigInteger getMaxId();
}
