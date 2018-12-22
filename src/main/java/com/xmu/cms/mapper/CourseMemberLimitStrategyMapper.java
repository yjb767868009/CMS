package com.xmu.cms.mapper;

import com.xmu.cms.entity.strategy.CourseMemberLimitStrategy;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
@Mapper
public interface CourseMemberLimitStrategyMapper {
    /**
     * 获取队伍中选课人数限制规则
     *
     * @param strategyId 规则id
     * @return 队伍中选课人数限制规则
     */
    CourseMemberLimitStrategy getCourseMemberLimitStrategyById(BigInteger strategyId);// TODO: 2018/12/22  
}
