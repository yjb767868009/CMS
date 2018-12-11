package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.mapper.TeacherMapper;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherDao.getAllTeachers();
    }

    @Override
    public Map<String, String> createTeacher(Teacher teacher) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = teacherDao.createTeacher(teacher);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Insert error");
        }
        return message;
    }

    @Override
    public Map<String, String> deleteTeacher(Integer teacherId) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = teacherDao.deleteTeacherByTeacherId(teacherId);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Delete error");
        }
        return message;
    }

    @Override
    public Teacher getTeacherByAccount(String account) {
        return teacherDao.getTeacherByAccount(account);
    }

    @Override
    public List<Teacher> getTeacherByName(String name) {
        return teacherDao.getTeacherByName(name);
    }

    @Override
    public Teacher getTeacherById(Integer teacherId) {
        return teacherDao.getTeacherById(teacherId);
    }

    @Override
    public Map<String, String> updateTeacherInfo(Integer teacherId, Teacher teacher) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = teacherDao.updateTeacherInfo(teacherId, teacher);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public Map<String, String> updateTeacherPassword(Integer teacherId, Teacher teacher) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = teacherDao.updateTeacherPassword(teacherId, teacher);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }
}
