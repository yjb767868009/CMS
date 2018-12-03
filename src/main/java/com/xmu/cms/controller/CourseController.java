package com.xmu.cms.controller;

import com.xmu.cms.entity.Round;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    private ClbumService clbumService;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private RoundService roundService;

    @GetMapping(value = "/rounds")
    public List<Round> getRounds(@PathVariable("courseId") Integer courseId) {
        return roundService.getRoundsByCourseId(courseId);
    }

    @GetMapping(value = "/seminars")
    public List<Seminar> getSeminarsByCourseId(@PathVariable("courseId") Integer courseId) {
        return seminarService.getSeminarsByCourseId(courseId);
    }

    @DeleteMapping(value = "")
    public Map<String,String> deleteCourse(@PathVariable("courseId") Integer courseId) {
        return courseService.deleteCourseById(courseId);
    }

    @PostMapping(value = "/clbum")
    public Map<String,String> newClbum(@PathVariable("courseId") Integer courseId,
                           @RequestParam("name") String name,
                           @RequestParam("classTime") String classTime,
                           @RequestParam("classPlace") String classPlace) {
        return clbumService.newClbum(courseId, name, classTime, classPlace);
    }
}
