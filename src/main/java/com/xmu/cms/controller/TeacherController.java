package com.xmu.cms.controller;

import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.service.*;
import com.xmu.cms.support.JsonResult;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    private SeminarService seminarService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private ClbumService clbumService;

    private Teacher getTeacherInSecurity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.toString());
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return teacherService.getTeacherByAccount(authentication.getName());
        }
        return null;
    }

    @GetMapping(value = "/courses")
    public List<Course> getAllCourse() {
        return courseService.getAllCoursesByTeacherId(getTeacherInSecurity().getTeacherId());
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

    @PutMapping(value = "/round/{roundId}/seminar/{seminarId}/modifySeminar")
    public Map<String, String> modifySeminar(@PathVariable("roundId") Integer roundId,
                                             @PathVariable("seminarId") Integer seminarId,
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

    @PatchMapping(value = "/seminar/{seminarId}/clbum/{clbumId}/start")
    public Map<String, String> startSeminar(@PathVariable("seminarId") Integer seminarId,
                                            @PathVariable("clbumId") Integer clbumId) {
        return seminarService.startClbumSeminar(seminarId, clbumId);
    }

    @PatchMapping(value = "/seminar/{seminarId}/clbum/{clbumId}/stop")
    public Map<String, String> stopSeminar(@PathVariable("seminarId") Integer seminarId,
                                           @PathVariable("clbumId") Integer clbumId) {
        return seminarService.stopClbumSeminar(seminarId, clbumId);
    }

    @PatchMapping(value = "/seminar/{seminarId}/clbum/{clbumId}/end")
    public Map<String, String> endSeminar(@PathVariable("seminarId") Integer seminarId,
                                          @PathVariable("clbumId") Integer clbumId) {
        return seminarService.endClbumSeminar(seminarId, clbumId);
    }

    @PatchMapping(value = "/attendance/{attendanceId}/setScore")
    public Map<String, String> setAttendancePresentationScore(@PathVariable("attendanceId") Integer attendanceId,
                                                              @RequestParam("presentationScore") Integer presentationScore) {
        return attendanceService.setAttendancePresentationScore(attendanceId, presentationScore);
    }
}
