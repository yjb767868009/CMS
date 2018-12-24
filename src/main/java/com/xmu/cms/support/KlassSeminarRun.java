package com.xmu.cms.support;

import com.xmu.cms.entity.Attendance;
import com.xmu.cms.entity.Question;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
public class KlassSeminarRun {
    private Attendance nowAttendance;
    private Question newQuestion;
    private Question selectQuestion;
    private String message;

    public KlassSeminarRun(Attendance nowAttendance, Question newQuestion, Question selectQuestion, String message) {
        this.nowAttendance = nowAttendance;
        this.newQuestion = newQuestion;
        this.selectQuestion = selectQuestion;
        this.message = message;
    }

    public KlassSeminarRun() {
    }

    public Question getSelectQuestion() {
        return selectQuestion;
    }

    public void setSelectQuestion(Question selectQuestion) {
        this.selectQuestion = selectQuestion;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Attendance getNowAttendance() {
        return nowAttendance;
    }

    public void setNowAttendance(Attendance nowAttendance) {
        this.nowAttendance = nowAttendance;
    }

    public Question getNewQuestion() {
        return newQuestion;
    }

    public void setNewQuestion(Question newQuestion) {
        this.newQuestion = newQuestion;
    }

}
