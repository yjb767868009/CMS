package com.xmu.cms.controller;

import com.xmu.cms.entity.Round;
import com.xmu.cms.entity.RoundScore;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.service.SeminarService;
import com.xmu.cms.support.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/12/18.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "")
public class RoundController {

    @Autowired
    private SeminarService seminarService;

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/round/{roundId}/seminar")
    public List<Seminar> getSeminarInRound(@PathVariable("roundId") BigInteger roundId) {
        return seminarService.getAllSeminarInRound(roundId);
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/round/{roundId}")
    public Round getRoundById(@PathVariable("roundId") BigInteger roundId) {
        return seminarService.getRoundByRoundId(roundId);
    }

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "/round/{roundId}")
    public Map<String, String> modifyRound(@PathVariable("roundId") BigInteger roundId,
                                           @RequestBody Round round) {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            round.setRoundId(roundId);
            seminarService.modifyRound(round);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured("ROLE_TEACHER")
    @GetMapping(value = "/round/{roundId}/team/{teamId}/score")
    public Map<String, Object> getTeamScoreInRound(@PathVariable("roundId") BigInteger roundId,
                                                   @PathVariable("teamId") BigInteger teamId) {
        return seminarService.getTeamRoundScoreAndSeminarScore(teamId, roundId);
    }

    @Secured("ROLE_STUDENT")
    @GetMapping(value = "/round/{roundId}/score")
    public Map<String, Object> getStudentRoundScoreAndSeminarScore(UserInfo info, @PathVariable("roundId") BigInteger roundId) {
        return seminarService.getStudentRoundScoreAndSeminarScore(info.getUserId(), roundId);
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/round/{roundId}/roundscore")
    public List<RoundScore> getRoundScore(@PathVariable("roundId") BigInteger roundId) {
        return seminarService.getRoundScore(roundId);
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/round/{roundId}/team/{teamId}/roundscore")
    public RoundScore getRoundTeamScore(@PathVariable("roundId") BigInteger roundId,
                                        @PathVariable("teamId") BigInteger teamId) {
        return seminarService.getRoundTeamScore(roundId, teamId);
    }
}
