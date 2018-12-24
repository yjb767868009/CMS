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
    List<Course> getAllCourseByTeacherId(@Param("teacherId") BigInteger teacherId);

    List<Course> getAllCourseByStudentId(@Param("studentId") BigInteger studentId);

    Integer createCourse(@Param("course") Course course);

    Integer deleteCourseById(@Param("courseId") BigInteger courseId);

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
    void updateCourseTeamMainCourse(Course course);// TODO: 2018/12/24

    /**
     * 更新课程的共享讨论课主课程
     *
     * @param receiveCourse 新的课程
     */
    void updateCourseSeminarMainCourse(Course receiveCourse);// TODO: 2018/12/24

    /**
     * 获得队伍的所属课程
     *
     * @param teamId 队伍id
     * @return 课程
     */
    Course getCourseByTeamId(BigInteger teamId); // TODO: 2018/12/24
}
