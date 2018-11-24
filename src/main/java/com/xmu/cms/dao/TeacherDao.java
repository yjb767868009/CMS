package com.xmu.cms.dao;

import com.xmu.cms.entity.Teacher;

import java.util.List;

/**
 * @author JuboYu on 2018/11/24.
 * @version 1.0
 */
public interface TeacherDao {
    List<Teacher> getAllTeachers();

    String newTeacher(Teacher teacher);

    String modifyTeacher(Teacher teacher);

    String deleteTeacher(Teacher teacher);
}
