package com.xmu.cms.dao;

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
public class TeacherDao {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CourseMapper courseMapper;

    public List<Teacher> getAllTeachers() {
        return teacherMapper.getAllTeachers();
    }

    public Teacher getTeacherByAccount(String account) {
        return teacherMapper.getTeacherByAccount(account);
    }

    public List<Teacher> getTeacherByName(String name) {
        return teacherMapper.getTeacherByName(name);
    }

    public Teacher getTeacherById(BigInteger teacherId) {
        return teacherMapper.getTeacherById(teacherId);
    }

    public Teacher getFullTeacherById(BigInteger teacherId) {
        Teacher teacher = teacherMapper.getTeacherById(teacherId);
        List<Course> courses = courseMapper.getAllCourseByTeacherId(teacher.getTeacherId());
        teacher.setCourses(courses);
        return teacher;
    }

    public Integer updateTeacherInfo(BigInteger teacherId, Teacher teacher) {
        Teacher modifyTeacher = teacherMapper.getTeacherById(teacherId);
        modifyTeacher.setAccount(teacher.getAccount());
        modifyTeacher.setName(teacher.getName());
        modifyTeacher.setEmail(teacher.getEmail());
        return teacherMapper.updateTeacher(modifyTeacher);
    }

    public Integer updateTeacherPassword(Teacher teacher) {
        Teacher modifyTeacher = teacherMapper.getTeacherById(teacher.getTeacherId());
        modifyTeacher.setPassword(teacher.getPassword());
        modifyTeacher.setActivation(true);
        return teacherMapper.updateTeacher(modifyTeacher);
    }

    public Integer deleteTeacherByTeacherId(BigInteger teacherId) {
        return teacherMapper.deleteTeacher(teacherId);
    }

    public BigInteger createTeacher(Teacher teacher) throws Exception {
        teacher.setActivation(false);
        return teacherMapper.insertTeacher(teacher);
    }

    public Integer modifyTeacherEmail(BigInteger teacherId, Teacher teacher) {
        Teacher modifyTeacher = teacherMapper.getTeacherById(teacherId);
        modifyTeacher.setEmail(teacher.getEmail());
        return teacherMapper.updateTeacher(modifyTeacher);
    }
}
