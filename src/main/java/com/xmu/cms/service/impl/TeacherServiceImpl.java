package com.xmu.cms.service.impl;

import com.xmu.cms.entity.Teacher;
import com.xmu.cms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author JuboYu on 2018/11/24.
 * @version 1.0
 */
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherDao.getAllTeachers();
    }

    @Override
    public Teacher newTeacher(Teacher teacher) {
        return teacherDao.newTeacher();
    }

    @Override
    public Teacher modifyTeacher(Teacher teacher) {
        return teacherDao.modifyTeacher();
    }

    @Override
    public Teacher deleteTeacher(Teacher teacher) {
        return teacherDao.deleteTeacher();
    }
}
