package com.xmu.cms.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xmu.cms.entity.strategy.*;

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
    private MemberLimitStrategy memberLimitStrategy;
    private List<CourseMemberLimitStrategy> courseMemberLimitStrategies;
    private TeamAndStrategy teamAndStrategy;
    private TeamOrStrategy teamOrStrategy;
    private ConflictCourseStrategy conflictCourseStrategy;

    public Course(BigInteger courseId, Teacher teacher, String courseName, String introduction, Integer presentationWeight, Integer reportWeight, Integer questionWeight, Timestamp teamStartTime, Timestamp teamEndTime, List<Klass> klasses, Course teamMainCourse, Course seminarMainCourse, MemberLimitStrategy memberLimitStrategy, List<CourseMemberLimitStrategy> courseMemberLimitStrategies, TeamAndStrategy teamAndStrategy, TeamOrStrategy teamOrStrategy, ConflictCourseStrategy conflictCourseStrategy) {
        this.courseId = courseId;
        this.teacher = teacher;
        this.courseName = courseName;
        this.introduction = introduction;
        this.presentationWeight = presentationWeight;
        this.reportWeight = reportWeight;
        this.questionWeight = questionWeight;
        this.teamStartTime = teamStartTime;
        this.teamEndTime = teamEndTime;
        this.klasses = klasses;
        this.teamMainCourse = teamMainCourse;
        this.seminarMainCourse = seminarMainCourse;
        this.memberLimitStrategy = memberLimitStrategy;
        this.courseMemberLimitStrategies = courseMemberLimitStrategies;
        this.teamAndStrategy = teamAndStrategy;
        this.teamOrStrategy = teamOrStrategy;
        this.conflictCourseStrategy = conflictCourseStrategy;
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

    public MemberLimitStrategy getMemberLimitStrategy() {
        return memberLimitStrategy;
    }

    public void setMemberLimitStrategy(MemberLimitStrategy memberLimitStrategy) {
        this.memberLimitStrategy = memberLimitStrategy;
    }

    public List<CourseMemberLimitStrategy> getCourseMemberLimitStrategies() {
        return courseMemberLimitStrategies;
    }

    public void setCourseMemberLimitStrategies(List<CourseMemberLimitStrategy> courseMemberLimitStrategies) {
        this.courseMemberLimitStrategies = courseMemberLimitStrategies;
    }

    public TeamAndStrategy getTeamAndStrategy() {
        return teamAndStrategy;
    }

    public void setTeamAndStrategy(TeamAndStrategy teamAndStrategy) {
        this.teamAndStrategy = teamAndStrategy;
    }

    public TeamOrStrategy getTeamOrStrategy() {
        return teamOrStrategy;
    }

    public void setTeamOrStrategy(TeamOrStrategy teamOrStrategy) {
        this.teamOrStrategy = teamOrStrategy;
    }

    public ConflictCourseStrategy getConflictCourseStrategy() {
        return conflictCourseStrategy;
    }

    public void setConflictCourseStrategy(ConflictCourseStrategy conflictCourseStrategy) {
        this.conflictCourseStrategy = conflictCourseStrategy;
    }
}
