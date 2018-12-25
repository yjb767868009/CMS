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
    List<Course> getAllCoursesByTeacher(BigInteger teacherId);

    void deleteCourseById(BigInteger courseId);

    List<Course> getAllCoursesByStudent(Student student);

    Map<String, String> createCourse(Course course);

    Course getCourse(BigInteger courseId) throws Exception;

    List<Team> getTeamInCourse(BigInteger courseId);

    void newKlass(BigInteger courseId, Klass klass);

    List<Klass> getKlassInCourse(BigInteger courseId);

    void deleteKlass(BigInteger klassId);

    List<Object> getShareInCourse(BigInteger courseId);

    List<ShareTeam> getShareTeamInCourse(BigInteger courseId);

    List<ShareSeminar> getShareSeminarInCourse(BigInteger courseId);

    List<Klass> getKlassByStudent(BigInteger studentId);

    List<Course> getMainShareCourseByTeacher(BigInteger teacherId);

    List<Course> getSubShareCourseByTeacher(BigInteger teacherId);

    Integer deleteShareTeam(BigInteger shareTeamId);

    Integer deleteShareSeminar(BigInteger seminarShareId);

    ShareTeam newShareTeam(ShareTeam shareTeam);

    ShareSeminar newShareSeminar(ShareSeminar shareSeminar);

    List<ShareTeam> getShareTeamByTeacherId(BigInteger teacherId);

    List<ShareSeminar> getShareSeminarByTeacherId(BigInteger teacherId);

    List<TeamApplication> getTeamApplicationByTeacherId(BigInteger teacherId);

    ShareTeam updateShareTeam(ShareTeam shareTeam);

    ShareSeminar updateShareSeminar(ShareSeminar shareSeminar);

    TeamApplication updateTeamApplication(TeamApplication teamApplication);

    void uploadKlassFile(BigInteger klassId, List<Student> students) throws Exception;

    Team getStudentTeamInKlass(BigInteger studentId, BigInteger klassId);
}
