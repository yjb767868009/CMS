package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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
                    message.put("userType", "student");
                    message.put("userId", student.getStudentId().toString());
                    message.put("message", "student");
                    message.put("activation", "false");
                } else {
                    message.put("message", "Account or password error");
                }
            }
        } else {
            if (password.equals(teacher.getPassword())) {
                message.put("userType", "teacher");
                message.put("userId", teacher.getTeacherId().toString());
                message.put("message", "teacher");
                message.put("activation", "false");
            } else {
                message.put("message", "Account or password error");
            }
        }
        return message;
    }
}
