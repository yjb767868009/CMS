package com.xmu.cms.controller;

import com.xmu.cms.entity.Question;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author JuboYu on 2018/12/19.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "")
public class QuestionController {
    @PutMapping(value="/question/{questionId}")
    public Map<String,String> makeScore(@PathVariable(value="questionId") Integer questionId,
                                        @RequestBody Question questionScore){
        //TODO 给提问打分，修改提问打分
        return null;
    }
}
