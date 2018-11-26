package com.xmu.cms.service;

import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
public interface TeacherService {
    public List<Teacher> getAllTeachers();

    public String newTeacher(String name, String account, String password, String email);

    public String modifyTeacherById(Integer teacherId, String name, String account, String email);

    public String modifyTeacherPasswordById(Integer teacherId, String password);

    public String deleteTeacherById(Integer teacherId);

}
