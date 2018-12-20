package com.xmu.cms.entity;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */
public class Question {
    private BigInteger questionId;
    private Student student;
    private Team team;
    private Attendance attendance;
    private float score;

    public Question(BigInteger questionId, Student student, Team team, Attendance attendance, float score) {
        this.questionId = questionId;
        this.student = student;
        this.team = team;
        this.attendance = attendance;
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

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
