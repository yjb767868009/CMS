package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JuboYu on 2018/12/11.
 * @version 1.0
 */
@Component
public class TeacherDaoImpl implements TeacherDao {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherMapper.getAllTeachers();
    }

    @Override
    public Teacher getTeacherByAccount(String account) {
        return teacherMapper.getTeacherByAccount(account);
    }

    @Override
    public List<Teacher> getTeacherByName(String name) {
        return teacherMapper.getTeacherByName(name);
    }

    @Override
    public Teacher getTeacherById(Integer teacherId) {
        return teacherMapper.getTeacherById(teacherId);
    }

    @Override
    public Integer updateTeacherInfo(Integer teacherId, Teacher teacher) {
        Teacher modifyTeacher = teacherMapper.getTeacherById(teacherId);
        modifyTeacher.setAccount(teacher.getAccount());
        modifyTeacher.setName(teacher.getName());
        modifyTeacher.setEmail(teacher.getEmail());
        return teacherMapper.updateTeacher(modifyTeacher);
    }

    @Override
    public Integer updateTeacherPassword(Integer teacherId, Teacher teacher) {
        Teacher modifyTeacher = teacherMapper.getTeacherById(teacherId);
        modifyTeacher.setPassword(teacher.getPassword());
        return teacherMapper.updateTeacher(modifyTeacher);
    }

    @Override
    public Integer deleteTeacherByTeacherId(Integer teacherId) {
        return teacherMapper.deleteTeacher(teacherId);
    }

    @Override
    public Integer createTeacher(Teacher teacher) throws Exception {
        teacher.setActivation(false);
        return teacherMapper.insertTeacher(teacher);
    }

    @Override
    public Integer modifyTeacherEmail(Integer teacherId, Teacher teacher) {
        Teacher modifyTeacher = teacherMapper.getTeacherById(teacherId);
        modifyTeacher.setEmail(teacher.getEmail());
        return teacherMapper.updateTeacher(modifyTeacher);
    }

    @Override
    public Integer modifyTeacherPassword(Integer teacherId, Teacher teacher) {
        Teacher modifyTeacher = teacherMapper.getTeacherById(teacherId);
        modifyTeacher.setPassword(teacher.getPassword());
        return teacherMapper.updateTeacher(modifyTeacher);
    }
}
