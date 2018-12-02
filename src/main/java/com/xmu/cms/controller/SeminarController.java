package com.xmu.cms.controller;

import com.xmu.cms.aspect.CheckTeacherPermission;
import com.xmu.cms.aspect.CheckUserPermission;
import com.xmu.cms.entity.*;
import com.xmu.cms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    private TeamService teamService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping(value = "")
    @CheckUserPermission
    public Seminar getSeminarById(@PathVariable("seminarId") Integer seminarId) {
        return seminarService.getSeminarBySeminarId(seminarId);
    }

    @GetMapping(value = "/teams")
    @CheckUserPermission
    public List<Team> getTeamsInSeminar(@PathVariable("seminarId") Integer seminarId) {
        return teamService.getAllTeamsInSeminar(seminarId);
    }

    @GetMapping(value = "/attendances")
    @CheckUserPermission
    public List<Attendance> getAttendancesInSeminar(@PathVariable("seminarId") Integer seminarId) {
        return attendanceService.getAttendancesInSeminar(seminarId);
    }


    @GetMapping(value = "/teamNum")
    @CheckUserPermission
    public Integer geTeamNumInSeminar(@PathVariable("seminarId") Integer seminarId) {
        return teamService.getAllTeamsInSeminar(seminarId).size();
    }

    @GetMapping(value = "/clbum/{clbumId}")
    @CheckTeacherPermission
    public Seminar getSeminar(@PathVariable("seminarId") Integer seminarId,
                              @PathVariable("clbumId") Integer clbumId){
        return seminarService.getClbumSeminar(seminarId,clbumId);
    }


    @PatchMapping(value = "/clbum/{clbumId}/start")
    @CheckTeacherPermission
    public Map<String, String> startSeminar(@PathVariable("seminarId") Integer seminarId,
                                            @PathVariable("clbumId") Integer clbumId) {
        return seminarService.startClbumSeminar(seminarId, clbumId);
    }

    @PatchMapping(value = "/clbum/{clbumId}/stop")
    @CheckTeacherPermission
    public Map<String,String> stopSeminar(@PathVariable("seminarId") Integer seminarId,
                              @PathVariable("clbumId") Integer clbumId) {
        return seminarService.stopClbumSeminar(seminarId, clbumId);
    }

    @PatchMapping(value = "/clbum/{clbumId}/end")
    @CheckTeacherPermission
    public Map<String,String> endSeminar(@PathVariable("seminarId") Integer seminarId,
                             @PathVariable("clbumId") Integer clbumId) {
        return seminarService.endClbumSeminar(seminarId, clbumId);
    }

    @GetMapping(value = "/clbum/{clbumId}/scores")
    @CheckTeacherPermission
    public Score getAllScoreInSeminar(@PathVariable("seminarId") Integer seminarId,
                                      @PathVariable("clbumId") Integer clbumId) {
        return scoreService.getAllScoresInClbumSeminar(seminarId, clbumId);
    }

    @GetMapping(value = "/")
    public Attendance getNextAttendance(@PathVariable("seminarId") Integer seminarId) {
        return seminarService.getNextAttendance(seminarId);
    }
}
