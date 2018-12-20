package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
public class ShareTeam {
    private Integer shareId;
    private Course masterCourse;
    private Course receiveCourse;
    private Teacher receiveTeacher;
    private Integer status;

    public ShareTeam(Integer shareId, Course masterCourse, Course receiveCourse, Teacher receiveTeacher, Integer status) {
        this.shareId = shareId;
        this.masterCourse = masterCourse;
        this.receiveCourse = receiveCourse;
        this.receiveTeacher = receiveTeacher;
        this.status = status;
    }

    public ShareTeam() {
    }

    public Integer getShareId() {
        return shareId;
    }

    public void setShareId(Integer shareId) {
        this.shareId = shareId;
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
