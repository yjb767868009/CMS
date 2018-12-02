package com.xmu.cms.dao;

import com.xmu.cms.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@Mapper
public interface CourseDao {
    List<Course> getAllCourseByTeacherId(@Param("teacherId") Integer teacherId);

    List<Course> getAllCourseByStudentId(@Param("studentId") Integer studentId);

    Integer deleteCourseById(@Param("id") Integer id);

    List<Course> getAllCourseByTeacherAccount(@Param("account") String account);
}
