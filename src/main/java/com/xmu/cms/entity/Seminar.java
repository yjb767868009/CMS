package com.xmu.cms.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Seminar {
    private BigInteger seminarId;
    private Course course;
    private Round round;
    private Integer maxTeamNum;
    private String topic;
    private String introduction;
    private Timestamp signStartTime;
    private Timestamp signEndTime;
    private Integer signOrder;
    private Boolean visible;
    private List<KlassSeminar> klassSeminars;

    public Seminar(BigInteger seminarId, Course course, Round round, Integer maxTeamNum, String topic, String introduction, Timestamp signStartTime, Timestamp signEndTime, Integer signOrder, Boolean visible, List<KlassSeminar> klassSeminars) {
        this.seminarId = seminarId;
        this.course = course;
        this.round = round;
        this.maxTeamNum = maxTeamNum;
        this.topic = topic;
        this.introduction = introduction;
        this.signStartTime = signStartTime;
        this.signEndTime = signEndTime;
        this.signOrder = signOrder;
        this.visible = visible;
        this.klassSeminars = klassSeminars;
    }

    public Seminar(BigInteger seminarId) {
        this.seminarId = seminarId;
    }

    public Seminar() {
    }

    public BigInteger getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(BigInteger seminarId) {
        this.seminarId = seminarId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
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

    public Integer getSignOrder() {
        return signOrder;
    }

    public void setSignOrder(Integer signOrder) {
        this.signOrder = signOrder;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public List<KlassSeminar> getKlassSeminars() {
        return klassSeminars;
    }

    public void setKlassSeminars(List<KlassSeminar> klassSeminars) {
        this.klassSeminars = klassSeminars;
    }
}
