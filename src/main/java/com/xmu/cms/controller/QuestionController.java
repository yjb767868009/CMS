package com.xmu.cms.controller;

import com.xmu.cms.entity.Question;
import com.xmu.cms.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JuboYu on 2018/12/19.
 * @version 1.0
 */
@RestController
public class QuestionController {
    @Autowired
    private SeminarService seminarService;

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "/question/{questionId}")
    public Map<String, String> scoreQuestion(@PathVariable("questionId") BigInteger questionId,
                                             @RequestBody Question question) {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            question.setQuestionId(questionId);
            seminarService.scoreQuestion(question);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }
}
