package com.xmu.cms.support;

import com.xmu.cms.entity.Attendance;
import com.xmu.cms.entity.Question;

import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
public class KlassSeminarRun {
    private List<Question> questions;
    private Map<Integer, Attendance> attendances;
    private Question selectQuestion;

    public KlassSeminarRun(List<Question> questions, Map<Integer, Attendance> attendances, Question selectQuestion) {
        this.questions = questions;
        this.attendances = attendances;
        this.selectQuestion = selectQuestion;
    }

    public KlassSeminarRun() {
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Map<Integer, Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(Map<Integer, Attendance> attendances) {
        this.attendances = attendances;
    }

    public Question getSelectQuestion() {
        return selectQuestion;
    }

    public void setSelectQuestion(Question selectQuestion) {
        this.selectQuestion = selectQuestion;
    }
}
