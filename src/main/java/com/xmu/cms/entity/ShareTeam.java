package com.xmu.cms.entity;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
public class ShareTeam {
    private BigInteger shareTeamId;
    private Course masterCourse;
    private Course receiveCourse;
    private Teacher receiveTeacher;
    private Integer status;

    public ShareTeam(BigInteger shareTeamId, Course masterCourse, Course receiveCourse, Teacher receiveTeacher, Integer status) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
