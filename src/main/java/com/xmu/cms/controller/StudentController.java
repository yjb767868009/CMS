package com.xmu.cms.controller;

import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.service.ClbumService;
import com.xmu.cms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/courses")
    public List<Course> getAllCourse(HttpSession session) {
        Integer studentId = Integer.valueOf(session.getAttribute("userId").toString());
        return courseService.getAllCoursesByStudentId(studentId);
    }

    @GetMapping(value = "/course/{courseId}/clbumName")
    public List<Seminar> getStudentClbumNameByCourseId(@PathVariable("courseId") Integer courseId,
                                                       HttpSession session) {
        Integer studentId = Integer.valueOf(session.getAttribute("userId").toString());
        return clbumService.getStudentClbumNameByCourseId(studentId, courseId);
    }

}
