package com.xmu.cms.dao;

import com.xmu.cms.entity.TeamApplication;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/23.
 * @version 1.0
 */
public interface TeamApplicationDao {
    List<TeamApplication> getTeamApplicationByTeacherId(BigInteger teacherId);

    TeamApplication updateTeamApplication(TeamApplication teamApplication);
}
