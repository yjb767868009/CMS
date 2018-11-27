package com.xmu.cms.entity;

import java.util.Date;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
public class Seminar {
    private Integer seminarId;
    private Integer courseId;
    private Integer roundId;
    private Integer maxTeamNum;
    private String topic;
    private String introduction;
    private Date signStartTime;
    private Date signEndTime;
    private Date reportEndTime;
    private String status;
    private boolean visible;
    private boolean signOrder;

    public Seminar(Integer seminarId, Integer courseId, Integer roundId, Integer maxTeamNum, String topic, String introduction, Date signStartTime, Date signEndTime, Date reportEndTime, String status, boolean visible, boolean signOrder) {
        this.seminarId = seminarId;
        this.courseId = courseId;
        this.roundId = roundId;
        this.maxTeamNum = maxTeamNum;
        this.topic = topic;
        this.introduction = introduction;
        this.signStartTime = signStartTime;
        this.signEndTime = signEndTime;
        this.reportEndTime = reportEndTime;
        this.status = status;
        this.visible = visible;
        this.signOrder = signOrder;
    }

    public Integer getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(Integer seminarId) {
        this.seminarId = seminarId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isSignOrder() {
        return signOrder;
    }

    public void setSignOrder(boolean signOrder) {
        this.signOrder = signOrder;
    }
}
