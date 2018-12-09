package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public class Score {
    private Integer scoreId;
    private Team team;
    private Seminar seminar;
    private Float presentationScore;
    private Float questionScore;
    private Float reportScore;
    private Float totalScore;

    public Score(Integer scoreId, Team team, Seminar seminar, Float presentationScore, Float questionScore, Float reportScore, Float totalScore) {
        this.scoreId = scoreId;
        this.team = team;
        this.seminar = seminar;
        this.presentationScore = presentationScore;
        this.questionScore = questionScore;
        this.reportScore = reportScore;
        this.totalScore = totalScore;
    }

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Seminar getSeminar() {
        return seminar;
    }

    public void setSeminar(Seminar seminar) {
        this.seminar = seminar;
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

    public Float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Float totalScore) {
        this.totalScore = totalScore;
    }
}
