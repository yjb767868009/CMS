package com.xmu.cms.service;

import com.xmu.cms.entity.Teacher;

import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */

public interface TeacherService {
    List<Teacher> getAllTeachers();

    Map<String, String> createTeacher(Teacher teacher);

    Map<String, String> deleteTeacher(Integer teacherId);

    Teacher getTeacherByAccount(String account);

    List<Teacher> getTeacherByName(String name);

    Teacher getTeacherById(Integer teacherId);

    Map<String, String> updateTeacherInfo(Integer teacherId, Teacher teacher);

    Map<String, String> updateTeacherPassword(Integer teacherId, Teacher teacher);
}
