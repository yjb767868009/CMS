package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.CourseDao;
import com.xmu.cms.entity.Course;
import com.xmu.cms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Course> getAllCoursesByTeacherId(Integer teacherId) {
        return courseDao.getAllCourseByTeacherId(teacherId);
    }

    @Override
    public String deleteCourseById(Integer courseId) {
        Integer count = courseDao.deleteCourseById(courseId);
        if (count == 0) {
            return "Delete no course";
        } else {
            return "Success";
        }
    }

    @Override
    public List<Course> getAllCoursesByStudentId(Integer studentId) {
        return courseDao.getAllCourseByStudentId(studentId);
    }
}
