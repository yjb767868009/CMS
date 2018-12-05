package com.xmu.cms.controller;

import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.entity.Student;
import com.xmu.cms.service.ClbumService;
import com.xmu.cms.service.CourseService;
import com.xmu.cms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ClbumService clbumService;

    @Autowired
    private StudentService studentService;

    private Student getStudentInSecurity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.toString());
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return studentService.getStudentByAccount(authentication.getName());
        }
        return null;
    }

    @GetMapping(value = "/courses")
    public List<Course> getAllCourse() {
        Integer studentId = getStudentInSecurity().getStudentId();
        return courseService.getAllCoursesByStudentId(studentId);
    }

    @GetMapping(value = "/course/{courseId}/clbumName")
    public List<Seminar> getStudentClbumNameByCourseId(@PathVariable("courseId") Integer courseId) {
        Integer studentId = getStudentInSecurity().getStudentId();
        return clbumService.getStudentClbumNameByCourseId(studentId, courseId);
    }

}
