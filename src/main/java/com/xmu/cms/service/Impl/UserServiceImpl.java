package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
    public String userLogIn(String account, String password) {
        String findTeacherPassword = teacherDao.getPasswordByAccount(account);
        if (findTeacherPassword == null) {
            String findStudentPassword = studentDao.getPasswordByAccount(account);
            if (findStudentPassword == null) {
                return "No this account";
            } else {
                if (findStudentPassword.equals(password)) {
                    return "Success";
                } else {
                    return "Account or password error";
                }
            }
        } else {
            if (findTeacherPassword.equals(password)) {
                return "Teacher";
            } else {
                return "Account or password error";
            }
        }
    }
}
