package com.xmu.cms.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/12/20.
 * @version 1.0
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ShareSeminar {
    private BigInteger shareSeminarId;
    private Course masterCourse;
    private Course receiveCourse;
    private Teacher receiveTeacher;
    private Boolean status;

    public ShareSeminar(BigInteger shareSeminarId, Course masterCourse, Course receiveCourse, Teacher receiveTeacher, Boolean status) {
        this.shareSeminarId = shareSeminarId;
        this.masterCourse = masterCourse;
        this.receiveCourse = receiveCourse;
        this.receiveTeacher = receiveTeacher;
        this.status = status;
    }

    public ShareSeminar() {
    }

    public BigInteger getShareSeminarId() {
        return shareSeminarId;
    }

    public void setShareSeminarId(BigInteger shareSeminarId) {
        this.shareSeminarId = shareSeminarId;
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
