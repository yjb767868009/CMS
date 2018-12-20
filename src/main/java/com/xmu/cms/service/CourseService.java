package com.xmu.cms.service;

import com.xmu.cms.entity.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
public interface CourseService {
    List<Course> getAllCoursesByTeacher(Teacher teacher);

    Map<String, String> deleteCourseById(BigInteger courseId);

    List<Course> getAllCoursesByStudent(Student student);

    Map<String, String> createCourse(Course course);

    Course getCourse(BigInteger courseId);

    List<Team> getTeamInCourse(BigInteger courseId);

    List<Team> getTeamInCourseByStudent(BigInteger courseId, BigInteger studentId);

    Map<String, String> newKlass(BigInteger courseId, Klass klass);

    List<Klass> getKlassInCourse(BigInteger courseId);

    Map<String, String> deleteKlass(BigInteger classId);

    List<ShareTeam> getShareInCourse(BigInteger courseId);

    Map<String, String> deleteShare(BigInteger courseId, BigInteger shareId);

    Team newTeam(BigInteger courseId, BigInteger classId, BigInteger studentId, Team team);

    Team getTeamByTeamId(BigInteger  teamId);

    Map<String, String> deleteTeam(BigInteger  teamId);

    Map<String, String> teamAddMembers(BigInteger  teamId, List<Student> students);

    Map<String, String> teamRemoveMember(BigInteger  teamId);
}
