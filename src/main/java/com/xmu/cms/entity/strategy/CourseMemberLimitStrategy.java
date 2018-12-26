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
public class CourseMemberLimitStrategy implements Strategy {
    private BigInteger strategyId;
    private Course course;
    private Integer minMember;
    private Integer maxMember;

    public CourseMemberLimitStrategy() {
    }

    public CourseMemberLimitStrategy(BigInteger strategyId, Course course, Integer minMember, Integer maxMember) {
        this.strategyId = strategyId;
        this.course = course;
        this.minMember = minMember;
        this.maxMember = maxMember;
    }

    public BigInteger getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(BigInteger strategyId) {
        this.strategyId = strategyId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getMinMember() {
        return minMember;
    }

    public void setMinMember(Integer minMember) {
        this.minMember = minMember;
    }

    public Integer getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(Integer maxMember) {
        this.maxMember = maxMember;
    }

    @Override
    public Boolean checkValid(Team team) {
        Integer courseCount = 0;
        List<Student> students = team.getMembers();
        for (Student student : students) {
            List<Course> courses = student.getCourses();
            for (Course studentCourse : courses) {
                if (course.getCourseId().equals(studentCourse.getCourseId())) {
                    courseCount++;
                }
            }
        }
        if (maxMember != null && courseCount > maxMember) return false;
        if (minMember != null && courseCount < minMember) return false;
        return true;
    }

    @Override
    public List<Strategy> findSubStrategy() {
        return null;
    }

    @Override
    public List<Strategy> getStrategy(List<Strategy> strategies) {
        strategies.add(this);
        return strategies;
    }
}
