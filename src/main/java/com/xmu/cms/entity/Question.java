package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/25.ab
 * @version 1.0
 */
public class Question {
    private Long questionId;
    private boolean selected;
    private double score;
    private Long studentId;
    private Long presentationId;

    public Question(Long questionId, boolean selected, double score, Long studentId, Long presentationId) {
        this.questionId = questionId;
        this.selected = selected;
        this.score = score;
        this.studentId = studentId;
        this.presentationId = presentationId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getPresentationId() {
        return presentationId;
    }

    public void setPresentationId(Long presentationId) {
        this.presentationId = presentationId;
    }
}
