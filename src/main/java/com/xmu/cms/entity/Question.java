package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */
public class Question {
    private Integer questionId;
    private Integer studentId;
    private Integer teamId;
    private Integer presentationId;
    private boolean selected;
    private float score;

    public Question(Integer questionId, Integer studentId, Integer teamId, Integer presentationId, boolean selected, float score) {
        this.questionId = questionId;
        this.studentId = studentId;
        this.teamId = teamId;
        this.presentationId = presentationId;
        this.selected = selected;
        this.score = score;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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

    public Integer getPresentationId() {
        return presentationId;
    }

    public void setPresentationId(Integer presentationId) {
        this.presentationId = presentationId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}
