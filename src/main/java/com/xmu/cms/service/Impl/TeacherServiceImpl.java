package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public String newTeacher(String name, String account, String password, String email) {
        Integer count = teacherDao.newTeacher(name, account, password, email, "", "", 1, false);
        if (count == 0) {
            return "Insert error";
        } else {
            return "Success";
        }
    }

    @Override
    public String modifyTeacherById(Integer teacherId, String name, String account, String email) {
        Integer count = teacherDao.modifyTeacherById(teacherId, name, account, email);
        if (count == 0) {
            return "Update error";
        } else {
            return "Success";
        }
    }

    @Override
    public String modifyTeacherPasswordById(Integer teacherId, String password) {
        Integer count = teacherDao.modifyTeacherPasswordById(teacherId, password);
        if (count == 0) {
            return "Update error";
        } else {
            return "Success";
        }
    }

    @Override
    public String deleteTeacherById(Integer teacherId) {
        Integer count = teacherDao.deleteTeacherById(teacherId);
        if (count == 0) {
            return "Delete error";
        } else {
            return "Success";
        }
    }

    @Override
    public List<Teacher> getTeacherByName(String name) {
        return teacherDao.getTeacherByName(name);
    }
}
