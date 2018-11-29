package com.xmu.cms.controller;

import com.xmu.cms.aspect.CheckTeacherPermission;
import com.xmu.cms.entity.Course;
import com.xmu.cms.service.CourseService;
import com.xmu.cms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/courses")
    @CheckTeacherPermission
    public List<Course> getAllCourse(HttpSession session) {
        Integer teacherId = Integer.valueOf(session.getAttribute("userId").toString());
        return courseService.getAllCoursesByTeacherId(teacherId);
    }
}
