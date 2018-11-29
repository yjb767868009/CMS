package com.xmu.cms.entity;

import java.util.Date;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
public class SeminarInfo {
    private Integer seminarId;
    private Integer courseId;
    private String topic;
    private String introduction;
    private boolean visible;

    public SeminarInfo(Integer seminarId, Integer courseId, String topic, String introduction, boolean visible) {
        this.seminarId = seminarId;
        this.courseId = courseId;
        this.topic = topic;
        this.introduction = introduction;
        this.visible = visible;
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
