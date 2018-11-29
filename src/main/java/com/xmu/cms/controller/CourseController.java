package com.xmu.cms.controller;

import com.xmu.cms.aspect.CheckTeacherPermission;
import com.xmu.cms.entity.Round;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.entity.TurningClass;
import com.xmu.cms.service.*;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
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
    private SeminarTurningClassService seminarTurningClassService;

    @Autowired
    private RoundService roundService;

    @GetMapping(value = "/rounds")
    public List<Round> getRounds(@PathVariable("courseId") Integer courseId) {
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

    @PostMapping(value = "/newSeminar")
    @CheckTeacherPermission
    public String newSeminar(@PathVariable("courseId") Integer courseId,
                             @RequestParam("topic") String topic,
                             @RequestParam("introduction") String introduction,
                             @RequestParam("visible") Boolean visible) {
        return seminarService.newSeminar(courseId, topic, introduction, visible);
    }

    @PutMapping(value = "/modifySeminar/{seminarId}")
    @CheckTeacherPermission
    public String modifySeminar(@PathVariable("courseId") Integer courseId,
                                @PathVariable("seminarId") Integer seminarId,
                                @RequestParam("topic") String topic,
                                @RequestParam("introduction") String introduction,
                                @RequestParam("visible") Boolean visible) {
        return seminarService.modifySeminar(courseId, seminarId, topic, introduction, visible);
    }

    @PostMapping(value = "/newSeminarTurningClass")
    @CheckTeacherPermission
    public String newSeminarTurningClass(@RequestParam("seminarId") Integer seminarId,
                                         @RequestParam("turningClassId") Integer turningClassId,
                                         @RequestParam("roundId") Integer roundId,
                                         @RequestParam("maxTeamNum") Integer maxTeamNum,
                                         @RequestParam("signStartTime") Date signStartTime,
                                         @RequestParam("signEndTime") Date signEndTime,
                                         @RequestParam("signOrder") Boolean signOrder) {
        return seminarTurningClassService.newSeminarTurningClass(seminarId, turningClassId, roundId, maxTeamNum, signStartTime, signEndTime, signOrder);
    }

    @PutMapping(value = "/modfiySeminarTurningClass")
    @CheckTeacherPermission
    public String modifySeminarTurningClass(@RequestParam("seminarId") Integer seminarId,
                                            @RequestParam("turningClassId") Integer turningClassId,
                                            @RequestParam("roundId") Integer roundId,
                                            @RequestParam("maxTeamNum") Integer maxTeamNum,
                                            @RequestParam("signStartTime") Date signStartTime,
                                            @RequestParam("signEndTime") Date signEndTime,
                                            @RequestParam("reportEndTime") Date reportEndTime,
                                            @RequestParam("status") Integer status,
                                            @RequestParam("signOrder") Boolean signOrder) {
        return seminarTurningClassService.modifySeminarTurningClass(seminarId, turningClassId, roundId, maxTeamNum, signStartTime, signEndTime, reportEndTime, status, signOrder);
    }
}
