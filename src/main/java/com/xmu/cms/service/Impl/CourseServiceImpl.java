package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.CourseDao;
import com.xmu.cms.dao.SeminarDao;
import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Seminar;
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
}
