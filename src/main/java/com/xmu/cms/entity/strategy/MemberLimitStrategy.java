package com.xmu.cms.entity.strategy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Team;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberLimitStrategy implements Strategy {
    private BigInteger strategyId;
    private Integer minMember;
    private Integer maxMember;
    private String type;

    public MemberLimitStrategy(BigInteger strategyId, Integer minMember, Integer maxMember, String type) {
        this.strategyId = strategyId;
        this.minMember = minMember;
        this.maxMember = maxMember;
        this.type = type;
    }

    public MemberLimitStrategy() {
    }

    public BigInteger getStrategyId() {
        return strategyId;
    }

    @Override
    public void setStrategyId(BigInteger strategyId) {
        this.strategyId = strategyId;
    }

    public Integer getMinMember() {
        return minMember;
    }

    public void setMinMember(Integer minMember) {
        this.minMember = minMember;
    }

    public Integer getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(Integer maxMember) {
        this.maxMember = maxMember;
    }

    @Override
    public Boolean checkValid(Team team) {
        Integer memberCount = 0;
        List<Student> students = team.getMembers();
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(team.getLeader());
        for (Student student : students) {
            memberCount++;
        }
        if (minMember != null && memberCount < minMember) {
            return false;
        }
        if (maxMember != null && memberCount > maxMember) {
            return false;
        }
        return true;
    }

    @Override
    public List<Strategy> getStrategy(List<Strategy> strategies) {
        type = MEMBER_LIMIT_STRATEGY;
        strategies.add(this);
        return strategies;
    }

    @Override
    public String getType() {
        return MEMBER_LIMIT_STRATEGY;
    }

    public void setType(String type) {
        this.type = type;
    }
}
