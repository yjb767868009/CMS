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
@RequestMapping(value = "/seminar")
public class SeminarController {
    @Autowired
    private SeminarService seminarService;


    @Secured("ROLE_TEACHER")
    @PostMapping(value = "")
    public Map<String, String> newSeminar(@RequestBody Seminar seminar) {
        return seminarService.newSeminar(seminar);
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/{seminarId}")
    public Seminar getSeminar(@PathVariable("seminarId") BigInteger seminarId) {
        return seminarService.getSeminarBySeminarId(seminarId);
    //获取讨论课所属班级
    @Secured({"ROLE_TEACHER","ROLE_STUDENT"})
    @GetMapping(value="{seminarId}/class")
    public Klass klass(@PathVariable("seminarId") Integer seminarId){
        //TODO
        return null;
    }

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "/{seminarId}")
    public Map<String, String> modifySeminar(@PathVariable("seminarId") BigInteger seminarId,
                                             @RequestBody Seminar seminar) {
        seminar.setSeminarId(seminarId);
        return seminarService.modifySeminar(seminar);
    }

    @Secured("ROLE_TEACHER")
    @DeleteMapping(value = "/{seminarId}")
    public Map<String, String> deleteSeminar(@PathVariable("seminarId") BigInteger seminarId) {
        return seminarService.deleteSeminar(seminarId);
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/{seminarId}")
    public Seminar getSeminar(@PathVariable("seminarId") Integer seminarId) {
        return seminarService.getSeminarBySeminarId(seminarId);
    }

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "{seminarId}/reportddl")
    public Map<String, String> modifySeminarReportDDL(@PathVariable("seminarId") BigInteger seminarId,
    @PutMapping(value = "{seminarId}/round")
    public Map<String, String> modifyRound(@PathVariable("seminarId") Integer seminarId,
                                                      @RequestBody Seminar seminar) {
        //TODO 设置讨论课轮次
        return null;
    }

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "{seminarId}/state")
    public Map<String, String> modifySeminarState(@PathVariable("seminarId") Integer seminarId,
                                                      @RequestBody Seminar seminar) {
        //TODO 设置讨论课状态
        return null;
    }

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "{seminarId}/round")
    public Map<String, String> modifySeminarReportDDL(@PathVariable("seminarId") Integer seminarId,
                                                      @RequestBody Seminar seminar) {
        //TODO 设置讨论课书面报告截止时间
        return null;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/seminar/{seminarId}/team/{teamId}/seminarscore")
    public SeminarScore getSeminarTeamScore(@PathVariable("seminarId") BigInteger seminarId,
                                            @PathVariable("teamId") BigInteger teamId) {
    @GetMapping(value = "/{seminarId}/team/{teamId}/seminarscore")
    public SeminarScore getSeminarTeamScore(@PathVariable("seminarId") Integer seminarId,
                                            @PathVariable("teamId") Integer teamId) {
        return seminarService.getSeminarTeamScore(seminarId, teamId);
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/seminar/{seminarId}/seminarscore")
    public List<SeminarScore> getSeminarScore(@PathVariable("seminarId") BigInteger seminarId) {
    @PutMapping(value = "/{seminarId}/team/{teamId}/seminarscore")
    public Map<String,String> updateSeminarTeamScore(@PathVariable("seminarId") Integer seminarId,
                                            @PathVariable("teamId") Integer teamId,
                                            @RequestBody SeminarScore seminarScore) {
        //TODO 按讨论课ID修改队伍讨论课成绩
        return null;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/{seminarId}/seminarscore")
    public List<SeminarScore> getSeminarScore(@PathVariable("seminarId") Integer seminarId) {
        return seminarService.getSeminarScore(seminarId);
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value="/{seminarId}/klass/{klassId}/question")
    public List<Question> getAllQuestion(@PathVariable("seminarId") Integer seminarId,
                                         @PathVariable("klassId") Integer klassId){
        //TODO 获取本节讨论课所有提问
        return null;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @PostMapping(value="/{seminarId}/klass/{klassId}/question")
    public Map<String,String> askQuestion(@PathVariable("seminarId") Integer seminarId,
                                         @PathVariable("klassId") Integer klassId,
                                          @RequestBody Question question){
        //TODO 提问
        return null;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value="/{seminarId}/klass/{klassId}/ppt")
    public void downloadPPT(@PathVariable("seminarId") Integer seminarId,
                                          @PathVariable("klassId") Integer klassId){
        //TODO 下载本次讨论课所有PPT
        return;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value="/{seminarId}/klass/{klassId}/report")
    public void downloadReport(@PathVariable("seminarId") Integer seminarId,
                            @PathVariable("klassId") Integer klassId){
        //TODO 下载本次讨论课所有报告
        return;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value="/{seminarId}/klass/{klassId}/attendance")
    public List<Attendance> getAttendance(@PathVariable("seminarId") Integer seminarId,
                            @PathVariable("klassId") Integer klassId){
        //TODO 下载本次讨论课所有PPT
        return null;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @PostMapping(value="/{seminarId}/klass/{klassId}/attendance")
    public Map<String,String> registion(@PathVariable("seminarId") Integer seminarId,
                                          @PathVariable("klassId") Integer klassId,
                                        @RequestBody Attendance attendance){
        //TODO 报名展示
        return null;
    }
}
