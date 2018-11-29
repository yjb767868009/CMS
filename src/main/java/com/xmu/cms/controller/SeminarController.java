package com.xmu.cms.controller;

import com.xmu.cms.aspect.CheckTeacherPermission;
import com.xmu.cms.entity.Score;
import com.xmu.cms.entity.SeminarInfo;
import com.xmu.cms.entity.Team;
import com.xmu.cms.service.ScoreService;
import com.xmu.cms.service.SeminarInfoService;
import com.xmu.cms.service.SeminarService;
import com.xmu.cms.service.TeamService;
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

    @GetMapping(value = "")
    public SeminarInfo getSeminarById(@PathVariable("seminarId") Integer seminarId) {
        return seminarInfoService.getSeminarById(seminarId);
    }

    @GetMapping(value = "/teams")
    public List<Team> getTeamsInSeminar(@PathVariable("seminarId") Integer seminarId) {
        return teamService.getAllTeamInSeminar(seminarId);
    }

    @GetMapping(value = "/teamNum")
    public Integer geTeamNumInSeminar(@PathVariable("seminarId") Integer seminarId) {
        return teamService.getAllTeamInSeminar(seminarId).size();
    }

    @PatchMapping(value = "/turningClass/{turningClassId}/start")
    @CheckTeacherPermission
    public String startSeminarTurningClass(@PathVariable("seminarId") Integer seminarId,
                                           @PathVariable("turningClassId") Integer turningClassId) {
        return seminarService.startSeminarTurningClass(seminarId, turningClassId);
    }

    @PatchMapping(value = "/turningClass/{turningClassId}/stop")
    @CheckTeacherPermission
    public String stopSeminarTurningClass(@PathVariable("seminarId") Integer seminarId,
                                          @PathVariable("turningClassId") Integer turningClassId) {
        return seminarService.stopSeminarTurningClass(seminarId, turningClassId);
    }

    @PatchMapping(value = "/turningClass/{turningClassId}/end")
    @CheckTeacherPermission
    public String endSeminarTurningClass(@PathVariable("seminarId") Integer seminarId,
                                         @PathVariable("turningClassId") Integer turningClassId) {
        return seminarService.endSeminarTurningClass(seminarId, turningClassId);
    }

    @GetMapping(value = "/turningClass/{turningClassId}/scores")
    public Score getAllScoreInSeminarTurningClass(@PathVariable("seminarId") Integer seminarId,
                                                  @PathVariable("turningClassId") Integer turningClassId) {
        return scoreService.getAllScoresInSeminarTurningClass(seminarId, turningClassId);
    }
}
