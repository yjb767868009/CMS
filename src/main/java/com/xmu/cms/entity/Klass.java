package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
public class Klass {
    private Integer klassId;
    private Course course;
    private Integer grade;
    private String name;
    private String classTime;
    private String classPlace;

    public Klass(Integer klassId, Course course, Integer grade, String name, String classPlace, String classTime) {
        this.klassId = klassId;
        this.course = course;
        this.grade = grade;
        this.name = name;
        this.classPlace = classPlace;
        this.classTime = classTime;
    }

    public Klass() {
    }

    public Integer getKlassId() {
        return klassId;
    }

    public void setKlassId(Integer id) {
        this.klassId = id;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
