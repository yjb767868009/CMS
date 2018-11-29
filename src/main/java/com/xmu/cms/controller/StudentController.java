package com.xmu.cms.controller;

import com.xmu.cms.aspect.CheckStudentPermission;
import com.xmu.cms.entity.Course;
import com.xmu.cms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/student")
public class StudentController {

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/courses")
    @CheckStudentPermission
    public List<Course> getAllCourse(HttpSession session) {
        Integer studentId = Integer.valueOf(session.getAttribute("userId").toString());
        return courseService.getAllCoursesByStudentId(studentId);
    }

}
