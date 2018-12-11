package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.entity.Student;
import com.xmu.cms.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public Integer newStudent(Student student) {
        return studentMapper.insertStudent(student);
    }

    @Override
    public Integer modifyStudentInfo(Integer studentId, Student student) {
        Student modifyStudent = studentMapper.getStudentById(studentId);
        modifyStudent.setAccount(student.getAccount());
        modifyStudent.setName(student.getName());
        modifyStudent.setEmail(student.getEmail());
        return studentMapper.updateStudent(student);
    }

    @Override
    public Integer modifyStudentPassword(Integer studentId, Student student) {
        Student modifyStudent = studentMapper.getStudentById(studentId);
        modifyStudent.setPassword(student.getPassword());
        return studentMapper.updateStudent(student);
    }

    @Override
    public Integer deleteStudent(Integer studentId) {
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
}
