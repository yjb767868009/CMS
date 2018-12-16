package com.xmu.cms.controller;

import com.xmu.cms.entity.*;
import com.xmu.cms.service.CourseService;
import com.xmu.cms.service.KlassService;
import com.xmu.cms.service.StudentService;
import com.xmu.cms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/12/16.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;


    @Autowired
    private KlassService klassService;

    @Secured("ROLE_TEACHER")
    @PostMapping(value = "/")
    public Map<String, String> createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }


    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/")
    public List<Course> getCourses(@RequestParam("userId") Integer userId,
                                   @RequestParam("userType") String userType) {
        switch (userType) {
            case "teacher":
                Teacher teacher = teacherService.getTeacherById(userId);
                return courseService.getAllCoursesByTeacher(teacher);
            case "student":
                Student student = studentService.getStudentById(userId);
                return courseService.getAllCoursesByStudent(student);
            default:
                return null;
        }
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/{courseId}")
    public Course getCourse(@PathVariable("courseId") Integer courseId) {
        return courseService.getCourse(courseId);
    }

    @Secured("ROLE_TEACHER")
    @DeleteMapping(value = "/{courseId}")
    public Map<String, String> deleteCourse(@PathVariable("courseId") Integer courseId) {
        return courseService.deleteCourseById(courseId);
    }

    @GetMapping(value = "/{courseId}/score")
    public SeminarScore getScoreInCourse() {
        //TODO
        return null;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/{courseId}/team")
    public List<Team> getTeamInCourse(@RequestParam("userId") Integer userId,
                                      @RequestParam("userType") String userType,
                                      @RequestBody Course course) {
        switch (userType) {
            case "teacher":
                return courseService.getTeamInCourse(course);
            case "student":
                Student student = studentService.getStudentById(userId);
                return courseService.getTeamInCourseByStudent(course, student);
            default:
                return null;
        }
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/{courseId}/noteam")
    public List<Student> getNoTeamStudent(@PathVariable("courseId") Integer courseId) {
        return studentService.getNoTeamStudent(courseId);
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/{courseId}/class")
    public List<Klass> getKlassInCourse(@PathVariable("courseId") Integer courseId) {
        return klassService.getKlassInCourse(courseId);
    }
}
