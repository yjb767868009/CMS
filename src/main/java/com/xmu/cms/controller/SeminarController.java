package com.xmu.cms.controller;

import com.xmu.cms.entity.Seminar;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/seminar")
public class SeminarController {
    @PostMapping(value = "/")
    public Map<String, String> newSeminar(@RequestBody Seminar seminar){
        //todo
        return null;
    }
}
