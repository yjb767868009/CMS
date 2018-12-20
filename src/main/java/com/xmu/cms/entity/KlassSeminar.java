package com.xmu.cms.entity;

import java.sql.Timestamp;

/**
 * @author JuboYu on 2018/12/20.
 * @version 1.0
 */
public class KlassSeminar {
    private Integer klassSeminarId;
    private Klass klass;
    private Seminar seminar;
    private Timestamp reportDDL;
    private Integer status;

    public KlassSeminar(Integer klassSeminarId, Klass klass, Seminar seminar, Timestamp reportDDL, Integer status) {
        this.klassSeminarId = klassSeminarId;
        this.klass = klass;
        this.seminar = seminar;
        this.reportDDL = reportDDL;
        this.status = status;
    }

    public KlassSeminar() {
    }

    public Integer getKlassSeminarId() {
        return klassSeminarId;
    }

    public void setKlassSeminarId(Integer klassSeminarId) {
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
}
