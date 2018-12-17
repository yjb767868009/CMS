package com.xmu.cms.controller;

import com.xmu.cms.entity.*;
import com.xmu.cms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private UserService userService;

    @Autowired
    private FileService fileService;

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
                Teacher teacher = userService.getTeacherById(userId);
                return courseService.getAllCoursesByTeacher(teacher);
            case "student":
                Student student = userService.getStudentById(userId);
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
                                      @PathVariable("courseId") Integer courseId) {
        switch (userType) {
            case "teacher":
                return courseService.getTeamInCourse(courseId);
            case "student":
                return courseService.getTeamInCourseByStudent(courseId, userId);
            default:
                return null;
        }
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/{courseId}/noteam")
    public List<Student> getNoTeamStudent(@PathVariable("courseId") Integer courseId) {
        return userService.getNoTeamStudent(courseId);
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/{courseId}/class")
    public List<Klass> getKlassInCourse(@PathVariable("courseId") Integer courseId) {
        return courseService.getKlassInCourse(courseId);
    }

    @Secured("ROLE_TEACHER")
    @PostMapping(value = "/{courseId}/class")
    public Map<String, String> createClass(@PathVariable("courseId") Integer courseId,
                                           @RequestBody Klass klass) {
        return courseService.newKlass(courseId, klass);
    }


    @Secured("ROLE_TEACHER")
    @GetMapping(value = "/{courseId}/share")
    public List<Share> listAllShareByCourseId(@PathVariable("courseId") Integer courseId) {
        return courseService.getShareInCourse(courseId);
    }

    @Secured("ROLE_TEACHER")
    @GetMapping(value = "/{courseId}/share/{shareId}")
    public Map<String, String> deleteShare(@PathVariable("courseId") Integer courseId,
                                           @PathVariable("shareId") Integer shareId) {
        return courseService.deleteShare(courseId, shareId);
    }

}
