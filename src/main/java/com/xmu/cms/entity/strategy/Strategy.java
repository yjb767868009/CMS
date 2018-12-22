package com.xmu.cms.entity.strategy;

import com.xmu.cms.entity.Team;

import java.util.List;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
public interface Strategy {
    Boolean checkValid(Team team);

    List<Strategy> findSubStrategy();

    List<Strategy> getStrategy();
}
