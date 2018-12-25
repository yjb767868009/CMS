package com.xmu.cms.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Attendance {
    private BigInteger attendanceId;
    private Team team;
    private KlassSeminar klassSeminar;
    private Integer teamOrder;
    private String presentationFile;
    private String reportFile;
    private Boolean present;

    public Attendance(BigInteger attendanceId, Team team, KlassSeminar klassSeminar, Integer teamOrder, String presentationFile, String reportFile, Boolean present) {
        this.attendanceId = attendanceId;
        this.team = team;
        this.klassSeminar = klassSeminar;
        this.teamOrder = teamOrder;
        this.presentationFile = presentationFile;
        this.reportFile = reportFile;
        this.present = present;
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

    public Integer getTeamOrder() {
        return teamOrder;
    }

    public void setTeamOrder(Integer teamOrder) {
        this.teamOrder = teamOrder;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    public KlassSeminar getKlassSeminar() {
        return klassSeminar;
    }

    public void setKlassSeminar(KlassSeminar klassSeminar) {
        this.klassSeminar = klassSeminar;
    }
}
