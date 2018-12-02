package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */
public class Attendance {
    private Integer attendanceId;
    private Integer teamId;
    private Integer clbumSeminarId;
    private Integer teamOrder;
    private String presentationFile;
    private String reportFile;
    private Float presentationScore;
    private Float reportScore;

    public Attendance(Integer attendanceId, Integer teamId, Integer clbumSeminarId, Integer teamOrder, String presentationFile, String reportFile, Float presentationScore, Float reportScore) {
        this.attendanceId = attendanceId;
        this.teamId = teamId;
        this.clbumSeminarId = clbumSeminarId;
        this.teamOrder = teamOrder;
        this.presentationFile = presentationFile;
        this.reportFile = reportFile;
        this.presentationScore = presentationScore;
        this.reportScore = reportScore;
    }

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getClbumSeminarId() {
        return clbumSeminarId;
    }

    public void setClbumSeminarId(Integer clbumSeminarId) {
        this.clbumSeminarId = clbumSeminarId;
    }

    public Integer getTeamOrder() {
        return teamOrder;
    }

    public void setTeamOrder(Integer teamOrder) {
        this.teamOrder = teamOrder;
    }

    public String getPreFile() {
        return presentationFile;
    }

    public void setPreFile(String presentationFile) {
        this.presentationFile = presentationFile;
    }

    public String getReportFile() {
        return reportFile;
    }

    public void setReportFile(String reportFile) {
        this.reportFile = reportFile;
    }

    public Float getAttendanceScore() {
        return presentationScore;
    }

    public void setAttendanceScore(Float presentationScore) {
        this.presentationScore = presentationScore;
    }

    public Float getReportScore() {
        return reportScore;
    }

    public void setReportScore(Float reportScore) {
        this.reportScore = reportScore;
    }
}
