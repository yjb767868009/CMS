package com.xmu.cms.entity;

import java.util.Date;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
public class Course {
    private Integer courseId;
    private Integer teacherId;
    private String name;
    private String introduction;
    private Float presentationWeight;
    private Float reportWeight;
    private Float questionWeight;
    private Date teamStartTime;
    private Date teamEndTime;

    public Course(Integer courseId, Integer teacherId, String name, String introduction, Float presentationWeight, Float reportWeight, Float questionWeight, Date teamStartTime, Date teamEndTime) {
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.name = name;
        this.introduction = introduction;
        this.presentationWeight = presentationWeight;
        this.reportWeight = reportWeight;
        this.questionWeight = questionWeight;
        this.teamStartTime = teamStartTime;
        this.teamEndTime = teamEndTime;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Float getPresentationWeight() {
        return presentationWeight;
    }

    public void setPresentationWeight(Float presentationWeight) {
        this.presentationWeight = presentationWeight;
    }

    public Float getReportWeight() {
        return reportWeight;
    }

    public void setReportWeight(Float reportWeight) {
        this.reportWeight = reportWeight;
    }

    public Float getQuestionWeight() {
        return questionWeight;
    }

    public void setQuestionWeight(Float questionWeight) {
        this.questionWeight = questionWeight;
    }

    public Date getTeamStartTime() {
        return teamStartTime;
    }

    public void setTeamStartTime(Date teamStartTime) {
        this.teamStartTime = teamStartTime;
    }

    public Date getTeamEndTime() {
        return teamEndTime;
    }

    public void setTeamEndTime(Date teamEndTime) {
        this.teamEndTime = teamEndTime;
    }
}
