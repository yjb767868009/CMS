package com.xmu.cms.controller;

import com.xmu.cms.entity.Seminar;
import com.xmu.cms.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/course/{courseId}")
public class CourseController {
    @Autowired
    private SeminarService seminarService;

    @GetMapping(value = "/seminars")
    public List<Seminar> getSeminars(@PathVariable("courseId") Integer courseId) {
        return seminarService.getSeminarsByCourseId(courseId);
    }
}
