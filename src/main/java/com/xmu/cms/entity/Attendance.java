package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */
public class Attendance {
    private Integer attendanceId;
    private Team team;
    private Clbum clbum;
    private Seminar seminar;
    private String presentationFile;
    private String reportFile;
    private Float presentationScore;
    private Float reportScore;

    public Attendance(Integer attendanceId, Team team, Clbum clbum, Seminar seminar, String presentationFile, String reportFile, Float presentationScore, Float reportScore) {
        this.attendanceId = attendanceId;
        this.team = team;
        this.clbum = clbum;
        this.seminar = seminar;
        this.presentationFile = presentationFile;
        this.reportFile = reportFile;
        this.presentationScore = presentationScore;
        this.reportScore = reportScore;
    }

    public Attendance() {
    }

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Clbum getClbum() {
        return clbum;
    }

    public void setClbum(Clbum clbum) {
        this.clbum = clbum;
    }

    public Seminar getSeminar() {
        return seminar;
    }

    public void setSeminar(Seminar seminar) {
        this.seminar = seminar;
    }

    public String getPresentationFile() {
        return presentationFile;
    }

    public void setPresentationFile(String presentationFile) {
        this.presentationFile = presentationFile;
    }

    public String getReportFile() {
        return reportFile;
    }

    public void setReportFile(String reportFile) {
        this.reportFile = reportFile;
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
