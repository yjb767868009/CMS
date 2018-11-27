package com.xmu.cms.controller;

import com.xmu.cms.entity.Course;
import com.xmu.cms.service.CourseService;
import com.xmu.cms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/teacher/{teacherId}")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/courses")
    public List<Course> getAllCourse(@PathVariable("teacherId") Integer teacherId) {
        return courseService.getAllCoursesByTeacherId(teacherId);
    }
}
