package com.xmu.cms.controller;

import com.xmu.cms.entity.Seminar;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.service.CourseService;
import com.xmu.cms.service.FileService;
import com.xmu.cms.service.SeminarService;
import com.xmu.cms.service.UserService;
import com.xmu.cms.support.JWTUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {
    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private FileService fileService;

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "")
    public List<Teacher> getAllTeachers() {
        return userService.getAllTeachers();
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(value = "")
    public Map<String, String> newTeacher(@RequestBody Teacher teacher) {
        return userService.createTeacher(teacher);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/{teacherId}/information")
    public Map<String, String> modifyTeacherInfo(@PathVariable("teacherId") Integer teacherId,
                                                 @RequestBody Teacher teacher) {
        return userService.updateTeacherInfo(teacherId, teacher);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/{teacherId}/password")
    public Map<String, String> modifyTeacherPassword(@PathVariable("teacherId") Integer teacherId,
                                                     @RequestBody Teacher teacher) {
        return userService.updateTeacherPassword(teacherId, teacher);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/{teacherId}")
    public Map<String, String> deleteTeacher(@PathVariable("teacherId") Integer teacherId) {
        return userService.deleteTeacher(teacherId);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/searchByName")
    public List<Teacher> getTeacherByName(@RequestParam("name") String name) {
        return userService.getTeacherByName(name);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/searchByAccount")
    public Teacher getTeacherByAccount(@RequestParam("account") String account) {
        return userService.getTeacherByAccount(account);
    }

    @PostMapping(value = "/course/{courseId}/round")
    public Map<String, String> newRound(@PathVariable("courseId") Integer courseId,
                                        @RequestParam("roundNum") Integer roundNum,
                                        @RequestParam("presentationScoreType") Integer presentationScoreType,
                                        @RequestParam("reportScoreType") Integer reportScoreType,
                                        @RequestParam("questionScoreType") Integer questionScoreType) {
        return seminarService.newRound(courseId, roundNum, presentationScoreType, reportScoreType, questionScoreType);
    }

    @PutMapping(value = "/seminar/{seminarId}/modifySeminar")
    public Map<String, String> modifySeminar(@PathVariable("seminarId") Integer seminarId,
                                             @RequestParam("roundId") Integer roundId,
                                             @RequestParam("maxTeamNum") Integer maxTeamNum,
                                             @RequestParam("topic") String topic,
                                             @RequestParam("introduction") String introduction,
                                             @RequestParam("signStartTime") Timestamp signStartTime,
                                             @RequestParam("signEndTime") Timestamp signEndTime,
                                             @RequestParam("signOrder") Boolean signOrder,
                                             @RequestParam("visible") Boolean visible) {
        return seminarService.modifySeminar(seminarId, roundId, maxTeamNum, topic, introduction, signStartTime, signEndTime, signOrder, visible);
    }

    @PostMapping(value = "/seminar")
    public Map<String, String> newSeminar(@RequestParam("roundId") Integer roundId,
                                          @RequestParam("maxTeamNum") Integer maxTeamNum,
                                          @RequestParam("topic") String topic,
                                          @RequestParam("introduction") String introduction,
                                          @RequestParam("signStartTime") Timestamp signStartTime,
                                          @RequestParam("signEndTime") Timestamp signEndTime,
                                          @RequestParam("signOrder") Boolean signOrder,
                                          @RequestParam("visible") Boolean visible) {
        return seminarService.newSeminar(roundId, maxTeamNum, topic, introduction, signStartTime, signEndTime, signOrder, visible);
    }

    @PatchMapping(value = "/klassSeminar/{klassSeminarId}/start")
    public Map<String, String> startSeminar(@PathVariable("klassSeminarId") Integer klassSeminarId) {
        return seminarService.startKlassSeminar(klassSeminarId);
    }

    @PatchMapping(value = "/klassSeminar/{klassSeminarId}/stop")
    public Map<String, String> stopSeminar(@PathVariable("klassSeminarId") Integer klassSeminarId) {
        return seminarService.stopKlassSeminar(klassSeminarId);
    }

    @PatchMapping(value = "/klassSeminar/{klassSeminarId}/end")
    public Map<String, String> endSeminar(@PathVariable("klassSeminarId") Integer klassSeminarId) {
        return seminarService.endKlassSeminar(klassSeminarId);
    }

    @PatchMapping(value = "/attendance/{attendanceId}/setScore")
    public Map<String, String> setAttendancePresentationScore(@PathVariable("attendanceId") Integer attendanceId,
                                                              @RequestParam("presentationScore") Integer presentationScore) {
        return seminarService.setAttendancePresentationScore(attendanceId, presentationScore);
    }

    @GetMapping(value = "/runningSeminar")
    public Seminar getRunningSeminar() {
        Integer teacherId = JWTUntil.getToken().getUserId();
        return seminarService.getRunningSeminarByTeacherId(teacherId);
    }

}
