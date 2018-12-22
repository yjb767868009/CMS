package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.entity.Course;
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
        return teacherMapper.getTeacherByAccount(account);
    }

    @Override
    public List<Teacher> getTeacherByName(String name) {
        return teacherMapper.getTeacherByName(name);
    }

    @Override
    public Teacher getTeacherById(BigInteger teacherId) {
        return teacherMapper.getTeacherById(teacherId);
    }

    @Override
    public Teacher getFullTeacherById(BigInteger teacherId) {
        Teacher teacher = teacherMapper.getTeacherById(teacherId);
        List<Course> courses = courseMapper.getAllCourseByTeacherId(teacher.getTeacherId());
        teacher.setCourses(courses);
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
    public Integer updateTeacherPassword(Teacher teacher) {
        Teacher modifyTeacher = teacherMapper.getTeacherById(teacher.getTeacherId());
        modifyTeacher.setPassword(teacher.getPassword());
        modifyTeacher.setActivation(true);
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
}
