package com.xmu.cms.controller;

import com.xmu.cms.entity.Question;
import com.xmu.cms.service.SeminarService;
import com.xmu.cms.support.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/12/19.
 * @version 1.0
 */
@RestController
public class QuestionController {
    @Autowired
    private SeminarService seminarService;


    @Secured({"ROLE_STUDENT", "ROLE_TEACHER"})
    @GetMapping(value = "/klassSeminar/{klassSeminarId}/question")
    public List<Question> getQuestionInKlassSeminar(@PathVariable("klassSeminarId") BigInteger klassSeminarId) {
        return seminarService.getQuestionInKlassSeminar(klassSeminarId);
    }

    @Secured("ROLE_STUDENT")
    @PostMapping(value = "/attendance/{attendanceId}/question")
    public Question askQuestion(UserInfo userInfo, @PathVariable("attendanceId") BigInteger attendanceId) {
        return seminarService.askQuestion(userInfo.getUserId(), attendanceId);
    }

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "/question/{questionId}")
    public Map<String, String> scoreQuestion(@PathVariable("questionId") BigInteger questionId,
                                             @RequestBody Question question) {
        question.setQuestionId(questionId);
        return seminarService.scoreQuestion(question);
    }
}
