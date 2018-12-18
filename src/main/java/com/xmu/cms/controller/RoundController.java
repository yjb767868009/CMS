package com.xmu.cms.controller;

import com.xmu.cms.entity.Round;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author JuboYu on 2018/12/18.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/round")
public class RoundController {

    @Autowired
    private SeminarService seminarService;

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/{roundId}/seminar")
    public List<Seminar> getSeminarInRound(@PathVariable("roundId") Integer roundId) {
        return seminarService.getAllSeminarInRound(roundId);
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/{roundId}")
    public Round getRoundById(@PathVariable("roundId") Integer roundId) {
        return seminarService.getRoundByRoundId(roundId);
    }
}
