package com.xmu.cms.controller;

import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @PostMapping(value = "/login")
    public String userLogIn(@RequestParam(value = "account") String account,
                            @RequestParam(value = "password") String password) {
        String findTeacherPassword = teacherDao.getPasswordByAccount(account);
        if (findTeacherPassword == null) {
            String findStudentPassword = studentDao.getPasswordByAccount(account);
            if (findStudentPassword == null) {
                return "No this account";
            } else {
                if (findStudentPassword.equals(password)) {
                    return "Student";
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

    @PostMapping(value = "/activation")
    public String userActivation(@RequestParam(value = "password") String password,
                                 @RequestParam(value = "email") String email){
        return studentDao.active(studentId,password,email);
    }

    @PostMapping(value = "/findPassword")
    public String findPassword(@RequestParam(value = "email") String email){
        return "Success";
    }

    @PostMapping(value = "/resetPassword")
    public String resetPassword(@RequestParam(value = "password") String password){
        return "Success";
    }

    @PostMapping(value = "/modifyEmail")
    public String modifyEmail(@RequestParam(value = "email") String email){
        return "Success";
    }

    @PatchMapping(value = "/modifyMessageInterval")
    public String modifyMessageInterval(@RequestParam(value = "messageInterval")Integer messageOnterval){
        return "Success";
    }



}
