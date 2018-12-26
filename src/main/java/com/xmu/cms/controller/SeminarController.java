package com.xmu.cms.controller;

import com.xmu.cms.entity.*;
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


    @Secured("ROLE_TEACHER")
    @PostMapping(value = "/seminar")
    public Map<String, String> newSeminar(@RequestBody Seminar seminar) {
        return seminarService.newSeminar(seminar);
    }

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
        if (userInfo.getUserType().equals("student")) {
            message.put("attendance", seminarService.getStudentAttendanceInKlassSeminar(userInfo.getUserId(), klassId, seminarId));
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
        Map<String, String> message = new HashMap<String, String>();
        try {
            seminarService.startKlassSeminar(klassSeminarId);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/klassseminar/{klassSeminarId}/attendances")
    public List<Attendance> getAttendanceInKlassSeminar(@PathVariable("klassSeminarId") BigInteger klassSeminarId) {
        return seminarService.getAttendancesInKlassSeminar(klassSeminarId);
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
    @PutMapping(value = "/seminar/{seminarId}/team/{teamId}/seminarscore")
    public Map<String, String> modifyTeamSeminarScore(@PathVariable("seminarId") BigInteger seminarId,
                                                      @PathVariable("teamId") BigInteger teamId,
                                                      @RequestBody SeminarScore seminarScore) {
        Map<String, String> message = new HashMap<String, String>();
        try {
            seminarScore.setSeminar(new Seminar(seminarId));
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
    @PostMapping(value = "/klassseminar/{klassSeminarId}/attendance")
    public Map<String, String> attendance(UserInfo info,
                                          @PathVariable("klassSeminarId") BigInteger klassSeminarId,
                                          @RequestBody Attendance attendance) {
        Map<String, String> message = new HashMap<String, String>();
        try {
            attendance.setKlassSeminar(new KlassSeminar(klassSeminarId));
            seminarService.newAttendance(info.getUserId(), attendance);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/klassseminar/{klassSeminarId}/run")
    public Map<String, Object> getRunKlassSeminarInfo(@PathVariable("klassSeminarId") BigInteger klassSeminarId) {
        Map<String, Object> message = new HashMap<String, Object>();
        List<Attendance> attendances = seminarService.getAttendancesInKlassSeminar(klassSeminarId);
        message.put("attendances", attendances);
        List<Question> questions = seminarService.getQuestionInKlassSeminar(klassSeminarId);
        message.put("questions", questions);
        return message;
    }
}
