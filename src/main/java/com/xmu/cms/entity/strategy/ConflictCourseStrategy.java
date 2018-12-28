package com.xmu.cms.entity.strategy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Team;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JuboYu on 2018/12/22.
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConflictCourseStrategy implements Strategy {
    private BigInteger strategyId;
    private List<Course> courses;
    private String type;

    public ConflictCourseStrategy() {
    }

    public ConflictCourseStrategy(BigInteger strategyId, String type) {
        this.strategyId = strategyId;
        this.type = type;
    }

    public BigInteger getStrategyId() {
        return strategyId;
    }

    @Override
    public void setStrategyId(BigInteger strategyId) {
        this.strategyId = strategyId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public Boolean checkValid(Team team) {
        List<Student> students = team.getMembers();
        Course hasCourse = null;
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(team.getLeader());
        for (Student student : students) {
            List<Course> studentCourses = student.getCourses();
            for (Course studentCourse : studentCourses) {
                for (Course course : courses) {
                    if (course.getCourseId().equals(hasCourse.getCourseId())) {
                        // TODO: 2018/12/28
                    }
                }
            }
        }
        return true;
    }

    @Override
    public List<Strategy> getStrategy(List<Strategy> strategies) {
        type = CONFLICT_COURSE_STRATEGY;
        strategies.add(this);
        return strategies;
    }

    @Override
    public String getType() {
        return CONFLICT_COURSE_STRATEGY;
    }

    public void setType(String type) {
        this.type = type;
    }
}
