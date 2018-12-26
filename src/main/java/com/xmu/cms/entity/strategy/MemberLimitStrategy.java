package com.xmu.cms.entity.strategy;

import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Team;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
public class MemberLimitStrategy implements Strategy {
    private BigInteger strategyId;
    private Integer minMember;
    private Integer maxMember;

    @Override
    public Boolean checkValid(Team team) {
        Integer memberCount = 0;
        List<Student> students = team.getMembers();
        for (Student student : students) {
            memberCount++;
        }
        if (minMember != null && memberCount < minMember) return false;
        if (maxMember != null && memberCount > maxMember) return false;
        return true;
    }

    @Override
    public List<Strategy> findSubStrategy() {
        return null;
    }

    @Override
    public List<Strategy> getStrategy(List<Strategy> strategies) {
        strategies.add(this);
        return strategies;
    }
}
