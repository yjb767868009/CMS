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

    Integer modifyStudentInfo(Student student);

    Integer modifyStudentPassword(Student student);

    Integer deleteStudent(Integer studentId);

    List<Student> getStudentByName(String name);

    Student getStudentByAccount(String account);

    Integer activateStudent(Integer studentId, Student student);

    Student getStudentById(Integer userId);

    Integer modifyStudentEmail(Integer studentId, Student student);

    List<Student> getNoTeamStudentInCourse(Integer courseId);
}
