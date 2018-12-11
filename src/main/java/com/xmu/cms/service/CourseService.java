package com.xmu.cms.service;

import com.xmu.cms.entity.Course;

import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
public interface CourseService {
    List<Course> getAllCoursesByTeacherId(Integer teacherId);

    Map<String, String> deleteCourseById(Integer courseId);

    List<Course> getAllCoursesByStudentId(Integer studentId);

    Map<String, String> createCourse(Course course);

    Course getCourse(Integer courseId);
}
