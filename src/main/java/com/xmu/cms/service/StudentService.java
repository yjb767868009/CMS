package com.xmu.cms.service;

import com.xmu.cms.entity.Student;

import java.util.List;

/**
 * @author JuboYu on 2018/11/23.
 * @version 1.0
 */
public interface StudentService {
    public List<Student> getAllStudents();

    public Student newStudent(Student student);

    public Student modifyStudent(Student student);

    public Student deleteStudent(Student student);
}
