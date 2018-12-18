package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
public class Round {
    private Integer roundId;
    private Course course;
    private Integer presentationScoreType;
    private Integer reportScoreType;
    private Integer questionScoreType;

    public Round(Integer roundId, Course course, Round round, Integer presentationScoreType, Integer reportScoreType, Integer questionScoreType) {
        this.roundId = roundId;
        this.course = course;
        this.presentationScoreType = presentationScoreType;
        this.reportScoreType = reportScoreType;
        this.questionScoreType = questionScoreType;
    }

    public Round() {
    }

    public Integer getRoundId() {
        return roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getPresentationScoreType() {
        return presentationScoreType;
    }

    public void setPresentationScoreType(Integer presentationScoreType) {
        this.presentationScoreType = presentationScoreType;
    }

    public Integer getReportScoreType() {
        return reportScoreType;
    }

    public void setReportScoreType(Integer reportScoreType) {
        this.reportScoreType = reportScoreType;
    }

    public Integer getQuestionScoreType() {
        return questionScoreType;
    }

    public void setQuestionScoreType(Integer questionScoreType) {
        this.questionScoreType = questionScoreType;
    }
}
