package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
public class Share {
    private Integer shareId;
    private String shareType;
    private Course masterCourse;
    private Course receiveCourse;

    public Share(Integer shareId, String shareType, Course masterCourse, Course receiveCourse) {
        this.shareId = shareId;
        this.shareType = shareType;
        this.masterCourse = masterCourse;
        this.receiveCourse = receiveCourse;
    }

    public Share() {
    }

    public Integer getShareId() {
        return shareId;
    }

    public void setShareId(Integer shareId) {
        this.shareId = shareId;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
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
}
