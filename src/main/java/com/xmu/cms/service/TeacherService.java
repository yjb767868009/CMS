package com.xmu.cms.service;

import com.xmu.cms.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JuboYu on 2018/11/23.
 * @version 1.0
 */
public interface TeacherService {
    List<Teacher> getAllTeachers();

    String newTeacher(Teacher teacher);

    String modifyTeacher(Teacher teacher);

    String deleteTeacher(Teacher teacher);
}
