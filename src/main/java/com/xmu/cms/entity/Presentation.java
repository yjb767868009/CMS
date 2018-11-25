package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */
public class Presentation {
    private Integer presentationId;
    private String ppt;
    private String report;
    private Float presentationScore;
    private Float reportScore;
    private Float teamOrder;
    private Integer teamId;
    private Integer seminarId;

    public Presentation(Integer presentationId, String ppt, String report, Float presentationScore, Float reportScore, Float teamOrder, Integer teamId, Integer seminarId) {
        this.presentationId = presentationId;
        this.ppt = ppt;
        this.report = report;
        this.presentationScore = presentationScore;
        this.reportScore = reportScore;
        this.teamOrder = teamOrder;
        this.teamId = teamId;
        this.seminarId = seminarId;
    }

    public Integer getPresentationId() {
        return presentationId;
    }

    public void setPresentationId(Integer presentationId) {
        this.presentationId = presentationId;
    }

    public String getPpt() {
        return ppt;
    }

    public void setPpt(String ppt) {
        this.ppt = ppt;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Float getPresentationScore() {
        return presentationScore;
    }

    public void setPresentationScore(Float presentationScore) {
        this.presentationScore = presentationScore;
    }

    public Float getReportScore() {
        return reportScore;
    }

    public void setReportScore(Float reportScore) {
        this.reportScore = reportScore;
    }

    public Float getTeamOrder() {
        return teamOrder;
    }

    public void setTeamOrder(Float teamOrder) {
        this.teamOrder = teamOrder;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(Integer seminarId) {
        this.seminarId = seminarId;
    }
}
