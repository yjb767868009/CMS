package com.xmu.cms.controller;

import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.dao.AdminDao;
import com.xmu.cms.entity.Student;
import com.xmu.cms.service.AdminService;
import com.xmu.cms.service.StudentService;
import com.xmu.cms.service.TeacherService;
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
@RequestMapping(value = "/api/admin")
public class AdminController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/login")
    public String adminLogIn(@RequestParam(value = "account") String account,
                             @RequestParam(value = "password") String password) {
        return adminService.adminLogIn(account,password);
    }

    @GetMapping(value = "/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping(value = "/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping(value = "/teacher")
    public String newTeacher(@RequestParam(value = "name") String name,
                             @RequestParam(value = "account") String account,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "email") String email) {
        return teacherService.newTeacher(name,account,password,email);
    }

    @PutMapping(value = "/teacher/{teacherId}")
    public String modifyTeacherById(@PathVariable("teacherId") Integer teacherId,
                                    @RequestParam(value = "name") String name,
                                    @RequestParam(value = "account") String account,
                                    @RequestParam(value = "email") String email) {
        return teacherService.modifyTeacherById(teacherId,name,account,email);
    }

    @PatchMapping(value = "/teacher/{teacherId}/modifyPassword")
    public String modifyTeacherPasswordById(@PathVariable("teacherId") Integer teacherId,
                                            @RequestParam(value = "password") String password) {
        return teacherService.modifyTeacherPasswordById(teacherId,password);
    }

    @DeleteMapping(value = "/teacher/{teacherId}")
    public String deleteTeacher(@PathVariable("teacherId") Integer teacherId) {
        return teacherService.deleteTeacherById(teacherId);
    }

    @PutMapping(value = "/student/{studentId}")
    public String modifyStudent(@PathVariable("studentId") Integer studentId,
                                @RequestParam("name") String name,
                                @RequestParam("account") String account,
                                @RequestParam("email") String email) {
        return studentService.modifyStudentById(studentId,name,account,email);
    }

    @PatchMapping(value = "student/{studentId}/modifyPassword")
    public String modifyStudentPasswordById(@PathVariable("studentId") Integer studentId,
                                            @RequestParam(value = "password") String password) {
        return studentService.modifyStudentPasswordById(studentId,password);
    }

    @DeleteMapping(value = "/student/{studentId}")
    public String deleteStudent(@PathVariable("studentId") Integer studentId) {
        return studentService.deleteStudentById(studentId);
    }

}
