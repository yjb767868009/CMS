package com.xmu.cms.entity;

import java.util.Date;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public class SeminarTurningClass {
    private Integer seminarId;
    private Integer turningClassId;
    private Integer roundId;
    private Integer maxTeamNum;
    private Date signStartTime;
    private Date signEndTime;
    private Date reportEndTime;
    /**
     * status 0表示未开始，1表示进行中，2表示暂停，4表示结束
     */
    private Integer status;
    private Boolean signOrder;

    public SeminarTurningClass(Integer seminarId, Integer turningClassId, Integer roundId, Integer maxTeamNum, Date signStartTime, Date signEndTime, Date reportEndTime, Integer status, Boolean signOrder) {
        this.seminarId = seminarId;
        this.turningClassId = turningClassId;
        this.roundId = roundId;
        this.maxTeamNum = maxTeamNum;
        this.signStartTime = signStartTime;
        this.signEndTime = signEndTime;
        this.reportEndTime = reportEndTime;
        this.status = status;
        this.signOrder = signOrder;
    }

    public Integer getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(Integer seminarId) {
        this.seminarId = seminarId;
    }

    public Integer getTurningClassId() {
        return turningClassId;
    }

    public void setTurningClassId(Integer turningClassId) {
        this.turningClassId = turningClassId;
    }

    public Integer getRoundId() {
        return roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    public Integer getMaxTeamNum() {
        return maxTeamNum;
    }

    public void setMaxTeamNum(Integer maxTeamNum) {
        this.maxTeamNum = maxTeamNum;
    }

    public Date getSignStartTime() {
        return signStartTime;
    }

    public void setSignStartTime(Date signStartTime) {
        this.signStartTime = signStartTime;
    }

    public Date getSignEndTime() {
        return signEndTime;
    }

    public void setSignEndTime(Date signEndTime) {
        this.signEndTime = signEndTime;
    }

    public Date getReportEndTime() {
        return reportEndTime;
    }

    public void setReportEndTime(Date reportEndTime) {
        this.reportEndTime = reportEndTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean isSignOrder() {
        return signOrder;
    }

    public void setSignOrder(Boolean signOrder) {
        this.signOrder = signOrder;
    }
}
