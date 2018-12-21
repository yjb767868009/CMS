package com.xmu.cms.controller;

import com.xmu.cms.entity.*;
import com.xmu.cms.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
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
    @PutMapping(value = "/seminar/{seminarId}/state")
    public Map<String, String> modifySeminarState(@PathVariable("seminarId") BigInteger seminarId,
                                                  @RequestBody Seminar seminar) {
        //TODO 设置讨论课状态
        return null;
    }

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "/seminar/{seminarId}/round")
    public Map<String, String> modifySeminarReportDDL(@PathVariable("seminarId") BigInteger seminarId,
                                                      @RequestBody Seminar seminar) {
        //TODO 设置讨论课书面报告截止时间
        return null;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/seminar/{seminarId}/team/{teamId}/seminarscore")
    public SeminarScore getSeminarTeamScore(@PathVariable("seminarId") BigInteger seminarId,
                                            @PathVariable("teamId") BigInteger teamId) {
        return seminarService.getSeminarTeamScore(seminarId, teamId);
    }

    @PutMapping(value = "/seminar/{seminarId}/team/{teamId}/seminarscore")
    public Map<String, String> updateSeminarTeamScore(@PathVariable("seminarId") BigInteger seminarId,
                                                      @PathVariable("teamId") BigInteger teamId,
                                                      @RequestBody SeminarScore seminarScore) {
        //TODO 按讨论课ID修改队伍讨论课成绩
        return null;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/seminar/{seminarId}/seminarscore")
    public List<SeminarScore> getSeminarScore(@PathVariable("seminarId") BigInteger seminarId) {
        return seminarService.getSeminarScore(seminarId);
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/seminar/{seminarId}/klass/{klassId}/question")
    public List<Question> getAllQuestion(@PathVariable("seminarId") BigInteger seminarId,
                                         @PathVariable("klassId") BigInteger klassId) {
        //TODO 获取本节讨论课所有提问
        return null;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @PostMapping(value = "/seminar/{seminarId}/klass/{klassId}/question")
    public Map<String, String> askQuestion(@PathVariable("seminarId") Integer seminarId,
                                           @PathVariable("klassId") Integer klassId,
                                           @RequestBody Question question) {
        //TODO 提问
        return null;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/seminar/{seminarId}/klass/{klassId}/ppt")
    public void downloadPPT(@PathVariable("seminarId") BigInteger seminarId,
                            @PathVariable("klassId") BigInteger klassId) {
        //TODO 下载本次讨论课所有PPT
        return;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/seminar/{seminarId}/klass/{klassId}/report")
    public void downloadReport(@PathVariable("seminarId") BigInteger seminarId,
                               @PathVariable("klassId") BigInteger klassId) {
        //TODO 下载本次讨论课所有报告
        return;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/seminar/{seminarId}/class/{classId}/attendance")
    public List<Attendance> getAttendance(@PathVariable("seminarId") BigInteger seminarId,
                                          @PathVariable("classId") BigInteger klassId) {
        //TODO 下载本次讨论课所有PPT
        return null;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @PostMapping(value = "/seminar/{seminarId}/class/{classId}/attendance")
    public Map<String, String> registion(@PathVariable("seminarId") BigInteger seminarId,
                                         @PathVariable("classId") BigInteger klassId,
                                         @RequestBody Attendance attendance) {
        //TODO 报名展示
        return null;
    }
}
