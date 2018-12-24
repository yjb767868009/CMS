package com.xmu.cms.mapper;

import com.xmu.cms.entity.strategy.TeamStrategy;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
@Mapper
public interface TeamStrategyMapper {
    /**
     * 获得班级的总规则
     *
     * @param courseId 班级id
     * @return 规则
     */
    TeamStrategy getCourseStrategy(BigInteger courseId);
}
