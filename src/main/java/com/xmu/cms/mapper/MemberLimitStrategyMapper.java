package com.xmu.cms.mapper;

import com.xmu.cms.entity.strategy.MemberLimitStrategy;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
@Mapper
public interface MemberLimitStrategyMapper {
    /**
     * 获取队员成员数量限制规则
     *
     * @param strategyId 规则id
     * @return 队伍成员数量限制规则
     */
    MemberLimitStrategy getMemberLimitStrategyById(BigInteger strategyId);
}
