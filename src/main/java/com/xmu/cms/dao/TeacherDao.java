package com.xmu.cms.dao;

import com.xmu.cms.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JuboYu on 2018/11/24.
 * @version 1.0
 */
@Mapper
public interface TeacherDao {
    List<Teacher> getAllTeachers();

    String newTeacher(Teacher teacher);

    String modifyTeacher(Teacher teacher);

    String deleteTeacher(Teacher teacher);
}
