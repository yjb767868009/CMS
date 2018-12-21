package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.mapper.CourseMapper;
import com.xmu.cms.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/11.
 * @version 1.0
 */
@Component
public class TeacherDaoImpl implements TeacherDao {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherMapper.getAllTeachers();
    }

    @Override
    public Teacher getTeacherByAccount(String account) {
        Teacher teacher = teacherMapper.getTeacherByAccount(account);
        //teacher.setCourses(courseMapper.getAllCourseByTeacherId(teacher.getTeacherId()));
        return teacher;
    }

    @Override
    public List<Teacher> getTeacherByName(String name) {
        return teacherMapper.getTeacherByName(name);
    }

    @Override
    public Teacher getTeacherById(BigInteger teacherId) {
        Teacher teacher = teacherMapper.getTeacherById(teacherId);
        teacher.setCourses(courseMapper.getAllCourseByTeacherId(teacher.getTeacherId()));
        return teacher;
    }

    @Override
    public Integer updateTeacherInfo(BigInteger teacherId, Teacher teacher) {
        Teacher modifyTeacher = teacherMapper.getTeacherById(teacherId);
        modifyTeacher.setAccount(teacher.getAccount());
        modifyTeacher.setName(teacher.getName());
        modifyTeacher.setEmail(teacher.getEmail());
        return teacherMapper.updateTeacher(modifyTeacher);
    }

    @Override
    public Integer updateTeacherPassword(BigInteger teacherId, Teacher teacher) {
        Teacher modifyTeacher = teacherMapper.getTeacherById(teacherId);
        modifyTeacher.setPassword(teacher.getPassword());
        return teacherMapper.updateTeacher(modifyTeacher);
    }

    @Override
    public Integer deleteTeacherByTeacherId(BigInteger teacherId) {
        return teacherMapper.deleteTeacher(teacherId);
    }

    @Override
    public Integer createTeacher(Teacher teacher) throws Exception {
        teacher.setActivation(false);
        return teacherMapper.insertTeacher(teacher);
    }

    @Override
    public Integer modifyTeacherEmail(BigInteger teacherId, Teacher teacher) {
        Teacher modifyTeacher = teacherMapper.getTeacherById(teacherId);
        modifyTeacher.setEmail(teacher.getEmail());
        return teacherMapper.updateTeacher(modifyTeacher);
    }

    @Override
    public Integer modifyTeacherPassword(BigInteger teacherId, Teacher teacher) {
        Teacher modifyTeacher = teacherMapper.getTeacherById(teacherId);
        modifyTeacher.setPassword(teacher.getPassword());
        return teacherMapper.updateTeacher(modifyTeacher);
    }
}
