package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
public class Round {
    private Integer roundId;
    private Integer courseId;
    private Integer roundNum;
    private Integer presentationScoreType;
    private Integer reportScoreType;
    private Integer questionScoreType;

    public Round(Integer roundId, Integer courseId, Integer roundNum, Integer presentationScoreType, Integer reportScoreType, Integer questionScoreType) {
        this.roundId = roundId;
        this.courseId = courseId;
        this.roundNum = roundNum;
        this.presentationScoreType = presentationScoreType;
        this.reportScoreType = reportScoreType;
        this.questionScoreType = questionScoreType;
    }

    public Integer getRoundId() {
        return roundId;
    }

    public void setRoundId(Integer id) {
        this.roundId = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getRoundNum() {
        return roundNum;
    }

    public void setRoundNum(Integer roundNum) {
        this.roundNum = roundNum;
    }

    public Integer getAttendanceScoreType() {
        return presentationScoreType;
    }

    public void setAttendanceScoreType(Integer presentationScoreType) {
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
