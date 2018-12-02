package com.xmu.cms.controller;

import com.xmu.cms.aspect.CheckTeacherPermission;
import com.xmu.cms.aspect.CheckUserPermission;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.service.RoundService;
import com.xmu.cms.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

/**
 * @author JuboYu on 2018/12/1.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/round/{roundId}")
public class RoundController {
    @Autowired
    private RoundService roundService;

    @Autowired
    private SeminarService seminarService;

    @GetMapping(value = "/seminars")
    @CheckUserPermission
    public Seminar getAllSeminarInRound(@PathVariable(value = "roundId") Integer roundId) {
        return roundService.getAllSeminarInRound(roundId);
    }

    @PostMapping(value = "/newSeminar")
    @CheckTeacherPermission
    public String newSeminar(@PathVariable("roundId") Integer roundId,
                             @RequestParam("maxTeamNum") Integer maxTeamNum,
                             @RequestParam("topic") String topic,
                             @RequestParam("introduction") String introduction,
                             @RequestParam("signStartTime") Timestamp signStartTime,
                             @RequestParam("signEndTime") Timestamp signEndTime,
                             @RequestParam("signOrder") Boolean signOrder,
                             @RequestParam("visible") Boolean visible) {
        return seminarService.newSeminar(roundId, maxTeamNum, topic, introduction, signStartTime, signEndTime, signOrder, visible);
    }

    @PutMapping(value = "/seminar/{seminarId}/modifySeminar")
    @CheckTeacherPermission
    public String modifySeminar(@PathVariable("roundId") Integer roundId,
                                @PathVariable("seminarId") Integer seminarId,
                                @RequestParam("maxTeamNum") Integer maxTeamNum,
                                @RequestParam("topic") String topic,
                                @RequestParam("introduction") String introduction,
                                @RequestParam("signStartTime") Timestamp signStartTime,
                                @RequestParam("signEndTime") Timestamp signEndTime,
                                @RequestParam("signOrder") Boolean signOrder,
                                @RequestParam("visible") Boolean visible) {
        return seminarService.modifySeminar(seminarId, roundId, maxTeamNum, topic, introduction, signStartTime, signEndTime, signOrder, visible);
    }
}
