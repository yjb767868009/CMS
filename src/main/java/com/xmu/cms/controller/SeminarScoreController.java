package com.xmu.cms.controller;

import com.xmu.cms.entity.SeminarScore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "")
public class SeminarScoreController {

    @PostMapping(value = "/seminarscore")
    public Map<String, String> newSeminarScore(@RequestBody SeminarScore seminarScore) {
        //TODO 新建讨论课成绩
        return null;
    }

}
