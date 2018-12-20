package com.xmu.cms.entity;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
public class Course {
    private BigInteger courseId;
    private Teacher teacher;
    private String CourseName;
    private String introduction;
    private Float presentationWeight;
    private Float reportWeight;
    private Float questionWeight;
    private Timestamp teamStartTime;
    private Timestamp teamEndTime;
    private List<Klass> klasses;

    public Course(BigInteger courseId, Teacher teacher, String CourseName, String introduction, Float presentationWeight, Float reportWeight, Float questionWeight, Timestamp teamStartTime, Timestamp teamEndTime, List<Klass> klasses) {
        this.courseId = courseId;
        this.teacher = teacher;
        this.CourseName = CourseName;
        this.introduction = introduction;
        this.presentationWeight = presentationWeight;
        this.reportWeight = reportWeight;
        this.questionWeight = questionWeight;
        this.teamStartTime = teamStartTime;
        this.teamEndTime = teamEndTime;
        this.klasses = klasses;
    }

    public Course() {
    }

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger id) {
        this.courseId = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        this.CourseName = courseName;
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

    public Timestamp getTeamStartTime() {
        return teamStartTime;
    }

    public void setTeamStartTime(Timestamp teamStartTime) {
        this.teamStartTime = teamStartTime;
    }

    public Timestamp getTeamEndTime() {
        return teamEndTime;
    }

    public void setTeamEndTime(Timestamp teamEndTime) {
        this.teamEndTime = teamEndTime;
    }

    public List<Klass> getKlasses() {
        return klasses;
    }

    public void setKlasses(List<Klass> klasses) {
        this.klasses = klasses;
    }
}
