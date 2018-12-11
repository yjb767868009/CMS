package com.xmu.cms.dao;

import com.xmu.cms.entity.Student;

import java.util.List;

/**
 * @author JuboYu on 2018/12/11.
 * @version 1.0
 */
public interface StudentDao {
    List<Student> getAllStudents();

    Integer newStudent(Student student);

    Integer modifyStudentInfo(Integer studentId, Student student);

    Integer modifyStudentPassword(Integer studentId, Student student);

    Integer deleteStudent(Integer studentId);

    List<Student> getStudentByName(String name);

    Student getStudentByAccount(String account);
}
