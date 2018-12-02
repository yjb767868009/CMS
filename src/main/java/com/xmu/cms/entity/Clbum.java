package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
public class Clbum {
    private Integer clbumId;
    private Integer courseId;
    private String name;
    private String classTime;
    private String classPlace;

    public Clbum(Integer clbumId, Integer courseId, String name, String classPlace, String classTime) {
        this.clbumId = clbumId;
        this.courseId = courseId;
        this.name = name;
        this.classPlace = classPlace;
        this.classTime = classTime;
    }

    public Integer getClbumId() {
        return clbumId;
    }

    public void setClbumId(Integer id) {
        this.clbumId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassPlace() {
        return classPlace;
    }

    public void setClassPlace(String classPlace) {
        this.classPlace = classPlace;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
