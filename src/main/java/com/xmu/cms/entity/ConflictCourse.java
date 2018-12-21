package com.xmu.cms.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/12/20.
 * @version 1.0
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ConflictCourse {
    private BigInteger conflictCourseId;
    private Course course1;
    private Course course2;

    public ConflictCourse(BigInteger conflictCourseId, Course course1, Course course2) {
        this.conflictCourseId = conflictCourseId;
        this.course1 = course1;
        this.course2 = course2;
    }

    public ConflictCourse() {
    }

    public BigInteger getConflictCourseId() {
        return conflictCourseId;
    }

    public void setConflictCourseId(BigInteger conflictCourseId) {
        this.conflictCourseId = conflictCourseId;
    }

    public Course getCourse1() {
        return course1;
    }

    public void setCourse1(Course course1) {
        this.course1 = course1;
    }

    public Course getCourse2() {
        return course2;
    }

    public void setCourse2(Course course2) {
        this.course2 = course2;
    }
}
