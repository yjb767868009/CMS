package com.xmu.cms.controller;

import com.xmu.cms.aspect.CheckAdminPermission;
import com.xmu.cms.entity.Student;
import com.xmu.cms.service.AdminService;
import com.xmu.cms.service.StudentService;
import com.xmu.cms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xmu.cms.entity.Teacher;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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
    public Map<String, String> adminLogIn(@RequestParam(value = "account") String account,
                                          @RequestParam(value = "password") String password,
                                          HttpSession session) {
        return adminService.adminLogIn(account, password, session);
    }

    @GetMapping(value = "/teachers")
    @CheckAdminPermission
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping(value = "/students")
    @CheckAdminPermission
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping(value = "/teacher")
    @CheckAdminPermission
    public Map<String, String> newTeacher(@RequestParam(value = "name") String name,
                                          @RequestParam(value = "account") String account,
                                          @RequestParam(value = "password") String password,
                                          @RequestParam(value = "email") String email) {
        return teacherService.newTeacher(name, account, password, email);
    }

    @PutMapping(value = "/teacher/{teacherId}")
    @CheckAdminPermission
    public Map<String, String> modifyTeacherById(@PathVariable("teacherId") Integer teacherId,
                                                 @RequestParam(value = "name") String name,
                                                 @RequestParam(value = "account") String account,
                                                 @RequestParam(value = "email") String email) {
        return teacherService.modifyTeacherByTeacherId(teacherId, name, account, email);
    }

    @PatchMapping(value = "/teacher/{teacherId}/modifyPassword")
    @CheckAdminPermission
    public Map<String, String> modifyTeacherPasswordById(@PathVariable("teacherId") Integer teacherId,
                                                         @RequestParam(value = "password") String password) {
        return teacherService.modifyTeacherPasswordByTeacherId(teacherId, password);
    }

    @DeleteMapping(value = "/teacher/{teacherId}")
    @CheckAdminPermission
    public Map<String, String> deleteTeacher(@PathVariable("teacherId") Integer teacherId) {
        return teacherService.deleteTeacherByTeacherId(teacherId);
    }

    @PutMapping(value = "/student/{studentId}")
    @CheckAdminPermission
    public Map<String, String> modifyStudent(@PathVariable("studentId") Integer studentId,
                                             @RequestParam("name") String name,
                                             @RequestParam("account") String account,
                                             @RequestParam("email") String email) {
        return studentService.modifyStudentById(studentId, name, account, email);
    }

    @PatchMapping(value = "student/{studentId}/modifyPassword")
    @CheckAdminPermission
    public Map<String, String> modifyStudentPasswordById(@PathVariable("studentId") Integer studentId,
                                                         @RequestParam(value = "password") String password) {
        return studentService.modifyStudentPasswordById(studentId, password);
    }

    @DeleteMapping(value = "/student/{studentId}")
    @CheckAdminPermission
    public Map<String, String> deleteStudent(@PathVariable("studentId") Integer studentId) {
        return studentService.deleteStudentById(studentId);
    }

    @GetMapping(value = "/teachers/searchByName")
    @CheckAdminPermission
    public List<Teacher> getTeacherByName(@RequestParam("name") String name) {
        return teacherService.getTeacherByName(name);
    }

    @GetMapping(value = "/teachers/searchByAccount")
    @CheckAdminPermission
    public List<Teacher> getTeacherByAccount(@RequestParam("account") String account) {
        return teacherService.getTeacherByAccount(account);
    }

    @GetMapping(value = "/students/searchByName")
    @CheckAdminPermission
    public List<Student> getStudentByName(@RequestParam("name") String name) {
        return studentService.getStudentByName(name);
    }

    @GetMapping(value = "/students/searchByAccount")
    @CheckAdminPermission
    public List<Student> getStudentByAccount(@RequestParam("account") String account) {
        return studentService.getStudentByAccount(account);
    }

}
