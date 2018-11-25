package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */
public class Presentation {
    private Long presentationId;
    private String ppt;
    private String report;
    private Float presentationScore;
    private Float reportScore;
    private Float teamOrder;
    private Long teamId;
    private Long seminarId;

    public Presentation(Long presentationId, String ppt, String report, Float presentationScore, Float reportScore, Float teamOrder, Long teamId, Long seminarId) {
        this.presentationId = presentationId;
        this.ppt = ppt;
        this.report = report;
        this.presentationScore = presentationScore;
        this.reportScore = reportScore;
        this.teamOrder = teamOrder;
        this.teamId = teamId;
        this.seminarId = seminarId;
    }

    public Long getPresentationId() {
        return presentationId;
    }

    public void setPresentationId(Long presentationId) {
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

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(Long seminarId) {
        this.seminarId = seminarId;
    }
}
