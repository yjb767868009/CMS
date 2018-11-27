package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.TeamDao;
import com.xmu.cms.entity.Team;
import com.xmu.cms.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamDao teamDao;

    @Override
    public List<Team> getAllTeamInSeminar(Integer seminarId) {
        return teamDao.getAllTeamsInSeminar(seminarId);
    }
}
