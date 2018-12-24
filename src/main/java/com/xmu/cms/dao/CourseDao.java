package com.xmu.cms.dao;

import com.xmu.cms.entity.Course;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/11.
 * @version 1.0
 */
public interface CourseDao {

    List<Course> getAllCoursesByTeacherId(BigInteger teacherId);

    Integer deleteCourse(BigInteger courseId);

    List<Course> getAllCourseByStudentId(BigInteger studentId);

    Integer createCourse(Course course);

    Course getCourse(BigInteger courseId);

    void updateCourseTeamMainCourse(Course course);

    void updateCourseSeminarMainCourse(Course course);

    Course getTeamMainCourse(BigInteger courseId);
}
