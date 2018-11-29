package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */
public class Presentation {
    private Integer presentationId;
    private Integer teamId;
    private Integer seminarId;
    private Integer teamOrder;
    private String ppt;
    private String report;
    private Float presentationScore;
    private Float reportScore;

    public Presentation(Integer presentationId, Integer teamId, Integer seminarId, Integer teamOrder, String ppt, String report, Float presentationScore, Float reportScore) {
        this.presentationId = presentationId;
        this.teamId = teamId;
        this.seminarId = seminarId;
        this.teamOrder = teamOrder;
        this.ppt = ppt;
        this.report = report;
        this.presentationScore = presentationScore;
        this.reportScore = reportScore;
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

    public Integer getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(Integer seminarId) {
        this.seminarId = seminarId;
    }

    public Integer getTeamOrder() {
        return teamOrder;
    }

    public void setTeamOrder(Integer teamOrder) {
        this.teamOrder = teamOrder;
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
}
