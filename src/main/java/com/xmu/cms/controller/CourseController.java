package com.xmu.cms.controller;

import com.xmu.cms.aspect.CheckTeacherPermission;
import com.xmu.cms.entity.Round;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.entity.TurningClass;
import com.xmu.cms.service.CourseService;
import com.xmu.cms.service.RoundService;
import com.xmu.cms.service.SeminarService;
import com.xmu.cms.service.TurningClassService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/course/{courseId}")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private TurningClassService turningClassService;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private RoundService roundService;

    @GetMapping(value = "/rounds")
    public List<Round> getRounds(@PathVariable("courseId") Integer courseId){
        return roundService.getRoundsByCourseId(courseId);
    }

    @GetMapping(value = "/seminars")
    public List<Seminar> getSeminars(@PathVariable("courseId") Integer courseId) {
        return seminarService.getSeminarsByCourseId(courseId);
    }

    @DeleteMapping(value = "")
    @CheckTeacherPermission
    public String deleteCourse(@PathVariable("courseId") Integer courseId) {
        return courseService.deleteCourseById(courseId);
    }

    @PostMapping(value = "/turningClass")
    @CheckTeacherPermission
    public String newTurningClass(@PathVariable("courseId") Integer courseId,
                                  @RequestParam(value = "name") String name,
                                  @RequestParam(value = "classTime") String classTime,
                                  @RequestParam(value = "classPlace") String classPlace) {
        return turningClassService.newTurningClass(courseId, name, classTime, classPlace);
    }
}
