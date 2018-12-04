package com.xmu.cms.entity;

import java.sql.Timestamp;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public class Seminar {
    private Integer seminarId;
    private Integer roundId;
    private Integer maxTeamNum;
    private String topic;
    private String introduction;
    private Timestamp signStartTime;
    private Timestamp signEndTime;
    private Boolean signOrder;
    private Boolean visible;

    public Seminar(Integer seminarId, Integer roundId, Integer maxTeamNum, String topic, String introduction, Timestamp signStartTime, Timestamp signEndTime, Boolean signOrder, Boolean visible) {
        this.seminarId = seminarId;
        this.roundId = roundId;
        this.maxTeamNum = maxTeamNum;
        this.topic = topic;
        this.introduction = introduction;
        this.signStartTime = signStartTime;
        this.signEndTime = signEndTime;
        this.signOrder = signOrder;
        this.visible = visible;
    }

    public Seminar() {
    }

    public Integer getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(Integer seminarId) {
        this.seminarId = seminarId;
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

    public Boolean getSignOrder() {
        return signOrder;
    }

    public void setSignOrder(Boolean signOrder) {
        this.signOrder = signOrder;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
