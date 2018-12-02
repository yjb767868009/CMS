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

    Map<String, String> modifyStudentById(Integer studentId, String name, String account, String email);

    Map<String, String> modifyStudentPasswordById(Integer studentId, String password);

    Map<String, String> deleteStudentById(Integer studentId);

    List<Student> getStudentByName(String name);

    List<Student> getStudentByAccount(String account);
}
