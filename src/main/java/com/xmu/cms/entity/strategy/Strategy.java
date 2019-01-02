package com.xmu.cms.entity.strategy;

import com.xmu.cms.entity.Team;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
public interface Strategy {
    static String TEAM_OR_STRATEGY = "TeamOrStrategy";
    static String TEAM_AND_STRATEGY = "TeamAndStrategy";
    static String CONFLICT_COURSE_STRATEGY = "ConflictCourseStrategy";
    static String COURSE_MEMBER_LIMIT_STRATEGY = "CourseMemberLimitStrategy";
    static String MEMBER_LIMIT_STRATEGY = "MemberLimitStrategy";
    static String TEAM_STRATEGY = "TeamStrategy";

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

    /**
     * 获取规则名
     *
     * @return 规则名
     */
    String getType();

    /**
     * 设置规则id
     *
     * @param strategyId 规则id
     */
    void setStrategyId(BigInteger strategyId);
}
