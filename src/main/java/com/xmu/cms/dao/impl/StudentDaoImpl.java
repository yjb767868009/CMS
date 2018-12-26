package com.xmu.cms.dao.impl;

import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.entity.Student;
import com.xmu.cms.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/11.
 * @version 1.0
 */
@Component
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getAllStudents() {
        return studentMapper.getAllStudents();
    }

    @Override
    public void newStudent(List<Student> students) {
        for (Student newStudent : students) {
            Student student = studentMapper.getStudentByAccount(newStudent.getAccount());
            if (student == null) {
                studentMapper.insertStudent(newStudent);
            }
        }
    }

    @Override
    public Integer modifyStudentInfo(Student student) {
        return studentMapper.updateStudentInfo(student);
    }

    @Override
    public Integer updateStudentPassword(Student student) {
        return studentMapper.updateStudentPassword(student);
    }

    @Override
    public Integer deleteStudent(BigInteger studentId) {
        return studentMapper.deleteStudent(studentId);
    }

    @Override
    public List<Student> getStudentByName(String name) {
        return studentMapper.getStudentByName(name);
    }

    @Override
    public Student getStudentByAccount(String account) {
        return studentMapper.getStudentByAccount(account);
    }

    @Override
    public Integer activateStudent(BigInteger studentId, Student student) {
        Student modifyStudent = studentMapper.getStudentById(studentId);
        modifyStudent.setPassword(student.getPassword());
        modifyStudent.setPassword(student.getEmail());
        return studentMapper.updateStudent(student);
    }

    @Override
    public Student getStudentById(BigInteger userId) {
        return studentMapper.getStudentById(userId);
    }

    @Override
    public Integer modifyStudentEmail(BigInteger studentId, Student student) {
        Student modifyStudent = studentMapper.getStudentById(studentId);
        modifyStudent.setEmail(student.getEmail());
        return studentMapper.updateStudent(modifyStudent);
    }

    @Override
    public List<Student> getNoTeamStudentInCourse(BigInteger courseId) {
        return studentMapper.getNoTeamStudentInCourse(courseId);
    }
}
