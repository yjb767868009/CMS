package com.xmu.cms.support;

import com.xmu.cms.entity.Attendance;
import com.xmu.cms.entity.Question;

import java.util.List;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
public class KlassSeminarRun {
    private List<Question> questions;
    private List<Attendance> attendances;
    private Attendance nowAttendance;
    private Question newQuestion;
    private Question selectQuestion;
    private String message;

    public KlassSeminarRun(List<Question> questions, List<Attendance> attendances, Attendance nowAttendance, Question newQuestion, Question selectQuestion, String message) {
        this.questions = questions;
        this.attendances = attendances;
        this.nowAttendance = nowAttendance;
        this.newQuestion = newQuestion;
        this.selectQuestion = selectQuestion;
        this.message = message;
    }

    public KlassSeminarRun() {
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
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
