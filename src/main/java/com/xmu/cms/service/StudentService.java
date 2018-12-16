package com.xmu.cms.service;

import com.xmu.cms.entity.Student;

import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */

public interface StudentService {
    List<Student> getAllStudents();

    Map<String, String> modifyStudentInfo(Integer studentId, Student student);

    Map<String, String> modifyStudentPassword(Integer studentId, Student student);

    Map<String, String> deleteStudent(Integer studentId);

    List<Student> getStudentByName(String name);

    Student getStudentByAccount(String account);

    Map<String, String> activateStudent(Integer userId, Student student);
}
