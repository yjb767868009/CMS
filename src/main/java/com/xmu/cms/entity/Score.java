package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public class Score {
    private String name;
    private Float presentationScore;
    private Float questionScore;
    private Float reportScore;
    private Float totalScore;

    public Score(String name, Float presentationScore, Float questionScore, Float reportScore, Float totalScore) {
        this.name = name;
        this.presentationScore = presentationScore;
        this.questionScore = questionScore;
        this.reportScore = reportScore;
        this.totalScore = totalScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAttendanceScore() {
        return presentationScore;
    }

    public void setAttendanceScore(Float presentationScore) {
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
}
