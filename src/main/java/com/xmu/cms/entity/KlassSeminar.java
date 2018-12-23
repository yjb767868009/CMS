package com.xmu.cms.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author JuboYu on 2018/12/20.
 * @version 1.0
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class KlassSeminar {
    private BigInteger klassSeminarId;
    private Klass klass;
    private Seminar seminar;
    private Timestamp reportDDL;
    private Integer status;
    private Attendance selectAttendance;

    public KlassSeminar(BigInteger klassSeminarId, Klass klass, Seminar seminar, Timestamp reportDDL, Integer status, Attendance selectAttendance) {
        this.klassSeminarId = klassSeminarId;
        this.klass = klass;
        this.seminar = seminar;
        this.reportDDL = reportDDL;
        this.status = status;
        this.selectAttendance = selectAttendance;
    }

    public KlassSeminar() {
    }

    public BigInteger getKlassSeminarId() {
        return klassSeminarId;
    }

    public void setKlassSeminarId(BigInteger klassSeminarId) {
        this.klassSeminarId = klassSeminarId;
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

    public Timestamp getReportDDL() {
        return reportDDL;
    }

    public void setReportDDL(Timestamp reportDDL) {
        this.reportDDL = reportDDL;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Attendance getSelectAttendance() {
        return selectAttendance;
    }

    public void setSelectAttendance(Attendance selectAttendance) {
        this.selectAttendance = selectAttendance;
    }
}
