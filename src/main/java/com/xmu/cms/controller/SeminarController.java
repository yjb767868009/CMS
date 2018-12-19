package com.xmu.cms.controller;

import com.xmu.cms.entity.Seminar;
import com.xmu.cms.entity.SeminarScore;
import com.xmu.cms.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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
    public Seminar getSeminar(@PathVariable("seminarId") Integer seminarId) {
        return seminarService.getSeminarBySeminarId(seminarId);
    }

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "/{seminarId}")
    public Map<String, String> modifySeminar(@PathVariable("seminarId") Integer seminarId,
                                             @RequestBody Seminar seminar) {
        seminar.setSeminarId(seminarId);
        return seminarService.modifySeminar(seminar);
    }

    @Secured("ROLE_TEACHER")
    @DeleteMapping(value = "/{seminarId}")
    public Map<String, String> deleteSeminar(@PathVariable("seminarId") Integer seminarId) {
        return seminarService.deleteSeminar(seminarId);
    }

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "{seminarId}/reportddl")
    public Map<String, String> modifySeminarReportDDL(@PathVariable("seminarId") Integer seminarId,
                                                      @RequestBody Seminar seminar) {
        seminar.setSeminarId(seminarId);
        return seminarService.modifySeminarReportDDL(seminar);
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/seminar/{seminarId}/team/{teamId}/seminarscore")
    public SeminarScore getSeminarTeamScore(@PathVariable("seminarId") Integer seminarId,
                                            @PathVariable("teamId") Integer teamId) {
        return seminarService.getSeminarTeamScore(seminarId, teamId);
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/seminar/{seminarId}/seminarscore")
    public List<SeminarScore> getSeminarScore(@PathVariable("seminarId") Integer seminarId) {
        return seminarService.getSeminarScore(seminarId);
    }
}
