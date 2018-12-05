package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Map<String, String> newTeacher(String name, String account, String password, String email) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = teacherDao.newTeacher(name, account, password, email, "", 1, false);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Insert error");
        }
        return message;
    }

    @Override
    public Map<String, String> modifyTeacherByTeacherId(Integer teacherId, String name, String account, String email) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = teacherDao.modifyTeacherByTeacherId(teacherId, name, account, email);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Update error");
        }
        return message;
    }

    @Override
    public Map<String, String> modifyTeacherPasswordByTeacherId(Integer teacherId, String password) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = teacherDao.modifyTeacherPasswordByTeacherId(teacherId, password);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Update error");
        }
        return message;
    }

    @Override
    public Map<String, String> deleteTeacherByTeacherId(Integer teacherId) {
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
}
