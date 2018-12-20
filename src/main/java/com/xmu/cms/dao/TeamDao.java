package com.xmu.cms.dao;

import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Team;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/16.
 * @version 1.0
 */
public interface TeamDao {
    List<Team> getTeamInCourse(BigInteger courseId);

    Team getTeamInCourseByStudent(BigInteger courseId, BigInteger studentId);

    List<Team> getAllTeamsInSeminar(BigInteger seminarId);

    Team newTeam(BigInteger courseId, BigInteger classId, BigInteger studentId, Team team);

    Team getTeamByTeamId(BigInteger teamId);

    Integer deleteTeam(BigInteger teamId);

    Integer addMembers(BigInteger teamId, List<Student> students);
}
