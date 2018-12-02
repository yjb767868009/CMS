package com.xmu.cms.service;

import com.xmu.cms.entity.Teacher;

import java.util.List;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */

public interface TeacherService {
    List<Teacher> getAllTeachers();

    String newTeacher(String name, String account, String password, String email);

    String modifyTeacherById(Integer id, String name, String account, String email);

    String modifyTeacherPasswordById(Integer id, String password);

    String deleteTeacherById(Integer id);

    List<Teacher> getTeacherByName(String name);
}
