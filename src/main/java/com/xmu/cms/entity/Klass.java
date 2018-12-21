package com.xmu.cms.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Klass {
    private BigInteger klassId;
    private Course course;
    private Integer grade;
    private Integer klassSerial;
    private String classTime;
    private String classPlace;

    public Klass(BigInteger klassId, Course course, Integer grade, Integer klassSerial, String classPlace, String classTime) {
        this.klassId = klassId;
        this.course = course;
        this.grade = grade;
        this.klassSerial = klassSerial;
        this.classPlace = classPlace;
        this.classTime = classTime;
    }

    public Klass() {
    }

    public BigInteger getKlassId() {
        return klassId;
    }

    public void setKlassId(BigInteger id) {
        this.klassId = id;
    }

    public Integer getKlassSerial() {
        return this.klassSerial;
    }

    public void setKlassSerial(Integer klassSerial) {
        this.klassSerial = klassSerial;
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
