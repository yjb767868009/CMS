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
    private List<Course> conflictCourses;

    public ConflictCourseStrategy(BigInteger strategyId, List<Course> conflictCourses) {
        this.strategyId = strategyId;
        this.conflictCourses = conflictCourses;
    }

    public ConflictCourseStrategy() {
    }

    public List<Course> getCourses() {
        return conflictCourses;
    }

    public void setCourses(List<Course> conflictCourses) {
        this.conflictCourses = conflictCourses;
    }

    public BigInteger getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(BigInteger strategyId) {
        this.strategyId = strategyId;
    }

    @Override
    public Boolean checkValid(Team team) {
        List<Student> students = team.getMembers();
        BigInteger hasCourseId = null;
        for (Student student : students) {
            List<Course> courses = student.getCourses();
            for (Course course : courses) {
                if (conflictCourses.contains(course)) {
                    if (hasCourseId != null && !course.getCourseId().equals(hasCourseId)) {
                        return false;
                    } else {
                        hasCourseId = course.getCourseId();
                    }
                }
            }
        }
        return true;
    }

    @Override
    public List<Strategy> getStrategy(List<Strategy> strategies) {
        strategies.add(this);
        return strategies;
    }


}
