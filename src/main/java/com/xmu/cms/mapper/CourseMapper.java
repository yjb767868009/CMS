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
    List<Course> getMainShareCourseByTeacher(BigInteger teacherId);

    /**
     * 获得老师的从课程
     *
     * @param teacherId 老师id
     * @return 课程列表
     */
    List<Course> getSubShareCourseByTeacher(BigInteger teacherId);
}
