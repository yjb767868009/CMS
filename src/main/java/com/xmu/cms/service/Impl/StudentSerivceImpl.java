package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.entity.Student;
import com.xmu.cms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
@Service
public class StudentSerivceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public String modifyStudentById(Integer studentId, String name, String account, String email) {
        Integer count = studentDao.modifyStudentById(studentId, name, account, email);
        if (count == 0) {
            return "Update error";
        } else {
            return "Success";
        }
    }

    @Override
    public String modifyStudentPasswordById(Integer studentId, String password) {
        Integer count = studentDao.modifyStudentPasswordById(studentId, password);
        if (count == 0) {
            return "Update error";
        } else {
            return "Success";
        }
    }

    @Override
    public String deleteStudentById(Integer studentId) {
        Integer count = studentDao.deleteStudentById(studentId);
        if (count == 0) {
            return "Delete error";
        } else {
            return "Success";
        }
    }
}
