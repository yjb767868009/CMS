package com.xmu.cms.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xmu.cms.entity.strategy.Strategy;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Course {
    private BigInteger courseId;
    private Teacher teacher;
    private String courseName;
    private String introduction;
    private Integer presentationWeight;
    private Integer reportWeight;
    private Integer questionWeight;
    private Timestamp teamStartTime;
    private Timestamp teamEndTime;
    private List<Klass> klasses;
    private Course teamMainCourse;
    private Course seminarMainCourse;
    private List<Strategy> strategies;

    public Course(BigInteger courseId, Teacher teacher, String coursename, String introduction, Integer presentationWeight, Integer reportWeight, Integer questionWeight, Timestamp teamStartTime, Timestamp teamEndTime, List<Klass> klasses, Course teamMainCourse, Course seminarMainCourse, List<Strategy> strategies) {
        this.courseId = courseId;
        this.teacher = teacher;
        this.courseName = coursename;
        this.introduction = introduction;
        this.presentationWeight = presentationWeight;
        this.reportWeight = reportWeight;
        this.questionWeight = questionWeight;
        this.teamStartTime = teamStartTime;
        this.teamEndTime = teamEndTime;
        this.klasses = klasses;
        this.teamMainCourse = teamMainCourse;
        this.seminarMainCourse = seminarMainCourse;
        this.strategies = strategies;
    }

    public Course(BigInteger courseId) {
        this.courseId = courseId;
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
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getPresentationWeight() {
        return presentationWeight;
    }

    public void setPresentationWeight(Integer presentationWeight) {
        this.presentationWeight = presentationWeight;
    }

    public Integer getReportWeight() {
        return reportWeight;
    }

    public void setReportWeight(Integer reportWeight) {
        this.reportWeight = reportWeight;
    }

    public Integer getQuestionWeight() {
        return questionWeight;
    }

    public void setQuestionWeight(Integer questionWeight) {
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

    public Course getTeamMainCourse() {
        return teamMainCourse;
    }

    public void setTeamMainCourse(Course teamMainCourse) {
        this.teamMainCourse = teamMainCourse;
    }

    public Course getSeminarMainCourse() {
        return seminarMainCourse;
    }

    public void setSeminarMainCourse(Course seminarMainCourse) {
        this.seminarMainCourse = seminarMainCourse;
    }

    public List<Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<Strategy> strategies) {
        this.strategies = strategies;
    }
}
