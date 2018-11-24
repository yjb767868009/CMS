package com.xmu.cms.service.impl;

import com.xmu.cms.entity.Teacher;
import com.xmu.cms.dao.TeacherDao;
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
    public String newTeacher(Teacher teacher) {
        return teacherDao.newTeacher(teacher);
    }

    @Override
    public String modifyTeacher(Teacher teacher) {
        return teacherDao.modifyTeacher(teacher);
    }

    @Override
    public String deleteTeacher(Teacher teacher) {
        return teacherDao.deleteTeacher(teacher);
    }
}
