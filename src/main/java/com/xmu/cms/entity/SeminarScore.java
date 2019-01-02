package com.xmu.cms.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeminarScore {
    private Attendance attendance;
    private Team team;
    private KlassSeminar klassSeminar;
    private Seminar seminar;
    private Float presentationScore;
    private Float questionScore;
    private Float reportScore;
    private Float totalScore;

    public SeminarScore(Attendance attendance, Team team, KlassSeminar klassSeminar, Seminar seminar, Float presentationScore, Float questionScore, Float reportScore, Float totalScore) {
        this.attendance = attendance;
        this.team = team;
        this.klassSeminar = klassSeminar;
        this.seminar = seminar;
        this.presentationScore = presentationScore;
        this.questionScore = questionScore;
        this.reportScore = reportScore;
        this.totalScore = totalScore;
    }

    public SeminarScore() {
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public KlassSeminar getKlassSeminar() {
        return klassSeminar;
    }

    public void setKlassSeminar(KlassSeminar klassSeminar) {
        this.klassSeminar = klassSeminar;
    }

    public Float getPresentationScore() {
        return presentationScore;
    }

    public void setPresentationScore(Float presentationScore) {
        this.presentationScore = presentationScore;
    }

    public Float getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(Float questionScore) {
        this.questionScore = questionScore;
    }

    public Float getReportScore() {
        return reportScore;
    }

    public void setReportScore(Float reportScore) {
        this.reportScore = reportScore;
    }

    public Float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Float totalScore) {
        this.totalScore = totalScore;
    }

    public Seminar getSeminar() {
        return seminar;
    }

    public void setSeminar(Seminar seminar) {
        this.seminar = seminar;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }
}
