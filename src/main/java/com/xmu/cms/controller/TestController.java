package com.xmu.cms.controller;

import com.xmu.cms.entity.Attendance;
import com.xmu.cms.entity.Question;
import com.xmu.cms.service.SeminarService;
import com.xmu.cms.support.KlassSeminarRun;
import com.xmu.cms.support.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/")
public class TestController {

    @GetMapping(value = "/getUserType")
    public Object getUserType(HttpSession session) {
        return session.getAttribute("userType");
    }

    @GetMapping(value = "/getUser")
    public Object getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.toString());
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }

    @GetMapping(value = "/test/myinfo")
    public UserInfo getUserInfo(UserInfo info) {
        return info;
    }


    @Autowired
    private SeminarService seminarService;


    @MessageMapping("/{klassSeminarId}/question")
    @SendTo("/topic/klassSeminar/{klassSeminarId}")
    public KlassSeminarRun question(@DestinationVariable("klassSeminarId") BigInteger klassSeminarId,
                                    Question question) throws Exception {
        Thread.sleep(1000); // simulated delay
        question.setSelected(false);
        question.setScore((float) 0);
        question = seminarService.askQuestion(question);
        KlassSeminarRun klassSeminarRun = new KlassSeminarRun();
        klassSeminarRun.setNewQuestion(question);
        return klassSeminarRun;
    }

    @MessageMapping("/{klassSeminarId}/join")
    @SendTo("/topic/klassSeminar/{klassSeminarId}")
    public KlassSeminarRun joinKlassSeminar(@DestinationVariable("klassSeminarId") BigInteger klassSeminarId) throws Exception {
        Thread.sleep(1000); // simulated delay
        List<Attendance> attendances = seminarService.getAttendancesInKlassSeminar(klassSeminarId);
        KlassSeminarRun klassSeminarRun = new KlassSeminarRun();
        klassSeminarRun.setAttendances(attendances);

        List<Question> questions = seminarService.getQuestionInKlassSeminar(klassSeminarId);
        klassSeminarRun.setQuestions(questions);
        return klassSeminarRun;

    }

    @MessageMapping("/{klassSeminarId}/getQuestion")
    @SendTo("/topic/klassSeminar/{klassSeminarId}")
    public KlassSeminarRun getQuestion(@DestinationVariable("klassSeminarId") BigInteger klassSeminarId) throws Exception {
        Thread.sleep(1000); // simulated delay
        Question question = seminarService.selectQuestionInKlassSeminar(klassSeminarId);
        KlassSeminarRun klassSeminarRun = new KlassSeminarRun();
        klassSeminarRun.setSelectQuestion(question);
        return klassSeminarRun;
    }

    @MessageMapping("/{klassSeminarId}/nextAttendance")
    @SendTo("/topic/klassSeminar/{klassSeminarId}")
    public KlassSeminarRun nextAttendance(@DestinationVariable("klassSeminarId") BigInteger klassSeminarId) throws Exception {
        Attendance attendance = seminarService.nextAttendance(klassSeminarId);
        KlassSeminarRun klassSeminarRun = new KlassSeminarRun();
        klassSeminarRun.setNowAttendance(attendance);
        return klassSeminarRun;
    }

    @MessageMapping("/{klassSeminarId}/stopAttendance")
    @SendTo("/topic/klassSeminar/{klassSeminarId}")
    public KlassSeminarRun stopAttendance(@DestinationVariable("klassSeminarId") BigInteger klassSeminarId) throws Exception {
        seminarService.stopKlassSeminar(klassSeminarId);
        KlassSeminarRun klassSeminarRun = new KlassSeminarRun();
        klassSeminarRun.setMessage("stop");
        return klassSeminarRun;
    }
}
