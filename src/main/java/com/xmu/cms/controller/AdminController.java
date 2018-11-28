package com.xmu.cms.controller;

import com.xmu.cms.aspect.CheckPermission;
import com.xmu.cms.entity.Student;
import com.xmu.cms.service.AdminService;
import com.xmu.cms.service.StudentService;
import com.xmu.cms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xmu.cms.entity.Teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author JuboYu on 2018/11/23.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @PostMapping(value = "/login")
    public String adminLogIn(@RequestParam(value = "account") String account,
                             @RequestParam(value = "password") String password,
                             HttpSession session) {
        return adminService.adminLogIn(account, password, session);
    }

    @GetMapping(value = "/teachers")
    @CheckPermission
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping(value = "/students")
    @CheckPermission
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping(value = "/teacher")
    @CheckPermission
    public String newTeacher(@RequestParam(value = "name") String name,
                             @RequestParam(value = "account") String account,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "email") String email) {
        return teacherService.newTeacher(name, account, password, email);
    }

    @PutMapping(value = "/teacher/{teacherId}")
    @CheckPermission
    public String modifyTeacherById(@PathVariable("teacherId") Integer teacherId,
                                    @RequestParam(value = "name") String name,
                                    @RequestParam(value = "account") String account,
                                    @RequestParam(value = "email") String email) {
        return teacherService.modifyTeacherById(teacherId, name, account, email);
    }

    @PatchMapping(value = "/teacher/{teacherId}/modifyPassword")
    @CheckPermission
    public String modifyTeacherPasswordById(@PathVariable("teacherId") Integer teacherId,
                                            @RequestParam(value = "password") String password) {
        return teacherService.modifyTeacherPasswordById(teacherId, password);
    }

    @DeleteMapping(value = "/teacher/{teacherId}")
    @CheckPermission
    public String deleteTeacher(@PathVariable("teacherId") Integer teacherId) {
        return teacherService.deleteTeacherById(teacherId);
    }

    @PutMapping(value = "/student/{studentId}")
    @CheckPermission
    public String modifyStudent(@PathVariable("studentId") Integer studentId,
                                @RequestParam("name") String name,
                                @RequestParam("account") String account,
                                @RequestParam("email") String email) {
        return studentService.modifyStudentById(studentId, name, account, email);
    }

    @PatchMapping(value = "student/{studentId}/modifyPassword")
    @CheckPermission
    public String modifyStudentPasswordById(@PathVariable("studentId") Integer studentId,
                                            @RequestParam(value = "password") String password) {
        return studentService.modifyStudentPasswordById(studentId, password);
    }

    @DeleteMapping(value = "/student/{studentId}")
    @CheckPermission
    public String deleteStudent(@PathVariable("studentId") Integer studentId) {
        return studentService.deleteStudentById(studentId);
    }

    @GetMapping(value = "/teachers/nameSearch/{name}")
    @CheckPermission
    public List<Teacher> getTeacherByName(@PathVariable("name") String name) {
        return teacherService.getTeacherByName(name);
    }

    @GetMapping(value = "/students/nameSearch/{name}")
    @CheckPermission
    public List<Student> getStudentByName(@PathVariable("name") String name) {
        return studentService.getStudentByName(name);
    }

    public String noLogIn() {
        return "NoLogIn";
    }
}
