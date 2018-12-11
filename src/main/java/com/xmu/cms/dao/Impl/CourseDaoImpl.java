package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.CourseDao;
import com.xmu.cms.entity.Course;
import com.xmu.cms.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JuboYu on 2018/12/11.
 * @version 1.0
 */
@Component
public class CourseDaoImpl implements CourseDao {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> getAllCoursesByTeacherId(Integer teacherId) {
        return courseMapper.getAllCourseByTeacherId(teacherId);
    }

    @Override
    public Integer deleteCourse(Integer courseId) {
        return null;
    }

    @Override
    public List<Course> getAllCourseByStudentId(Integer studentId) {
        return null;
    }

    @Override
    public Integer createCourse(Course course) {
        return null;
    }

    @Override
    public Course getCourse(Integer courseId) {
        return courseMapper.getCourseById(courseId);
    }
}
