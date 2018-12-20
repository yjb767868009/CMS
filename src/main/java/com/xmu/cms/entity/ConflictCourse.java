package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/12/20.
 * @version 1.0
 */
public class ConflictCourse {
    private Integer conflictCourseId;
    private Course course1;
    private Course course2;

    public ConflictCourse(Integer conflictCourseId, Course course1, Course course2) {
        this.conflictCourseId = conflictCourseId;
        this.course1 = course1;
        this.course2 = course2;
    }

    public ConflictCourse() {
    }

    public Integer getConflictCourseId() {
        return conflictCourseId;
    }

    public void setConflictCourseId(Integer conflictCourseId) {
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
