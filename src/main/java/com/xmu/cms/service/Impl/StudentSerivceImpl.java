package com.xmu.cms.service.Impl;

import com.xmu.cms.mapper.StudentMapper;
import com.xmu.cms.entity.Student;
import com.xmu.cms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
@Service
public class StudentSerivceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getAllStudents() {
        return studentMapper.getAllStudents();
    }

    @Override
    public Map<String, String> modifyStudentById(Integer studentId, String name, String account, String email) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = studentMapper.modifyStudentByStudentId(studentId, name, account, email);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Update error");
        }
        return message;
    }

    @Override
    public Map<String, String> modifyStudentPasswordById(Integer studentId, String password) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = studentMapper.modifyStudentPasswordByStudentId(studentId, password);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Update error");
        }
        return message;
    }

    @Override
    public Map<String, String> deleteStudentById(Integer studentId) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = studentMapper.deleteStudentByStudentId(studentId);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Delete error");
        }
        return message;
    }

    @Override
    public List<Student> getStudentByName(String name) {
        return studentMapper.getStudentByName(name);
    }

    @Override
    public Student getStudentByAccount(String account) {
        return studentMapper.getStudentByAccount(account);
    }

}
