package com.xmu.cms.service;

import com.xmu.cms.entity.Admin;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.support.MyUser;
import com.xmu.cms.support.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
public interface UserService {
    Map<String, String> adminLogIn(Admin admin);

    Map<String, String> userLogIn(String account, String password);

    Map<String, String> getMyInfo(UserInfo info);

    Map<String, String> modifyEmail(UserInfo info, MyUser user);

    Map<String, String> modifyPassword(UserInfo info, MyUser user);

    List<Teacher> getAllTeachers();

    Map<String, String> createTeacher(Teacher teacher);

    Map<String, String> deleteTeacher(Integer teacherId);

    Teacher getTeacherByAccount(String account);

    List<Teacher> getTeacherByName(String name);

    Teacher getTeacherById(Integer teacherId);

    Map<String, String> updateTeacherInfo(Integer teacherId, Teacher teacher);

    Map<String, String> updateTeacherPassword(Integer teacherId, Teacher teacher);

    List<Student> getAllStudents();

    Map<String, String> modifyStudentInfo(Student student);

    Map<String, String> modifyStudentPassword(Student student);

    Map<String, String> deleteStudent(Integer studentId);

    List<Student> getStudentByName(String name);

    Student getStudentByAccount(String account);

    Map<String, String> activateStudent(Integer userId, Student student);

    Student getStudentById(Integer studentId);

    List<Student> getNoTeamStudent(Integer courseId);
}
