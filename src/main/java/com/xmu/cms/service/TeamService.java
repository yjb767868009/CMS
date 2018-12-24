package com.xmu.cms.service;

import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Team;
import com.xmu.cms.entity.TeamApplication;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
public interface TeamService {
    Team newTeam(BigInteger courseId, BigInteger classId, BigInteger studentId, Team team);

    Team getTeamByTeamId(BigInteger teamId);

    Map<String, String> deleteTeam(BigInteger teamId);

    Map<String, String> teamAddMembers(BigInteger teamId, List<Student> students);

    Map<String, String> teamRemoveMember(BigInteger teamId, Student student);

    List<Team> getAllTeamsInSeminar(BigInteger seminarId);

    TeamApplication sendTeamApplication(TeamApplication teamApplication);
}
