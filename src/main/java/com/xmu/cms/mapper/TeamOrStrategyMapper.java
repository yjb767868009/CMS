package com.xmu.cms.mapper;

import com.xmu.cms.entity.strategy.TeamOrStrategy;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
@Mapper
public interface TeamOrStrategyMapper {

    /**
     * 通过id获取队伍或规则
     *
     * @param strategyId 队伍或规则id
     * @return 队伍或规则id
     */
    TeamOrStrategy getTeamOrStrategyById(BigInteger strategyId);
}
