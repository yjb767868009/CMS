package com.xmu.cms.dao;

import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Klass;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Team;
import com.xmu.cms.mapper.CourseMapper;
import com.xmu.cms.mapper.KlassMapper;
import com.xmu.cms.mapper.StudentMapper;
import com.xmu.cms.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/16.
 * @version 1.0
 */
@Component
public class TeamDao {

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private KlassMapper klassMapper;

    @Autowired
    private CourseMapper courseMapper;

    public List<Team> getTeamAndMembersInCourse(BigInteger courseId) {
        List<Team> teams = teamMapper.getTeamInCourse(courseId);
        for (Team team : teams) {
            team.setMembers(studentMapper.getMembersInTeam(team.getTeamId()));
        }
        return teams;
    }

    public List<Team> getTeamInCourse(BigInteger courseId) {
        return teamMapper.getTeamInCourse(courseId);
    }

    public List<Team> getAllTeamsInSeminar(BigInteger seminarId) {
        List<Team> teams = teamMapper.getAllTeamsInSeminar(seminarId);
        for (Team team : teams) {
            team.setMembers(studentMapper.getMembersInTeam(team.getTeamId()));
        }
        return teams;
    }

    @Transactional(rollbackFor = Exception.class)
    public Team newTeam(BigInteger courseId, BigInteger klassId, BigInteger studentId, Team team) throws Exception {
        Team myTeam = teamMapper.getStudentTeamInKlass(studentId, klassId);
        if (myTeam != null) {
            throw new Exception("已创建队伍");
        }
        Team bigTeam = teamMapper.getLastTeamInKlass(klassId);
        Integer newKlassSerial;
        Integer newTeamSerial;
        if (bigTeam != null) {
            newKlassSerial = bigTeam.getKlassSerial();
            newTeamSerial = bigTeam.getTeamSerial() + 1;
        } else {
            newKlassSerial = klassMapper.getKlassByKlassId(klassId).getKlassSerial();
            newTeamSerial = 1;
        }
        team.setKlassSerial(newKlassSerial);
        team.setTeamSerial(newTeamSerial);
        team.setLeader(new Student(studentId));
        team.setKlass(new Klass(klassId));
        team.setCourse(new Course(courseId));
        teamMapper.insertTeam(team);
        Team newTeam = teamMapper.getTeamBySerial(newKlassSerial, newTeamSerial);
        klassMapper.addTeam(klassId, newTeam.getTeamId());
        klassMapper.addMembers(newTeam.getTeamId(), studentId);
        List<Student> students = team.getMembers();
        if (students != null) {
            for (Student student : students) {
                klassMapper.addMembers(newTeam.getTeamId(), student.getStudentId());
            }
        }
        return getFullTeam(newTeam.getTeamId());
    }

    public Team getTeamByTeamId(BigInteger teamId) {
        return teamMapper.getTeamByTeamId(teamId);
    }

    public Integer deleteTeam(BigInteger teamId) {
        klassMapper.deleteTeamStudent(teamId);
        klassMapper.deleteKlassTeam(teamId);
        return teamMapper.deleteTeamByTeamId(teamId);
    }

    public Team addMembers(BigInteger teamId, List<Student> students) {
        Team team = teamMapper.getTeamByTeamId(teamId);
        for (Student student : students) {
            Team findTeam = teamMapper.getStudentTeamInKlass(student.getStudentId(), team.getKlass().getKlassId());
            if (findTeam == null) {
                klassMapper.addMembers(team.getTeamId(), student.getStudentId());
            }
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

    public Team removeMember(BigInteger teamId, Student student) {
        klassMapper.removeTeamStudent(teamId, student);
        return getFullTeam(teamId);
    }

    public void updateTeamValid(Team team) {
        teamMapper.updateTeamValid(team);
    }

    public Team getStudentTeamInKlassSeminar(BigInteger studentId, BigInteger klassSeminarId) {
        return teamMapper.getStudentTeamInKlassSeminar(studentId, klassSeminarId);
    }

    public Team getStudentTeamInKlass(BigInteger studentId, BigInteger klassId) {
        return teamMapper.getStudentTeamInKlass(studentId, klassId);
    }

    public List<Team> getSimpleTeamInCourse(BigInteger courseId) {
        return teamMapper.getSimpleTeamInCourse(courseId);
    }

    public Team getStudentTeamInCourse(BigInteger studentId, BigInteger courseId) {
        return teamMapper.getStudentTeamInCourse(studentId, courseId);
    }

    public Team getTeamAndMembers(BigInteger teamId) {
        Team team = teamMapper.getTeamByTeamId(teamId);
        team.setMembers(studentMapper.getMembersInTeam(team.getTeamId()));
        return team;
    }

    public Team getStudentTeamInRound(BigInteger studentId, BigInteger roundId) {
        return teamMapper.getStudentTeamInRound(studentId, roundId);
    }

    public List<Course> getAllCourse() {
        return courseMapper.getAllCourse();
    }
}
