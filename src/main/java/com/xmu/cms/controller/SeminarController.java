package com.xmu.cms.controller;

import com.xmu.cms.entity.*;
import com.xmu.cms.service.CourseService;
import com.xmu.cms.service.SeminarService;
import com.xmu.cms.support.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
@RestController
public class SeminarController {

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private CourseService courseService;


    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/seminar/{seminarId}")
    public Seminar getSeminar(@PathVariable("seminarId") BigInteger seminarId) {
        return seminarService.getSeminarBySeminarId(seminarId);
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/seminar/{seminarId}/class/{classId}")
    public Map<String, Object> getKlassSeminar(UserInfo userInfo,
                                               @PathVariable("seminarId") BigInteger seminarId,
                                               @PathVariable("classId") BigInteger klassId) {
        Map<String, Object> message = new HashMap<String, Object>(2);
        String student = "student";
        if (userInfo.getUserType().equals(student)) {
            message.put("attendance", seminarService.getStudentAttendanceInKlassAndSeminar(userInfo.getUserId(), klassId, seminarId));
        }
        message.put("klassSeminar", seminarService.getKlassSeminarByKlassAndSeminar(klassId, seminarId));
        message.put("seminar", seminarService.getSeminarBySeminarId(seminarId));
        return message;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/seminar/{seminarId}/class")
    public List<Klass> getKlassInSeminar(@PathVariable("seminarId") BigInteger seminarId) {
        return seminarService.getKlassInSeminar(seminarId);
    }

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "/seminar/{seminarId}")
    public Map<String, String> modifySeminar(@PathVariable("seminarId") BigInteger seminarId,
                                             @RequestBody Seminar seminar) {
        seminar.setSeminarId(seminarId);
        return seminarService.modifySeminar(seminar);
    }

    @Secured("ROLE_TEACHER")
    @DeleteMapping(value = "/seminar/{seminarId}")
    public Map<String, String> deleteSeminar(@PathVariable("seminarId") BigInteger seminarId) {
        return seminarService.deleteSeminar(seminarId);
    }

    @Secured("ROLE_TEACHER")
    @GetMapping(value = "/runklassseminar")
    public KlassSeminar getRunningKlassSeminar(UserInfo userInfo) {
        return seminarService.getRunningKlassSeminarByTeacherId(userInfo.getUserId());
    }

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "/klassseminar/{klassSeminarId}/start")
    public Map<String, String> startKlassSeminar(@PathVariable("klassSeminarId") BigInteger klassSeminarId) {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            seminarService.startKlassSeminar(klassSeminarId);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "/klasssemianr/{klassSeminarId}/reportddl")
    public Map<String, String> modifyKlassSeminarReportDDL(@PathVariable("klassSeminarId") BigInteger klassSeminarId,
                                                           @RequestBody KlassSeminar klassSeminar) {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            klassSeminar.setKlassSeminarId(klassSeminarId);
            seminarService.modifyKlassSeminarReportDDL(klassSeminar);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/seminar/{seminarId}/team/{teamId}/seminarscore")
    public SeminarScore getSeminarTeamScore(@PathVariable("seminarId") BigInteger seminarId,
                                            @PathVariable("teamId") BigInteger teamId) {
        return seminarService.getSeminarTeamScore(seminarId, teamId);
    }

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "/klassseminar/{klassSeminarId}/team/{teamId}/seminarscore")
    public Map<String, String> modifyTeamSeminarScore(@PathVariable("klassSeminarId") BigInteger klassSeminarId,
                                                      @PathVariable("teamId") BigInteger teamId,
                                                      @RequestBody SeminarScore seminarScore) {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            seminarScore.setKlassSeminar(new KlassSeminar(klassSeminarId));
            seminarScore.setTeam(new Team(teamId));
            seminarService.modifyTeamSeminarScore(seminarScore);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/seminar/{seminarId}/seminarscore")
    public List<SeminarScore> getSeminarScore(@PathVariable("seminarId") BigInteger seminarId) {
        return seminarService.getSeminarScore(seminarId);
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/klassseminar/{klassSeminarId}/attendance")
    public Map<String, Object> getAttendanceInKlassSeminar(UserInfo info, @PathVariable("klassSeminarId") BigInteger klassSeminarId) {
        Map<String, Object> message = new HashMap<>(2);
        try {
            List<Attendance> attendances = seminarService.getAttendancesInKlassSeminar(klassSeminarId);
            message.put("attendance", attendances);
            String studentType = "student";
            if (studentType.equals(info.getUserType())) {
                message.put("message", "other");
                Team team = courseService.getStudentTeamInKlassSeminar(info.getUserId(), klassSeminarId);
                if (team != null) {
                    for (Attendance attendance : attendances) {
                        if (attendance.getTeam().getTeamId().equals(team.getTeamId())) {
                            message.put("message", attendance.getTeamOrder());
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            message.put("message", "Error");
        }
        return message;
    }

    @Secured("ROLE_STUDENT")
    @PostMapping(value = "/klassseminar/{klassSeminarId}/attendance")
    public Map<String, String> attendance(UserInfo info,
                                          @PathVariable("klassSeminarId") BigInteger klassSeminarId,
                                          @RequestBody Attendance attendance) {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            seminarService.newAttendance(info.getUserId(), klassSeminarId, attendance);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/klassseminar/{klassSeminarId}/run")
    public Map<String, Object> getRunKlassSeminarInfo(@PathVariable("klassSeminarId") BigInteger klassSeminarId) {
        Map<String, Object> message = new HashMap<String, Object>(2);
        List<Attendance> attendances = seminarService.getAttendancesInKlassSeminar(klassSeminarId);
        message.put("attendances", attendances);
        List<Question> questions = seminarService.getQuestionInKlassSeminar(klassSeminarId);
        message.put("questions", questions);
        return message;
    }


}
