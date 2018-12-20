package com.xmu.cms.dao;

import com.xmu.cms.entity.Teacher;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/11.
 * @version 1.0
 */
public interface TeacherDao {

    List<Teacher> getAllTeachers();

    Teacher getTeacherByAccount(String account);

    List<Teacher> getTeacherByName(String name);

    Teacher getTeacherById(BigInteger teacherId);

    Integer updateTeacherInfo(BigInteger teacherId, Teacher teacher);

    Integer updateTeacherPassword(BigInteger teacherId, Teacher teacher);

    Integer deleteTeacherByTeacherId(BigInteger teacherId);

    Integer createTeacher(Teacher teacher) throws Exception;

    Integer modifyTeacherEmail(BigInteger teacherId, Teacher teacher);

    Integer modifyTeacherPassword(BigInteger teacherId, Teacher teacher);
}
