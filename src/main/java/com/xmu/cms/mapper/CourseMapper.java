package com.xmu.cms.mapper;

import com.xmu.cms.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author BiqunZhou on 2018/12/5.
 * @version 2.0
 */
@Mapper
public interface CourseMapper {
    List<Course> getAllCourseByTeacherId(@Param("teacherId") Integer teacherId);

    List<Course> getAllCourseByStudentId(@Param("studentId") Integer studentId);

    Integer createCourse(@Param("course") Course course);

    Integer deleteCourseById(@Param("courseId") Integer courseId);

    Course getCourseById(@Param("courseId") Integer courseId);
}
