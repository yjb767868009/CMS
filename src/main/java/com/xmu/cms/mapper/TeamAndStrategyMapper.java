package com.xmu.cms.mapper;

import com.xmu.cms.entity.strategy.TeamAndStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
@Mapper
public interface TeamAndStrategyMapper {

    /**
     * 通过id获取队伍与规则
     *
     * @param strategyId 规则id
     * @return 队伍与规则
     */
    TeamAndStrategy getTeamAndStrategyById(@Param("strategyId") BigInteger strategyId);

    /**
     * 新建队伍与规则
     *
     * @param strategy 规则
     * @return 新的规则id
     */
    Integer insertTeamAndStrategy(@Param("strategy") TeamAndStrategy strategy);
}
