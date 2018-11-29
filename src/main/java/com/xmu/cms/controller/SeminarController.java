package com.xmu.cms.controller;

import com.xmu.cms.aspect.CheckTeacherPermission;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.entity.SeminarTurningClass;
import com.xmu.cms.entity.Team;
import com.xmu.cms.service.SeminarService;
import com.xmu.cms.service.SeminarTurningClassService;
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
    private SeminarService seminarService;

    @Autowired
    private SeminarTurningClassService seminarTurningClassService;

    @Autowired
    private TeamService teamService;

    @GetMapping(value = "")
    public Seminar getSeminarById(@PathVariable("seminarId") Integer seminarId) {
        return seminarService.getSeminarById(seminarId);
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
        return seminarTurningClassService.startSeminarTurningClass(seminarId, turningClassId);
    }

    @PatchMapping(value = "/turningClass/{turningClassId}/stop")
    @CheckTeacherPermission
    public String stopSeminarTurningClass(@PathVariable("seminarId") Integer seminarId,
                                          @PathVariable("turningClassId") Integer turningClassId) {
        return seminarTurningClassService.stopSeminarTurningClass(seminarId, turningClassId);
    }

    @PatchMapping(value = "/turningClass/{turningClassId}/continue")
    @CheckTeacherPermission
    public String continueSeminarTurningClass(@PathVariable("seminarId") Integer seminarId,
                                              @PathVariable("turningClassId") Integer turningClassId) {
        return seminarTurningClassService.continueSeminarTurningClass(seminarId, turningClassId);
    }

    @PatchMapping(value = "/turningClass/{turningClassId}/end")
    @CheckTeacherPermission
    public String endSeminarTurningClass(@PathVariable("seminarId") Integer seminarId,
                                         @PathVariable("turningClassId") Integer turningClassId) {
        return seminarTurningClassService.endSeminarTurningClass(seminarId, turningClassId);
    }
}
