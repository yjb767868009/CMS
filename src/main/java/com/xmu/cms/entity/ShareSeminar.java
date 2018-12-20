package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/12/20.
 * @version 1.0
 */
public class ShareSeminar {
    private Integer shareSeminarId;
    private Course masterCourse;
    private Course receiveCourse;
    private Teacher receiveTeacher;
    private Integer status;

    public ShareSeminar(Integer shareSeminarId, Course masterCourse, Course receiveCourse, Teacher receiveTeacher, Integer status) {
        this.shareSeminarId = shareSeminarId;
        this.masterCourse = masterCourse;
        this.receiveCourse = receiveCourse;
        this.receiveTeacher = receiveTeacher;
        this.status = status;
    }

    public ShareSeminar() {
    }

    public Integer getShareSeminarId() {
        return shareSeminarId;
    }

    public void setShareSeminarId(Integer shareSeminarId) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
