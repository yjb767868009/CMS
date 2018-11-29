package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
public class TurningClass {
    private Integer turningClassId;
    private Integer courseId;
    private String name;
    private String classTime;
    private String classPlace;

    public TurningClass(Integer turningClassId, Integer courseId, String name, String classPlace, String classTime) {
        this.turningClassId = turningClassId;
        this.courseId = courseId;
        this.name = name;
        this.classPlace = classPlace;
        this.classTime = classTime;
    }

    public Integer getTurningClassId() {
        return turningClassId;
    }

    public void setTurningClassId(Integer turningClassId) {
        this.turningClassId = turningClassId;
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
