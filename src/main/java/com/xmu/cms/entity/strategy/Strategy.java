package com.xmu.cms.entity.strategy;

import com.xmu.cms.entity.Team;

import java.util.List;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
public interface Strategy {
    /**
     * 检查队伍合法性
     *
     * @param team 队伍
     * @return 合法为true，非法为false
     */
    Boolean checkValid(Team team);

    /**
     * 将树状规则变成线性,在规则列表加上自己
     *
     * @param strategies 规则列表
     * @return 规则列表
     */
    List<Strategy> getStrategy(List<Strategy> strategies);
}
