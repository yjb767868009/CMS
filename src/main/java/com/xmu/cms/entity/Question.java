package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */
public class Question {
    private Integer questionId;
    private Integer studentId;
    private Integer teamId;
    private Integer attendanceId;
    private float score;

    public Question(Integer questionId, Integer studentId, Integer teamId, Integer attendanceId, float score) {
        this.questionId = questionId;
        this.studentId = studentId;
        this.teamId = teamId;
        this.attendanceId = attendanceId;
        this.score = score;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer id) {
        this.questionId = id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}
