package com.xmu.cms.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ShareTeam {
    private BigInteger shareTeamId;
    private Course masterCourse;
    private Course receiveCourse;
    private Teacher receiveTeacher;
    private Boolean status;

    public ShareTeam(BigInteger shareTeamId, Course masterCourse, Course receiveCourse, Teacher receiveTeacher, Boolean status) {
        this.shareTeamId = shareTeamId;
        this.masterCourse = masterCourse;
        this.receiveCourse = receiveCourse;
        this.receiveTeacher = receiveTeacher;
        this.status = status;
    }

    public ShareTeam() {
    }

    public BigInteger getShareTeamId() {
        return shareTeamId;
    }

    public void setShareTeamId(BigInteger shareTeamId) {
        this.shareTeamId = shareTeamId;
    }

    public Course getMasterCourse() {
        return masterCourse;
    }

    public void setMasterCourse(Course masterCourse) {
        this.masterCourse = masterCourse;
    }

    public Course getReceiveCourse() {
        return receiveCourse;
    }

    public void setReceiveCourse(Course receiveCourse) {
        this.receiveCourse = receiveCourse;
    }

    public Teacher getReceiveTeacher() {
        return receiveTeacher;
    }

    public void setReceiveTeacher(Teacher receiveTeacher) {
        this.receiveTeacher = receiveTeacher;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
