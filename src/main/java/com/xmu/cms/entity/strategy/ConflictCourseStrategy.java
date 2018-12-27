package com.xmu.cms.entity.strategy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Team;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConflictCourseStrategy implements Strategy {
    private BigInteger strategyId;
    private Course courseOne;
    private Course courseTwo;
    private String type;

    public ConflictCourseStrategy() {
    }

    public ConflictCourseStrategy(BigInteger strategyId, Course courseOne, Course courseTwo, String type) {
        this.strategyId = strategyId;
        this.courseOne = courseOne;
        this.courseTwo = courseTwo;
        this.type = type;
    }

    public BigInteger getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(BigInteger strategyId) {
        this.strategyId = strategyId;
    }

    public Course getCourseOne() {
        return courseOne;
    }

    public void setCourseOne(Course courseOne) {
        this.courseOne = courseOne;
    }

    public Course getCourseTwo() {
        return courseTwo;
    }

    public void setCourseTwo(Course courseTwo) {
        this.courseTwo = courseTwo;
    }

    @Override
    public Boolean checkValid(Team team) {
        List<Student> students = team.getMembers();
        Boolean hasCourseOne = false;
        Boolean hasCourseTwo = false;
        for (Student student : students) {
            List<Course> courses = student.getCourses();
            for (Course course : courses) {
                if (course.getCourseId().equals(courseOne.getCourseId())) {
                    hasCourseOne = true;
                }
                if (course.getCourseId().equals(courseTwo.getCourseId())) {
                    hasCourseTwo = true;
                }
            }
            if (hasCourseOne && hasCourseTwo) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Strategy> getStrategy(List<Strategy> strategies) {
        String strategyClass = this.getClass().getName();
        type = strategyClass.substring(strategyClass.lastIndexOf(".") + 1);
        strategies.add(this);
        return strategies;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
