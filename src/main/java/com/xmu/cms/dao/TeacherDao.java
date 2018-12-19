package com.xmu.cms.dao;

import com.xmu.cms.entity.Teacher;

import java.util.List;

/**
 * @author JuboYu on 2018/12/11.
 * @version 1.0
 */
public interface TeacherDao {

    List<Teacher> getAllTeachers();

    Teacher getTeacherByAccount(String account);

    List<Teacher> getTeacherByName(String name);

    Teacher getTeacherById(Integer teacherId);

    Integer updateTeacherInfo(Integer teacherId, Teacher teacher);

    Integer updateTeacherPassword(Integer teacherId, Teacher teacher);

    Integer deleteTeacherByTeacherId(Integer teacherId);

    Integer createTeacher(Teacher teacher) throws Exception;

    Integer modifyTeacherEmail(Integer teacherId, Teacher teacher);

    Integer modifyTeacherPassword(Integer teacherId, Teacher teacher);
}
