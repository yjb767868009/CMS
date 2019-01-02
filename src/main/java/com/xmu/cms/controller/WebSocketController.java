package com.xmu.cms.controller;

import com.xmu.cms.entity.Attendance;
import com.xmu.cms.entity.KlassSeminar;
import com.xmu.cms.entity.Question;
import com.xmu.cms.service.SeminarService;
import com.xmu.cms.support.KlassSeminarRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@RestController
public class WebSocketController {

    @Autowired
    private SeminarService seminarService;

    @MessageMapping("/{klassSeminarId}/question")
    @SendTo("/topic/klassSeminar/{klassSeminarId}")
    public KlassSeminarRun question(@DestinationVariable("klassSeminarId") BigInteger klassSeminarId,
                                    @RequestBody Question question) throws Exception {
        Thread.sleep(1000);
        question.setSelected(false);
        question.setScore((float) 0);
        question.setKlassSeminar(new KlassSeminar(klassSeminarId));
        KlassSeminarRun klassSeminarRun = new KlassSeminarRun();
        try {
            question = seminarService.askQuestion(question);
            klassSeminarRun.setNewQuestion(question);
        } catch (Exception e) {
            klassSeminarRun.setMessage(e.getMessage());
        }
        return klassSeminarRun;
    }

    @MessageMapping("/{klassSeminarId}/getQuestion")
    @SendTo("/topic/klassSeminar/{klassSeminarId}")
    public KlassSeminarRun getQuestion(@DestinationVariable("klassSeminarId") BigInteger klassSeminarId) throws Exception {
        Thread.sleep(1000);
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
        if (attendance == null) {
            klassSeminarRun.setMessage("无未展示的组");
        } else {
            klassSeminarRun.setNowAttendance(attendance);
        }
        return klassSeminarRun;
    }

    @MessageMapping("/{klassSeminarId}/endAttendance")
    @SendTo("/topic/klassSeminar/{klassSeminarId}")
    public KlassSeminarRun endKlassSeminar(@DestinationVariable("klassSeminarId") BigInteger klassSeminarId) throws Exception {
        seminarService.endKlassSeminar(klassSeminarId);
        KlassSeminarRun klassSeminarRun = new KlassSeminarRun();
        klassSeminarRun.setMessage("end");
        return klassSeminarRun;
    }
}
