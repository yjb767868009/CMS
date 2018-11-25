package com.xmu.cms.controller;

import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.entity.Student;
import com.xmu.cms.service.StudentService;
import com.xmu.cms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xmu.cms.entity.Admin;
import com.xmu.cms.entity.Teacher;


import java.util.ArrayList;
import java.util.List;

/**
 * @author JuboYu on 2018/11/23.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private AdminDao adminDao;

    @PostMapping(value = "/login")
    public String adminLogIn(@RequestParam(value = "account") String account,
                             @RequestParam(value = "password") String password) {
        String findPassword = adminDao.getPasswordByAccount(account);
        if (findPassword == null) {
            return "No this account";
        } else {
            if (password.equals(findPassword)) {
                return "Success";
            } else {
                return "Account or Password error";
            }
        }
    }

    @GetMapping(value = "/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherDao.getAllTeachers();
    }

    @GetMapping(value = "/students")
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @PostMapping(value = "/teacher")
    public String newTeacher(@RequestParam(value = "name") String name,
                             @RequestParam(value = "account") String account,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "email") String email) {
        return teacherDao.newTeacher(teacher);
    }

    @PutMapping(value = "/teacher/{teacherId}")
    public String modifyTeacherById(@PathVariable("teacherId") Long teacherId,
                                    @RequestParam(value = "name") String name,
                                    @RequestParam(value = "account") String account,
                                    @RequestParam(value = "email") String email) {
        return teacherDao.modifyTeacherById(teacherId, name, account, email);
    }

    @PatchMapping(value = "/teacher/{teacherId}/modifyPassword")
    public String modifyTeacherPasswordById(@PathVariable("teacherId") Long teacherId,
                                            @RequestParam(value = "password") String password) {
        return teacherDao.modifyTeacherPasswordById(teacherId, password);
    }

    @DeleteMapping(value = "/teacher/{teacherId}")
    public String deleteTeacher(@PathVariable("teacherId") Long teacherId) {
        return teacherDao.deleteTeacherById(teacherId);
    }

    @PutMapping(value = "/student/{studentId}")
    public String modifyStudent(@PathVariable("studentId") Long studentId,
                                 @RequestParam("name") String name,
                                 @RequestParam("account") String account,
                                 @RequestParam("email") String email) {
        return studentDao.modifyStudentById(studentId,name,account,email);
    }

    @PatchMapping(value = "student/{studentId}/modifyPassword")
    public String modifyStudentPasswordById(@PathVariable("studentId") Long studentId,
                                            @RequestParam(value = "password") String password){
        return studentDao.modifyStudentPasswordById(studentId,password);
    }

    @DeleteMapping(value = "/student/{studentId}")
    public String deleteStudent(@PathVariable("studentId") Long studentId) {
        return studentDao.deleteStudentById(studentId);
    }

}
