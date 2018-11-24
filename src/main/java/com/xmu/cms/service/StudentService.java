package com.xmu.cms.service;

import com.xmu.cms.entity.Student;

import java.util.List;

/**
 * @author JuboYu on 2018/11/23.
 * @version 1.0
 */
public interface StudentService {
    List<Student> getAllStudents();

    String newStudent(Student student);

    String modifyStudent(Student student);

    String deleteStudent(Student student);
}
