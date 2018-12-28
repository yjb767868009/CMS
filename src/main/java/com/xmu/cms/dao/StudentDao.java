package com.xmu.cms.dao;

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
public class StudentDao {
    @Autowired
    private StudentMapper studentMapper;

    public List<Student> getAllStudents() {
        return studentMapper.getAllStudents();
    }

    public void newStudent(List<Student> students) {
        for (Student newStudent : students) {
            Student student = studentMapper.getStudentByAccount(newStudent.getAccount());
            if (student == null) {
                studentMapper.insertStudent(newStudent);
            }
        }
    }

    public Integer modifyStudentInfo(Student student) {
        return studentMapper.updateStudentInfo(student);
    }

    public Integer updateStudentPassword(Student student) {
        return studentMapper.updateStudentPassword(student);
    }

    public Integer deleteStudent(BigInteger studentId) {
        return studentMapper.deleteStudent(studentId);
    }

    public List<Student> getStudentByName(String name) {
        return studentMapper.getStudentByName(name);
    }

    public Student getStudentByAccount(String account) {
        return studentMapper.getStudentByAccount(account);
    }

    public void activateStudent(Student student) {
        studentMapper.activeStudent(student);
    }

    public Student getStudentById(BigInteger userId) {
        return studentMapper.getStudentById(userId);
    }

    public Integer modifyStudentEmail(BigInteger studentId, Student student) {
        Student modifyStudent = studentMapper.getStudentById(studentId);
        modifyStudent.setEmail(student.getEmail());
        return studentMapper.updateStudent(modifyStudent);
    }

    public List<Student> getNoTeamStudentInCourse(BigInteger courseId) {
        return studentMapper.getNoTeamStudentInCourse(courseId);
    }

    public List<Student> getStudentInCourse(BigInteger courseId) {
        return studentMapper.getStudentInCourse(courseId);
    }
}
