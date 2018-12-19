package com.xmu.cms.service;

import com.xmu.cms.entity.Team;

import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
public interface TeamService {
    List<Team> getAllTeamsInSeminar(Integer seminarId);
}
