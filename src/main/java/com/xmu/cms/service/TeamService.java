package com.xmu.cms.service;

import com.xmu.cms.dao.*;
import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Team;
import com.xmu.cms.entity.TeamApplication;
import com.xmu.cms.entity.strategy.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@Service
public class TeamService {
    @Autowired
    private TeamDao teamDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private StrategyDao strategyDao;

    @Autowired
    private TeamApplicationDao teamApplicationDao;

    public Team newTeam(BigInteger courseId, BigInteger classId, BigInteger studentId, Team team) throws Exception {
        Team newTeam = teamDao.newTeam(courseId, classId, studentId, team);
        Course teamMainCourse = courseDao.getTeamMainCourse(courseId);
        if (teamMainCourse != null) {
            courseId = teamMainCourse.getCourseId();
        }
        Strategy strategy = strategyDao.getCourseStrategy(courseId);
        Boolean valid = strategy.checkValid(newTeam);
        if (!valid.equals(newTeam.getValid())) {
            newTeam.setValid(valid);
            teamDao.updateTeamValid(team);
        }
        return newTeam;
    }

    public Team getTeamByTeamId(BigInteger teamId) {
        return teamDao.getTeamByTeamId(teamId);
    }

    public Map<String, String> deleteTeam(BigInteger teamId) {
        Map<String, String> message = new HashMap<String, String>(1);
        Integer count = teamDao.deleteTeam(teamId);
        if (count > 0) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    public Map<String, String> teamAddMembers(BigInteger teamId, List<Student> students) {
        Map<String, String> message = new HashMap<>(1);
        Team team = teamDao.addMembers(teamId, students);
        BigInteger courseId = team.getCourse().getCourseId();
        Course teamMainCourse = courseDao.getTeamMainCourse(courseId);
        if (teamMainCourse != null) {
            courseId = teamMainCourse.getCourseId();
        }
        Strategy strategy = strategyDao.getCourseStrategy(courseId);
        Boolean valid = strategy.checkValid(team);
        if (!team.getValid().equals(valid)) {
            team.setValid(valid);
            teamDao.updateTeamValid(team);
        }
        message.put("message", "Success");
        return message;
    }

    public Map<String, String> teamRemoveMember(BigInteger teamId, Student student) {
        Map<String, String> message = new HashMap<String, String>(1);
        Team team = teamDao.removeMember(teamId, student);
        BigInteger courseId = team.getCourse().getCourseId();
        Course teamMainCourse = courseDao.getTeamMainCourse(courseId);
        if (teamMainCourse != null) {
            courseId = teamMainCourse.getCourseId();
        }
        Strategy strategy = strategyDao.getCourseStrategy(courseId);
        Boolean valid = strategy.checkValid(team);
        if (!team.getValid().equals(valid)) {
            team.setValid(valid);
            teamDao.updateTeamValid(team);
        }
        message.put("message", "Success");
        return message;
    }

    public List<Team> getAllTeamsInSeminar(BigInteger seminarId) {
        return teamDao.getAllTeamsInSeminar(seminarId);
    }

    public TeamApplication sendTeamApplication(TeamApplication teamApplication) throws Exception {
        return teamApplicationDao.sendTeamApplication(teamApplication);
    }

    public Team getTeamAndMembers(BigInteger teamId) {
        Team team = teamDao.getTeamAndMembers(teamId);
        List<Student> students = team.getMembers();
        for (Student student : students) {
            if (student.getStudentId().equals(team.getLeader().getStudentId())) {
                students.remove(student);
                team.setMembers(students);
                break;
            }
        }
        return team;
    }

    public Team getCourseTeamAndMembers(BigInteger courseId, BigInteger teamId) {
        Team team = teamDao.getCourseTeamAndMembers(courseId, teamId);
        List<Student> students = team.getMembers();
        for (Student student : students) {
            if (student.getStudentId().equals(team.getLeader().getStudentId())) {
                students.remove(student);
                team.setMembers(students);
                break;
            }
        }
        return team;
    }
}
