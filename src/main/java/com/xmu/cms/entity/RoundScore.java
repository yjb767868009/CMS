package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public class RoundScore {
    private Team team;
    private Round round;
    private Float presentationScore;
    private Float questionScore;
    private Float reportScore;
    private Float totalScore;

    public RoundScore(Team team, Round round, Float presentationScore, Float questionScore, Float reportScore, Float totalScore) {
        this.team = team;
        this.round = round;
        this.presentationScore = presentationScore;
        this.questionScore = questionScore;
        this.reportScore = reportScore;
        this.totalScore = totalScore;
    }

    public RoundScore() {
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }
}
