package com.xmu.cms.controller;

import com.xmu.cms.aspect.CheckTeacherPermission;
import com.xmu.cms.entity.*;
import com.xmu.cms.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/seminar/{seminarId}")
public class SeminarController {
    @Autowired
    private SeminarInfoService seminarInfoService;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private PresentationService presentationService;

    @GetMapping(value = "/info")
    public SeminarInfo getSeminarById(@PathVariable("seminarId") Integer seminarId) {
        return seminarInfoService.getSeminarById(seminarId);
    }

    @GetMapping(value = "/teams")
    public List<Team> getTeamsInSeminar(@PathVariable("seminarId") Integer seminarId) {
        return teamService.getAllTeamsInSeminar(seminarId);
    }

    @GetMapping(value = "/presentations")
    public List<Presentation> getPresentationsInSeminar(@PathVariable("seminarId") Integer seminarId) {
        return presentationService.getPresentationsInSeminar(seminarId);
    }


    @GetMapping(value = "/teamNum")
    public Integer geTeamNumInSeminar(@PathVariable("seminarId") Integer seminarId) {
        return teamService.getAllTeamsInSeminar(seminarId).size();
    }

    @GetMapping(value = "/turningClass/{turningClassId}")
    @CheckTeacherPermission
    public Seminar getSeminar(@PathVariable("seminarId") Integer seminarId,
                              @PathVariable("turningClassId") Integer turningClassId){
        return seminarService.getSeminar(seminarId,turningClassId);
    }


    @PatchMapping(value = "/turningClass/{turningClassId}/start")
    @CheckTeacherPermission
    public String startSeminar(@PathVariable("seminarId") Integer seminarId,
                               @PathVariable("turningClassId") Integer turningClassId) {
        return seminarService.startSeminar(seminarId, turningClassId);
    }

    @PatchMapping(value = "/turningClass/{turningClassId}/stop")
    @CheckTeacherPermission
    public String stopSeminar(@PathVariable("seminarId") Integer seminarId,
                              @PathVariable("turningClassId") Integer turningClassId) {
        return seminarService.stopSeminar(seminarId, turningClassId);
    }

    @PatchMapping(value = "/turningClass/{turningClassId}/end")
    @CheckTeacherPermission
    public String endSeminar(@PathVariable("seminarId") Integer seminarId,
                             @PathVariable("turningClassId") Integer turningClassId) {
        return seminarService.endSeminar(seminarId, turningClassId);
    }

    @GetMapping(value = "/turningClass/{turningClassId}/scores")
    @CheckTeacherPermission
    public Score getAllScoreInSeminar(@PathVariable("seminarId") Integer seminarId,
                                      @PathVariable("turningClassId") Integer turningClassId) {
        return scoreService.getAllScoresInSeminar(seminarId, turningClassId);
    }

    @GetMapping(value = "/")
    public Presentation getNextPresentation(@PathVariable("seminarId") Integer seminarId) {
        return seminarService.getNextPresentation(seminarId);
    }
}
