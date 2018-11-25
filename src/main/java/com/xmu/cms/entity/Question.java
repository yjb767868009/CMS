package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */
public class Question {
    private Integer questionId;
    private boolean selected;
    private double score;
    private Integer studentId;
    private Integer presentationId;

    public Question(Integer questionId, boolean selected, double score, Integer studentId, Integer presentationId) {
        this.questionId = questionId;
        this.selected = selected;
        this.score = score;
        this.studentId = studentId;
        this.presentationId = presentationId;
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
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
}
