package com.xmu.cms.controller;

import com.xmu.cms.entity.Round;
import com.xmu.cms.entity.RoundScore;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "/{roundId}")
    public Map<String, String> modifyRound(@PathVariable("roundId") Round round) {
        return seminarService.modifyRound(round);
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/round/{roundId}/roundscore")
    public List<RoundScore> getRoundScore(@PathVariable("roundId") Integer roundId) {
        return seminarService.getRoundScore(roundId);
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/round/{roundId}/team/{teamId}/roundscore")
    public RoundScore getRoundTeamScore(@PathVariable("roundId") Integer roundId,
                                        @PathVariable("teamId") Integer teamId) {
        return seminarService.getRoundTeamScore(roundId, teamId);
    }
}