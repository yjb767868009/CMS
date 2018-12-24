package com.xmu.cms.controller;

import com.xmu.cms.entity.Attendance;
import com.xmu.cms.entity.KlassSeminar;
import com.xmu.cms.entity.Question;
import com.xmu.cms.service.SeminarService;
import com.xmu.cms.support.HelloMessage;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private KlassSeminarRun getAttendanceAndQuestion(KlassSeminarRun klassSeminarRun) {
        KlassSeminar klassSeminar = seminarService.getKlassSeminarByKlassAndSeminar(new BigInteger("1"), new BigInteger("1"));
        List<Attendance> attendances = seminarService.getAttendancesInKlassSeminar(klassSeminar.getKlassSeminarId());
        Map<Integer, Attendance> reAttendances = new HashMap<Integer, Attendance>();
        for (Attendance attendance : attendances) {
            reAttendances.put(attendance.getTeamOrder(), attendance);
        }

        List<Question> questions = seminarService.getQuestionInKlassSeminar(klassSeminar.getKlassSeminarId());
        klassSeminarRun.setQuestions(questions);
        klassSeminarRun.setAttendances(reAttendances);
        return klassSeminarRun;
    }

    @MessageMapping("/{klassSeminarId}/hello")
    @SendTo("/topic/klassSeminar/{klassSeminarId}")
    public KlassSeminarRun greeting(@DestinationVariable("klassSeminarId") BigInteger klassSeminarId
            , HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return getAttendanceAndQuestion(new KlassSeminarRun());
    }

    @MessageMapping("/{klassSeminarId}/question")
    @SendTo("/topic/klassSeminar/{klassSeminarId}")
    public KlassSeminarRun question(@DestinationVariable("klassSeminarId") BigInteger klassSeminarId,
                                    Question question) throws Exception {
        Thread.sleep(1000); // simulated delay
        question.setSelected(false);
        question.setScore((float) 0);
        seminarService.askQuestion(question);
        return getAttendanceAndQuestion(new KlassSeminarRun());
    }

    @MessageMapping("/{klassSeminarId}/scorequestion")
    @SendTo("/topic/klassSeminar/{klassSeminarId}")
    public KlassSeminarRun scoreQuestion(@DestinationVariable("klassSeminarId") BigInteger klassSeminarId,
                                         Question question) throws Exception {
        Thread.sleep(1000); // simulated delay
        seminarService.scoreQuestion(question);
        return getAttendanceAndQuestion(new KlassSeminarRun());
    }

    @MessageMapping("/{klassSeminarId}/getQuestion")
    @SendTo("/topic/klassSeminar/{klassSeminarId}")
    public KlassSeminarRun getQuestion(@DestinationVariable("klassSeminarId") BigInteger klassSeminarId) throws Exception {
        Thread.sleep(1000); // simulated delay
        Question question = seminarService.selectQuestionInKlassSeminar(klassSeminarId);
        KlassSeminarRun klassSeminarRun = new KlassSeminarRun();
        klassSeminarRun.setSelectQuestion(question);
        return getAttendanceAndQuestion(klassSeminarRun);
    }

    @MessageMapping("/{klassSeminarId}/nextAttendance")
    @SendTo("/topic/klassSeminar/{klassSeminarId}")
    public KlassSeminarRun nextAttendance(@DestinationVariable("klassSeminarId") BigInteger klassSeminarId) throws Exception {
        seminarService.nextAttendance(klassSeminarId);
        return getAttendanceAndQuestion(new KlassSeminarRun());
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
