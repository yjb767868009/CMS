package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.entity.Team;
import com.xmu.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

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
    public String userLogIn(String account, String password, HttpSession session) {
        Teacher teacher = teacherDao.getTeacherByAccount(account);
        if (teacher == null) {
            Student student = studentDao.getStudentByAccount(account);
            if (student == null) {
                return "No this account";
            } else {
                String studentPassword = student.getPassword();
                if (studentPassword.equals(password)) {
                    session.setAttribute("userType", "student");
                    session.setAttribute("userId", student.getStudentId());
                    return "Student";
                } else {
                    return "Account or password error";
                }
            }
        } else {
            String teacherPassword = teacher.getPassword();
            if (teacherPassword.equals(password)) {
                session.setAttribute("userType", "teacher");
                session.setAttribute("userId", teacher.getTeacherId());
                return "Teacher";
            } else {
                return "Account or password error";
            }
        }
    }
}
