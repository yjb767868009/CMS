package com.xmu.cms.dao;

import com.xmu.cms.entity.Course;
import com.xmu.cms.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/11.
 * @version 1.0
 */
@Component
public class CourseDao {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private KlassMapper klassMapper;

    @Autowired
    private RoundMapper roundMapper;

    @Autowired
    private SeminarMapper seminarMapper;

    @Autowired
    private KlassSeminarMapper klassSeminarMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private ShareSeminarMapper shareSeminarMapper;

    @Autowired
    private ShareTeamMapper shareTeamMapper;

    @Autowired
    private SeminarScoreMapper seminarScoreMapper;

    public List<Course> getAllCoursesByTeacherId(BigInteger teacherId) {
        return courseMapper.getAllCourseByTeacherId(teacherId);
    }

    public void deleteCourse(BigInteger courseId) {
        courseMapper.deleteCourseById(courseId);
    }

    public List<Course> getAllCourseByStudentId(BigInteger studentId) {
        return courseMapper.getAllCourseByStudentId(studentId);
    }

    public BigInteger createCourse(Course course) {
        return BigInteger.valueOf(courseMapper.insertCourse(course));
    }

    public Course getCourse(BigInteger courseId) {
        return courseMapper.getCourseById(courseId);
    }

    public void updateCourseTeamMainCourse(Course course) {
        courseMapper.updateCourseTeamMainCourse(course);
        courseMapper.deleteCourseTeam(course);
    }

    public void updateCourseSeminarMainCourse(Course course) {
        courseMapper.updateCourseSeminarMainCourse(course);
    }

    public Course getTeamMainCourse(BigInteger courseId) {
        return courseMapper.getTeamMainCourseByCourseId(courseId);
    }

    public Course getSeminarMainCourse(BigInteger courseId) {
        return courseMapper.getSeminarMainCourseByCourseId(courseId);
    }
}
