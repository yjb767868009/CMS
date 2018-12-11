package com.xmu.cms.dao;

import com.xmu.cms.entity.Course;

import java.util.List;

/**
 * @author JuboYu on 2018/12/11.
 * @version 1.0
 */
public interface CourseDao {

    List<Course> getAllCoursesByTeacherId(Integer teacherId);

    Integer deleteCourse(Integer courseId);

    List<Course> getAllCourseByStudentId(Integer studentId);

    Integer createCourse(Course course);

    Course getCourse(Integer courseId);
}
