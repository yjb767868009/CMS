package com.xmu.cms.service.impl;

import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.entity.Student;
import com.xmu.cms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JuboYu on 2018/11/23.
 * @version 1.0
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public String newStudent(Student student) {
        return studentDao.newStudent(student);
    }

    @Override
    public String modifyStudent(Student student) {
        return studentDao.modifyStudent(student);
    }

    @Override
    public String deleteStudent(Student student) {
        return studentDao.deleteStudent(student);
    }
}
