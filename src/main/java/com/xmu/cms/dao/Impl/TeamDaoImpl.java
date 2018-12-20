package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.TeamDao;
import com.xmu.cms.entity.Team;
import com.xmu.cms.mapper.StudentMapper;
import com.xmu.cms.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JuboYu on 2018/12/16.
 * @version 1.0
 */
@Component
public class TeamDaoImpl implements TeamDao {

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Team> getTeamInCourse(Integer courseId) {
        List<Team> teams = teamMapper.getTeamInCourse(courseId);
        for (Team team : teams) {
            team.setMembers(studentMapper.getMembersInTeam(team.getTeamId()));
        }
        return teams;
    }

    @Override
    public Team getTeamInCourseByStudent(Integer courseId, Integer studentId) {
        Team team = teamMapper.getTeamInCourseByStudent(courseId, studentId);
        team.setMembers(studentMapper.getMembersInTeam(team.getTeamId()));
        return team;
    }

    @Override
    public List<Team> getAllTeamsInSeminar(Integer seminarId) {
        List<Team> teams = teamMapper.getAllTeamsInSeminar(seminarId);
        for (Team team : teams) {
            team.setMembers(studentMapper.getMembersInTeam(team.getTeamId()));
        }
        return teams;
    }
}
