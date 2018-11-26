package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
public class TurningClass {
    private Integer turningClassId;
    private String name;
    private String introduction;
    private String classPlace;
    private String classTime;

    public TurningClass(Integer turningClassId, String name, String introduction, String classPlace, String classTime) {
        this.turningClassId = turningClassId;
        this.name = name;
        this.introduction = introduction;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
}
