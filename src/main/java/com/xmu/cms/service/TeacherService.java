package com.xmu.cms.service;

import com.xmu.cms.entity.Teacher;

import java.util.List;

/**
 * @author JuboYu on 2018/11/23.
 * @version 1.0
 */
public interface TeacherService {
    public List<Teacher> getAllTeachers();

    public Teacher newTeacher(Teacher teacher);

    public Teacher modifyTeacher(Teacher teacher);

    public Teacher deleteTeacher(Teacher teacher);
}
