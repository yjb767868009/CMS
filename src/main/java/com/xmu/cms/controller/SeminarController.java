package com.xmu.cms.controller;

import com.xmu.cms.entity.Seminar;
import com.xmu.cms.entity.Team;
import com.xmu.cms.service.SeminarService;
import com.xmu.cms.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private TeamService teamService;

    @GetMapping(value = "")
    public Seminar getSeminarById(@PathVariable("seminarId") Integer seminarId) {
        return seminarService.getSeminarById(seminarId);
    }

    @GetMapping(value = "/teams")
    public List<Team> getTeamsInSeminar(@PathVariable("seminarId") Integer seminarId) {
        return teamService.getAllTeamInSeminar(seminarId);
    }
}
