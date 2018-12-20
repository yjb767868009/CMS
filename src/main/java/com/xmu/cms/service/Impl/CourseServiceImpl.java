package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.CourseDao;
import com.xmu.cms.dao.KlassDao;
import com.xmu.cms.dao.ShareTeamDao;
import com.xmu.cms.dao.TeamDao;
import com.xmu.cms.entity.*;
import com.xmu.cms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private KlassDao klassDao;

    @Autowired
    private ShareTeamDao shareTeamDao;

    @Override
    public List<Course> getAllCoursesByTeacher(Teacher teacher) {
        return courseDao.getAllCoursesByTeacherId(teacher.getTeacherId());
    }

    @Override
    public Map<String, String> deleteCourseById(Integer courseId) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = courseDao.deleteCourse(courseId);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public List<Course> getAllCoursesByStudent(Student student) {
        return courseDao.getAllCourseByStudentId(student.getStudentId());
    }

    @Override
    public Map<String, String> createCourse(Course course) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = courseDao.createCourse(course);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public Course getCourse(Integer courseId) {
        return courseDao.getCourse(courseId);
    }

    @Override
    public List<Team> getTeamInCourse(Integer courseId) {
        return teamDao.getTeamInCourse(courseId);
    }

    @Override
    public List<Team> getTeamInCourseByStudent(Integer courseId, Integer studentId) {
        List<Team> teams = new ArrayList<Team>();
        teams.add(teamDao.getTeamInCourseByStudent(courseId, studentId));
        return teams;
    }

    @Override
    public Map<String, String> newKlass(Integer courseId, Klass klass) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = klassDao.newKlass(courseId, klass);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public List<Klass> getKlassInCourse(Integer courseId) {
        return klassDao.getAllKlass(courseId);
    }

    @Override
    public Map<String, String> deleteKlass(Integer classId) {
        //TODO
        return null;
    }

    @Override
    public List<ShareTeam> getShareInCourse(Integer courseId) {
        return shareTeamDao.getShareInCourse(courseId);
    }

    @Override
    public Map<String, String> deleteShare(Integer courseId, Integer shareId) {
        Map<String, String> message = new HashMap<String, String>(1);
        Integer count = shareTeamDao.deleteShare(courseId, shareId);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }
}
