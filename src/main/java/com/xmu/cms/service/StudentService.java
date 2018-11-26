package com.xmu.cms.service;

import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
public interface StudentService {
    public List<Student> getAllStudents();

    public String modifyStudentById(Integer studentId, String name, String account, String email);

    public String modifyStudentPasswordById(Integer studentId, String password);

    public String deleteStudentById(Integer studentId);

}
