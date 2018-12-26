package com.xmu.cms.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Question {
    private BigInteger questionId;
    private KlassSeminar klassSeminar;
    private Student student;
    private Team team;
    private Attendance attendance;
    private Boolean selected;
    private Float score;

    public Question(BigInteger questionId, KlassSeminar klassSeminar, Student student, Team team, Attendance attendance, Boolean selected, Float score) {
        this.questionId = questionId;
        this.klassSeminar = klassSeminar;
        this.student = student;
        this.team = team;
        this.attendance = attendance;
        this.selected = selected;
        this.score = score;
    }

    public Question() {
    }

    public BigInteger getQuestionId() {
        return questionId;
    }

    public void setQuestionId(BigInteger questionId) {
        this.questionId = questionId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getName() {
        return team.getTeamName() + "-" + student.getName();
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public KlassSeminar getKlassSeminar() {
        return klassSeminar;
    }

    public void setKlassSeminar(KlassSeminar klassSeminar) {
        this.klassSeminar = klassSeminar;
    }
}
