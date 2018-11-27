package com.xmu.cms.controller;

import com.xmu.cms.entity.Seminar;
import com.xmu.cms.entity.Team;
import com.xmu.cms.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/seminar/{seminarId}")
public class SeminarController {
    @Autowired
    private SeminarService seminarService;

    @GetMapping(value = "")
    public Seminar getSeminarById(@PathVariable("seminarId") Integer seminarId) {
        return seminarService.getSeminarById(seminarId);
    }

    @GetMapping(value = "/teams")
    public Team getTeamsInSeminar(@PathVariable("seminarId") Integer seminarId) {
        return null;
    }
}
