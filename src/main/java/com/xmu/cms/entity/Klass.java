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
    private String klassTime;
    private String klassPlace;

    public Klass(BigInteger klassId, Course course, Integer grade, Integer klassSerial, String klassPlace, String klassTime) {
        this.klassId = klassId;
        this.course = course;
        this.grade = grade;
        this.klassSerial = klassSerial;
        this.klassPlace = klassPlace;
        this.klassTime = klassTime;
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

    public String getKlassPlace() {
        return klassPlace;
    }

    public void setKlassPlace(String classPlace) {
        this.klassPlace = classPlace;
    }

    public String getKlassTime() {
        return klassTime;
    }

    public void setKlassTime(String classTime) {
        this.klassTime = classTime;
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

    public String getName() {
        return grade.toString() + "-" + klassSerial.toString();
    }
}
