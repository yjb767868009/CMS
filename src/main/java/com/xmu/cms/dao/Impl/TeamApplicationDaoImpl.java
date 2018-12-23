package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.TeamApplicationDao;
import com.xmu.cms.entity.TeamApplication;
import com.xmu.cms.mapper.TeamApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/23.
 * @version 1.0
 */
public class TeamApplicationDaoImpl implements TeamApplicationDao {
    @Autowired
    private TeamApplicationMapper teamApplicationMapper;

    @Override
    public List<TeamApplication> getTeamApplicationByTeacherId(BigInteger teacherId) {
        return teamApplicationMapper.getTeamApplicationByTeacherId(teacherId);
    }

    @Override
    public TeamApplication updateTeamApplication(TeamApplication teamApplication) {
        teamApplicationMapper.updateTeamApplication(teamApplication);
        return teamApplicationMapper.getTeamApplication(teamApplication.getTeamApplicationId());
    }
}
