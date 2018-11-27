package com.xmu.cms.service;

import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Seminar;

import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
public interface CourseService {
    List<Course> getAllCoursesByTeacherId(Integer teacherId);
}
