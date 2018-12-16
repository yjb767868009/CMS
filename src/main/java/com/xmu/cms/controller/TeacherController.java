package com.xmu.cms.controller;

import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.service.*;
import com.xmu.cms.support.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private RoundService roundService;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private ClbumService clbumService;

    @Autowired
    private FileService fileService;

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(value = "/")
    public Map<String, String> newTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/{teacherId}/information")
    public Map<String, String> modifyTeacherInfo(@PathVariable("teacherId") Integer teacherId,
                                                 @RequestBody Teacher teacher) {
        return teacherService.updateTeacherInfo(teacherId, teacher);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/{teacherId}/password")
    public Map<String, String> modifyTeacherPassword(@PathVariable("teacherId") Integer teacherId,
                                                     @RequestBody Teacher teacher) {
        return teacherService.updateTeacherPassword(teacherId, teacher);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/{teacherId}")
    public Map<String, String> deleteTeacher(@PathVariable("teacherId") Integer teacherId) {
        return teacherService.deleteTeacher(teacherId);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/searchByName")
    public List<Teacher> getTeacherByName(@RequestParam("name") String name) {
        return teacherService.getTeacherByName(name);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/searchByAccount")
    public Teacher getTeacherByAccount(@RequestParam("account") String account) {
        return teacherService.getTeacherByAccount(account);
    }

    @PostMapping(value = "/course")
    public Map<String, String> createCourse(Course course) {
        return courseService.createCourse(course);
    }

    @DeleteMapping(value = "/course/{courseId}")
    public Map<String, String> deleteCourse(@PathVariable("courseId") Integer courseId) {
        return courseService.deleteCourseById(courseId);
    }

    @PostMapping(value = "/course/{courseId}/clbum")
    public Map<String, String> newClbum(@PathVariable("courseId") Integer courseId,
                                        @RequestParam("name") String name,
                                        @RequestParam("classTime") String classTime,
                                        @RequestParam("classPlace") String classPlace) {
        return clbumService.newClbum(courseId, name, classTime, classPlace);
    }

    @PostMapping(value = "/course/{courseId}/round")
    public Map<String, String> newRound(@PathVariable("courseId") Integer courseId,
                                        @RequestParam("roundNum") Integer roundNum,
                                        @RequestParam("presentationScoreType") Integer presentationScoreType,
                                        @RequestParam("reportScoreType") Integer reportScoreType,
                                        @RequestParam("questionScoreType") Integer questionScoreType) {
        return roundService.newRound(courseId, roundNum, presentationScoreType, reportScoreType, questionScoreType);
    }

    @PostMapping(value = "/clbum/{clbumId}/uploadClbumFile")
    public Map<String, String> uploadClbumFile(@PathVariable("clbumId") Integer clbumId,
                                               @RequestParam("file") MultipartFile file) {
        return fileService.uploadClbumFile(clbumId, file);
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

    @PatchMapping(value = "/clbumSeminar/{clbumSeminarId}/start")
    public Map<String, String> startSeminar(@PathVariable("clbumSeminarId") Integer clbumSeminarId) {
        return seminarService.startClbumSeminar(clbumSeminarId);
    }

    @PatchMapping(value = "/clbumSeminar/{clbumSeminarId}/stop")
    public Map<String, String> stopSeminar(@PathVariable("clbumSeminarId") Integer clbumSeminarId) {
        return seminarService.stopClbumSeminar(clbumSeminarId);
    }

    @PatchMapping(value = "/clbumSeminar/{clbumSeminarId}/end")
    public Map<String, String> endSeminar(@PathVariable("clbumSeminarId") Integer clbumSeminarId) {
        return seminarService.endClbumSeminar(clbumSeminarId);
    }

    @PatchMapping(value = "/attendance/{attendanceId}/setScore")
    public Map<String, String> setAttendancePresentationScore(@PathVariable("attendanceId") Integer attendanceId,
                                                              @RequestParam("presentationScore") Integer presentationScore) {
        return attendanceService.setAttendancePresentationScore(attendanceId, presentationScore);
    }

    @GetMapping(value = "/runningSeminar")
    public Seminar getRunningSeminar() {
        Integer teacherId = Token.getToken().getUserId();
        return seminarService.getRunningSeminarByTeacherId(teacherId);
    }

}
