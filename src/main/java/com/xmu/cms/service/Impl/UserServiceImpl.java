package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.service.UserService;
import com.xmu.cms.support.Token;
import com.xmu.cms.support.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Map<String, String> userLogIn(String account, String password) {
        Map<String, String> message = new HashMap<String, String>(2);
        Teacher teacher = teacherDao.getTeacherByAccount(account);
        if (teacher == null) {
            Student student = studentDao.getStudentByAccount(account);
            if (student == null) {
                message.put("message", "No this account");
            } else {
                if (password.equals(student.getPassword())) {
                    Token.setToken(new UserInfo(student.getStudentId(), "student"));
                    message.put("message", "student");
                    message.put("activation", "false");
                } else {
                    message.put("message", "Account or password error");
                }
            }
        } else {
            if (password.equals(teacher.getPassword())) {
                Token.setToken(new UserInfo(teacher.getTeacherId(), "teacher"));
                message.put("message", "teacher");
                message.put("activation", "false");
            } else {
                message.put("message", "Account or password error");
            }
        }
        return message;
    }

    @Override
    public Map<String, String> getMyInfo() {
        Map<String, String> messages = new HashMap<String, String>(2);
        UserInfo info = Token.getToken();
        assert info != null;
        if (info.getUserType().equals("teacher")) {
            Teacher teacher = teacherDao.getTeacherById(info.getUserId());
            messages.put("name", teacher.getName());
            messages.put("account", teacher.getAccount());
            messages.put("message", "Success");
            return messages;
        }
        if (info.getUserType().equals("student")) {
            Student student = studentDao.getStudentById(info.getUserId());
            messages.put("name", student.getName());
            messages.put("account", student.getAccount());
            messages.put("message", "Success");
            return messages;
        }
        messages.put("message", "Error");
        return messages;
    }
}
