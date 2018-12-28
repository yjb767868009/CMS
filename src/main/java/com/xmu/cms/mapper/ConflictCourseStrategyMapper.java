package com.xmu.cms.mapper;

import com.xmu.cms.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

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
    List<Course> getConflictCourseStrategyById(@Param("conflictCourseId") BigInteger strategyId);

    /**
     * 获取最大的id
     *
     * @return 最大id
     */
    BigInteger getMaxId();

    /**
     * 新建冲突课程池
     *
     * @param strategyId 规则id
     * @param courseId   课程id
     */
    void insertConflictCourseStrategy(@Param("strategyId") BigInteger strategyId,
                                      @Param("courseId") BigInteger courseId);
}
