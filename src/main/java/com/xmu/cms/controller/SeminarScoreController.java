package com.xmu.cms.controller;

import com.xmu.cms.entity.Seminar;
import com.xmu.cms.entity.SeminarScore;
import com.xmu.cms.entity.Team;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "")
public class SeminarScoreController {

    @PostMapping(value = "/seminarscore")
    public Map<String, String> NewSeminarScore(@RequestBody SeminarScore seminarScore) {
        //TODO 新建讨论课成绩
        return null;
    }

}
