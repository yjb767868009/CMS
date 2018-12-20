package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.TeamDao;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Team;
import com.xmu.cms.mapper.KlassMapper;
import com.xmu.cms.mapper.StudentMapper;
import com.xmu.cms.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
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

    @Autowired
    private KlassMapper klassMapper;

    @Override
    public List<Team> getTeamInCourse(BigInteger courseId) {
        List<Team> teams = teamMapper.getTeamInCourse(courseId);
        for (Team team : teams) {
            team.setMembers(studentMapper.getMembersInTeam(team.getTeamId()));
        }
        return teams;
    }

    @Override
    public Team getTeamInCourseByStudent(BigInteger courseId, BigInteger studentId) {
        Team team = teamMapper.getTeamInCourseByStudent(courseId, studentId);
        team.setMembers(studentMapper.getMembersInTeam(team.getTeamId()));
        return team;
    }

    @Override
    public List<Team> getAllTeamsInSeminar(BigInteger seminarId) {
        List<Team> teams = teamMapper.getAllTeamsInSeminar(seminarId);
        for (Team team : teams) {
            team.setMembers(studentMapper.getMembersInTeam(team.getTeamId()));
        }
        return teams;
    }

    @Override
    public Team newTeam(BigInteger courseId, BigInteger klassId, BigInteger studentId, Team newTeam) {
        teamMapper.insertTeam(klassId, courseId, studentId, newTeam);
        Team team = teamMapper.getTeamByKlass(klassId, studentId);
        List<Student> students = newTeam.getMembers();
        klassMapper.updateStudent(klassId, studentId, courseId, team.getTeamId());
        for (Student student : students) {
            klassMapper.updateStudent(klassId, student.getStudentId(), courseId, team.getTeamId());
        }
        return team;
    }

    @Override
    public Team getTeamByTeamId(BigInteger teamId) {
        return teamMapper.getTeamByTeamId(teamId);
    }

    @Override
    public Integer deleteTeam(BigInteger teamId) {
        klassMapper.deleteTeamStudent(teamId);
        return teamMapper.deleteTeamByTeamId(teamId);
    }

    @Override
    public Integer addMembers(BigInteger teamId, List<Student> students) {
        Integer count = 0;
        Team team = teamMapper.getTeamByTeamId(teamId);
        for (Student student : students) {
            count += klassMapper.addMembers(team.getKlass().getKlassId(), student.getStudentId());
        }
        return count;
    }
}
