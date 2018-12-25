package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.TeamDao;
import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Team;
import com.xmu.cms.mapper.CourseMapper;
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

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Team> getTeamInCourse(BigInteger courseId) {
        List<Team> teams = teamMapper.getTeamInCourse(courseId);
        for (Team team : teams) {
            team.setMembers(studentMapper.getMembersInTeam(team.getTeamId()));
        }
        return teams;
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
        klassMapper.addMembers(klassId, studentId, team.getTeamId());
        for (Student student : students) {
            klassMapper.addMembers(klassId, student.getStudentId(), team.getTeamId());
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
    public Team addMembers(BigInteger teamId, List<Student> students) {
        Team team = teamMapper.getTeamByTeamId(teamId);
        for (Student student : students) {
            klassMapper.addMembers(team.getKlass().getKlassId(), team.getTeamId(), student.getStudentId());
        }
        return getFullTeam(teamId);
    }

    private Team getFullTeam(BigInteger teamId) {
        Team team = teamMapper.getTeamByTeamId(teamId);
        List<Student> members = studentMapper.getMembersInTeam(teamId);
        for (Student student : members) {
            List<Course> courses = courseMapper.getAllCourseByStudentId(student.getStudentId());
            student.setCourses(courses);
        }
        team.setMembers(members);
        return team;
    }

    @Override
    public Team removeMember(BigInteger teamId, Student student) {
        klassMapper.removeTeamStudent(teamId, student);
        return getFullTeam(teamId);
    }

    @Override
    public void updateTeamValid(Team team) {
        teamMapper.updateTeamValid(team);
    }

    @Override
    public Team getStudentTeamInKlassSeminar(BigInteger studentId, BigInteger klassSeminarId) {
        return teamMapper.getStudentTeamInKlassSeminar(studentId, klassSeminarId);
    }

    @Override
    public Team getStudentTeamInKlass(BigInteger studentId, BigInteger klassId) {
        return teamMapper.getStudentTeamInKlass(studentId, klassId);
    }
}
