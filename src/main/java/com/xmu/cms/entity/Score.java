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

    public Score(String name, Float presentationScore, Float questionScore, Float reportScore) {
        this.name = name;
        this.presentationScore = presentationScore;
        this.questionScore = questionScore;
        this.reportScore = reportScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
