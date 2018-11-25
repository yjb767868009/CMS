package com.xmu.cms.controller;

import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.dao.AdminDao;
import com.xmu.cms.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xmu.cms.entity.Admin;
import com.xmu.cms.entity.Teacher;

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
        Integer count = teacherDao.newTeacher(name, account, password, email,1,false);
        if (count==0){
            return "Insert error";
        }else {
            return "Success";
        }
    }

    @PutMapping(value = "/teacher/{teacherId}")
    public String modifyTeacherById(@PathVariable("teacherId") Integer teacherId,
                                    @RequestParam(value = "name") String name,
                                    @RequestParam(value = "account") String account,
                                    @RequestParam(value = "email") String email) {
        Integer count = teacherDao.modifyTeacherById(teacherId, name, account, email);
        if (count == 0) {
            return "Update error";
        } else {
            return "Success";
        }
    }

    @PatchMapping(value = "/teacher/{teacherId}/modifyPassword")
    public String modifyTeacherPasswordById(@PathVariable("teacherId") Integer teacherId,
                                            @RequestParam(value = "password") String password) {
        Integer count = teacherDao.modifyTeacherPasswordById(teacherId, password);
        if (count == 0) {
            return "Update error";
        } else {
            return "Success";
        }
    }

    @DeleteMapping(value = "/teacher/{teacherId}")
    public String deleteTeacher(@PathVariable("teacherId") Integer teacherId) {
        Integer count = teacherDao.deleteTeacherById(teacherId);
        if (count == 0) {
            return "Delete error";
        } else {
            return "Success";
        }
    }

    @PutMapping(value = "/student/{studentId}")
    public String modifyStudent(@PathVariable("studentId") Integer studentId,
                                @RequestParam("name") String name,
                                @RequestParam("account") String account,
                                @RequestParam("email") String email) {
        Integer count = studentDao.modifyStudentById(studentId, name, account, email);
        if (count == 0) {
            return "Update error";
        } else {
            return "Success";
        }
    }

    @PatchMapping(value = "student/{studentId}/modifyPassword")
    public String modifyStudentPasswordById(@PathVariable("studentId") Integer studentId,
                                            @RequestParam(value = "password") String password) {
        Integer count = studentDao.modifyStudentPasswordById(studentId, password);
        if (count == 0) {
            return "Update error";
        } else {
            return "Success";
        }
    }

    @DeleteMapping(value = "/student/{studentId}")
    public String deleteStudent(@PathVariable("studentId") Integer studentId) {
        Integer count = studentDao.deleteStudentById(studentId);
        if (count == 0) {
            return "Delete error";
        } else {
            return "Success";
        }
    }

}
