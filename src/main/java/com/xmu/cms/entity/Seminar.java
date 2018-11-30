package com.xmu.cms.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public class Seminar {
    private Integer seminarId;
    private Integer turningClassId;
    private Integer roundId;
    private Integer maxTeamNum;
    private Timestamp signStartTime;
    private Timestamp signEndTime;
    private Timestamp reportEndTime;
    /**
     * status 0表示未开始，1表示进行中，2表示暂停，3表示结束
     */
    private Integer status;
    /**
     * presentationNo
     * 表示第几个展示的组
     */
    private Integer presentationNo;
    private Boolean signOrder;

    public Seminar(Integer seminarId, Integer turningClassId, Integer roundId, Integer maxTeamNum, Timestamp signStartTime, Timestamp signEndTime, Timestamp reportEndTime, Integer status, Integer presentationNo, Boolean signOrder) {
        this.seminarId = seminarId;
        this.turningClassId = turningClassId;
        this.roundId = roundId;
        this.maxTeamNum = maxTeamNum;
        this.signStartTime = signStartTime;
        this.signEndTime = signEndTime;
        this.reportEndTime = reportEndTime;
        this.status = status;
        this.presentationNo = presentationNo;
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

    public Timestamp getSignStartTime() {
        return signStartTime;
    }

    public void setSignStartTime(Timestamp signStartTime) {
        this.signStartTime = signStartTime;
    }

    public Timestamp getSignEndTime() {
        return signEndTime;
    }

    public void setSignEndTime(Timestamp signEndTime) {
        this.signEndTime = signEndTime;
    }

    public Timestamp getReportEndTime() {
        return reportEndTime;
    }

    public void setReportEndTime(Timestamp reportEndTime) {
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

    public Integer getPresentationNo() {
        return presentationNo;
    }

    public void setPresentationNo(Integer presentationNo) {
        this.presentationNo = presentationNo;
    }
}
