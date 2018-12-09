package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
public class Clbum {
    private Integer clbumId;
    private Course course;
    private String name;
    private String classTime;
    private String classPlace;

    public Clbum(Integer clbumId, Course course, String name, String classPlace, String classTime) {
        this.clbumId = clbumId;
        this.course = course;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
