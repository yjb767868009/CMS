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

    Map<String, String> newTeacher(String name, String account, String password, String email);

    Map<String, String> modifyTeacherByTeacherId(Integer teacherId, String name, String account, String email);

    Map<String, String> modifyTeacherPasswordByTeacherId(Integer teacherId, String password);

    Map<String, String> deleteTeacherByTeacherId(Integer teacherId);

    Teacher getTeacherByAccount(String account);

    List<Teacher> getTeacherByName(String name);
}
