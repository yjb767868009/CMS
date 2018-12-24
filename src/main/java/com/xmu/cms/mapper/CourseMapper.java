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
}
