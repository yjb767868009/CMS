package com.xmu.cms.service;

import com.xmu.cms.entity.Admin;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.support.MyUser;

import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
public interface UserService {
    Map<String, String> adminLogIn(Admin admin);

    Map<String, String> userLogIn(String account, String password);

    Map<String, String> getMyInfo(Integer userId, String userType);

    Map<String, String> modifyEmail(Integer userId, String userType, MyUser user);

    Map<String, String> modifyPassword(Integer userId, String userType, MyUser user);

    List<Teacher> getAllTeachers();

    Map<String, String> createTeacher(Teacher teacher);

    Map<String, String> deleteTeacher(Integer teacherId);

    Teacher getTeacherByAccount(String account);

    List<Teacher> getTeacherByName(String name);

    Teacher getTeacherById(Integer teacherId);

    Map<String, String> updateTeacherInfo(Integer teacherId, Teacher teacher);

    Map<String, String> updateTeacherPassword(Integer teacherId, Teacher teacher);

    List<Student> getAllStudents();

    Map<String, String> modifyStudentInfo(Integer studentId, Student student);

    Map<String, String> modifyStudentPassword(Integer studentId, Student student);

    Map<String, String> deleteStudent(Integer studentId);

    List<Student> getStudentByName(String name);

    Student getStudentByAccount(String account);

    Map<String, String> activateStudent(Integer userId, Student student);

    Student getStudentById(Integer studentId);

    List<Student> getNoTeamStudent(Integer courseId);
}
