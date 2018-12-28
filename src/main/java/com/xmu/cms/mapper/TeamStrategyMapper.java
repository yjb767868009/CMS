package com.xmu.cms.mapper;

import com.xmu.cms.entity.strategy.TeamStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    TeamStrategy getCourseStrategy(@Param("courseId") BigInteger courseId);

    /**
     * 新建队伍总规则
     *
     * @param teamStrategy 队伍规则
     * @return 新建的id
     */
    Integer insertTeamStrategy(@Param("strategy") TeamStrategy teamStrategy);
}
