package com.xmu.cms.dao;

import com.xmu.cms.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@Mapper
public interface CourseDao {
    List<Course> getAllCourseByTeacherId(@Param("teacherId") Integer teacherId);

    List<Course> getAllCourseByStudentId(@Param("studentId") Integer studentId);

    Integer deleteCourseById(@Param("courseId") Integer courseId);

    List<Course> getAllCourseByTeacherAccount(@Param("account") String account);

    Integer newCourse(@Param("courseId") Integer CourseId,
                      @Param("teacherId") Integer teacherId,
                      @Param("name") String name,
                      @Param("introduction") String introduction,
                      @Param("presentationWeight") Float presentationWeight,
                      @Param("reportWeight") Float reportWeight,
                      @Param("questionWeight") Float questionWeight,
                      @Param("teamStartDate") Timestamp teamStartDate,
                      @Param("teamEndDate") Timestamp teamEndDate);
}
