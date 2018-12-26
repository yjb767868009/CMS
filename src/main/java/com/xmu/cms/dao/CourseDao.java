package com.xmu.cms.dao;

import com.xmu.cms.entity.Course;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/11.
 * @version 1.0
 */
public interface CourseDao {
    /**
     * 获取老师的所有课程
     *
     * @param teacherId 老师id
     * @return 课程列表
     */
    List<Course> getAllCoursesByTeacherId(BigInteger teacherId);

    /**
     * 删除课程
     *
     * @param courseId 课程id
     */
    void deleteCourse(BigInteger courseId);

    /**
     * 获取学生的所有课程
     *
     * @param studentId 学生id
     * @return 课程列表
     */
    List<Course> getAllCourseByStudentId(BigInteger studentId);

    /**
     * 新建课程
     *
     * @param course 新的班级
     * @return 新建信息
     */
    Integer createCourse(Course course);

    /**
     * 根据id获取课程
     *
     * @param courseId 课程id
     * @return 课程
     */
    Course getCourse(BigInteger courseId);

    /**
     * 修改课程的共享队伍的主课程
     *
     * @param course 新的课程
     */
    void updateCourseTeamMainCourse(Course course);

    /**
     * 修改课程的共享讨论课的主课程
     *
     * @param course 新的课程
     */
    void updateCourseSeminarMainCourse(Course course);

    /**
     * 获取共享队伍的主课程
     *
     * @param courseId 课程id
     * @return 课程
     */
    Course getTeamMainCourse(BigInteger courseId);

    /**
     * 获取共享讨论课的主课程
     *
     * @param courseId 课程id
     * @return 课程
     */
    Course getSeminarMainCourse(BigInteger courseId);
}
