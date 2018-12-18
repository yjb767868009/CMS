package com.xmu.cms.service;

import com.xmu.cms.entity.*;

import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
public interface CourseService {
    List<Course> getAllCoursesByTeacher(Teacher teacher);

    Map<String, String> deleteCourseById(Integer courseId);

    List<Course> getAllCoursesByStudent(Student student);

    Map<String, String> createCourse(Course course);

    Course getCourse(Integer courseId);

    List<Team> getTeamInCourse(Integer courseId);

    List<Team> getTeamInCourseByStudent(Integer courseId, Integer studentId);

    Map<String, String> newKlass(Integer courseId, Klass klass);

    List<Klass> getKlassInCourse(Integer courseId);

    Map<String, String> deleteKlass(Integer classId);

    List<Share> getShareInCourse(Integer courseId);

    Map<String, String> deleteShare(Integer courseId, Integer shareId);
}
