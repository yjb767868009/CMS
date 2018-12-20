package com.xmu.cms.entity;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */
public class Attendance {
    private BigInteger attendanceId;
    private Team team;
    private Klass klass;
    private Seminar seminar;
    private String presentationFile;
    private String reportFile;

    public Attendance(BigInteger attendanceId, Team team, Klass klass, Seminar seminar, String presentationFile, String reportFile) {
        this.attendanceId = attendanceId;
        this.team = team;
        this.klass = klass;
        this.seminar = seminar;
        this.presentationFile = presentationFile;
        this.reportFile = reportFile;
    }

    public Attendance() {
    }

    public BigInteger getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(BigInteger attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
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

}
