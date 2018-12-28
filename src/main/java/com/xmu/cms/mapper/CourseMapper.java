package com.xmu.cms.mapper;

import com.xmu.cms.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @author BiqunZhou on 2018/12/5.
 * @version 2.0
 */
@Mapper
public interface CourseMapper {
    /**
     * 获取老师下的所有课程
     *
     * @param teacherId 老师id
     * @return 课程列表
     */
    List<Course> getAllCourseByTeacherId(@Param("teacherId") BigInteger teacherId);

    /**
     * 获取学生选的所有课程
     *
     * @param studentId 学生id
     * @return 课程列表
     */
    List<Course> getAllCourseByStudentId(@Param("studentId") BigInteger studentId);

    /**
     * 新建课程
     *
     * @param course 课程
     * @return 新建成功信息
     */
    Integer createCourse(@Param("course") Course course);

    /**
     * 删除课程
     *
     * @param courseId 课程id
     * @return 删除成功信息
     */
    Integer deleteCourseById(@Param("courseId") BigInteger courseId);

    /**
     * 获取课程
     *
     * @param courseId 课程id
     * @return 课程对象
     */
    Course getCourseById(@Param("courseId") BigInteger courseId);

    /**
     * 获得老师的主课程
     *
     * @param teacherId 老师id
     * @return 课程列表
     */
    List<Course> getMainShareCourseByTeacher(@Param("teacherId") BigInteger teacherId);

    /**
     * 获得老师的从课程
     *
     * @param teacherId 老师id
     * @return 课程列表
     */
    List<Course> getSubShareCourseByTeacher(@Param("teacherId") BigInteger teacherId);

    /**
     * 更新课程的共享队伍主课程
     *
     * @param course 新的课程
     */
    void updateCourseTeamMainCourse(@Param("course") Course course);

    /**
     * 更新课程的共享讨论课主课程
     *
     * @param receiveCourse 新的课程
     */
    void updateCourseSeminarMainCourse(@Param("course") Course receiveCourse);

    /**
     * 获得队伍的所属课程
     *
     * @param teamId 队伍id
     * @return 课程
     */
    Course getCourseByTeamId(@Param("teamId") BigInteger teamId);

    /**
     * 获取课程的队伍主课程
     *
     * @param courseId 课程id
     * @return 队伍主课程
     */
    Course getTeamMainCourse(@Param("courseId") BigInteger courseId);

    /**
     * 共享之后,删除课程原有的所有队伍
     *
     * @param course 课程
     */
    void deleteCourseTeam(@Param("course") Course course);

    /**
     * 根据课程id获得队伍共享课程
     *
     * @param courseId 课程id
     * @return 课程
     */
    Course getTeamMainCourseByCourseId(@Param("courseId") BigInteger courseId);

    /**
     * 根据课程id获得讨论课共享课程
     *
     * @param courseId 课程id
     * @return 课程
     */
    Course getSeminarMainCourseByCourseId(@Param("courseId") BigInteger courseId);

    List<Course> getAllCourse();
}
