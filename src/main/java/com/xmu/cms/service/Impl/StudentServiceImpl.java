package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.StudentDao;
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
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public Map<String, String> modifyStudentInfo(Integer studentId, Student student) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = studentDao.modifyStudentInfo(studentId, student);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Update error");
        }
        return message;
    }

    @Override
    public Map<String, String> modifyStudentPassword(Integer studentId, Student student) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = studentDao.modifyStudentPassword(studentId, student);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Update error");
        }
        return message;
    }

    @Override
    public Map<String, String> deleteStudent(Integer studentId) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = studentDao.deleteStudent(studentId);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Delete error");
        }
        return message;
    }

    @Override
    public List<Student> getStudentByName(String name) {
        return studentDao.getStudentByName(name);
    }

    @Override
    public Student getStudentByAccount(String account) {
        return studentDao.getStudentByAccount(account);
    }

    @Override
    public Map<String, String> activateStudent(Integer studentId, Student student) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = studentDao.activateStudent(studentId, student);
        if (count == 1) {
            message.put("message", "成功");
        } else {
            message.put("message", "信息不合法");
        }
        return message;
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return studentDao.getStudentById(studentId);
    }

    @Override
    public List<Student> getNoTeamStudent(Integer courseId) {
        return studentDao.getNoTeamStudentInCourse(courseId);
    }

}
