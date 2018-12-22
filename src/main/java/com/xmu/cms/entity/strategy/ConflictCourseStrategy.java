package com.xmu.cms.entity.strategy;

import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Team;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
public class ConflictCourseStrategy implements Strategy {
    private BigInteger strategyId;
    private Course courseOne;
    private Course courseTwo;

    public ConflictCourseStrategy() {
    }

    public ConflictCourseStrategy(BigInteger strategyId, Course courseOne, Course courseTwo) {
        this.strategyId = strategyId;
        this.courseOne = courseOne;
        this.courseTwo = courseTwo;
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
    public List<Strategy> findSubStrategy() {
        return null;
    }

    @Override
    public List<Strategy> getStrategy() {
        return null;
    }
}
