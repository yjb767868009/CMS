package com.xmu.cms.controller;

import com.xmu.cms.entity.SeminarScore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value="")
public class SeminarScoreController {

    @PostMapping(value="/seminarscore")
    public Map<String,String> NewSeminarScore(@RequestBody SeminarScore seminarScore){
        //TODO 新建讨论课成绩
        return null;
    }
}
