package com.xmu.cms.dao;

import com.xmu.cms.entity.Student;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/11.
 * @version 1.0
 */
public interface StudentDao {
    List<Student> getAllStudents();

    void newStudent(List<Student> students);

    Integer modifyStudentInfo(Student student);

    Integer updateStudentPassword(Student student);

    Integer deleteStudent(BigInteger studentId);

    List<Student> getStudentByName(String name);

    Student getStudentByAccount(String account);

    Integer activateStudent(BigInteger studentId, Student student);

    Student getStudentById(BigInteger userId);

    Integer modifyStudentEmail(BigInteger studentId, Student student);

    List<Student> getNoTeamStudentInCourse(BigInteger courseId);
}
