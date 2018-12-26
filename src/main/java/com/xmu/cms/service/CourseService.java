package com.xmu.cms.service;

import com.xmu.cms.entity.*;
import com.xmu.cms.entity.strategy.Strategy;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
public interface CourseService {
    /**
     * 删除课程
     *
     * @param courseId 课程id
     */
    void deleteCourseById(BigInteger courseId);

    /**
     * 新建课程
     *
     * @param course 新的课程
     * @throws Exception 新建异常
     */
    void createCourse(Course course) throws Exception;

    /**
     * 获取课程
     *
     * @param courseId 课程id
     * @return 课程
     * @throws Exception 获取异常
     */
    Course getCourse(BigInteger courseId) throws Exception;

    /**
     * 获取队伍中所有队伍
     *
     * @param courseId 课程id
     * @return 队伍列表
     */
    List<Team> getTeamInCourse(BigInteger courseId);

    /**
     * 新建班级
     *
     * @param courseId 课程id
     * @param klass    班级
     */
    void newKlass(BigInteger courseId, Klass klass);

    /**
     * 获取课程中的班级
     *
     * @param courseId 课程id
     * @return 班级列表
     */
    List<Klass> getKlassInCourse(BigInteger courseId);

    /**
     * 删除班级
     *
     * @param klassId 班级id
     */
    void deleteKlass(BigInteger klassId);

    /**
     * 返回课程中的共享信息
     *
     * @param courseId 课程id
     * @return 共享信息列表
     */
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

    Strategy getCourseStrategy(BigInteger courseId);

    void newCourseStrategy(List<Strategy> strategies);

}
