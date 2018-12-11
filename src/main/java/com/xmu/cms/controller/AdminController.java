package com.xmu.cms.controller;

import com.xmu.cms.entity.Admin;
import com.xmu.cms.entity.Student;
import com.xmu.cms.service.AdminService;
import com.xmu.cms.service.StudentService;
import com.xmu.cms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xmu.cms.entity.Teacher;

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
    public Map<String, String> adminLogIn(@RequestBody Admin admin) {
        return adminService.adminLogIn(admin);
    }

    @PostMapping(value = "/setJWT")
    public Map<String, String> setJWT(@RequestBody Admin admin) {
        return adminService.adminLogIn(admin);
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
    public Map<String, String> newTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    @PutMapping(value = "/teacher/{teacherId}/information")
    public Map<String, String> modifyTeacherInfo(@PathVariable("teacherId") Integer teacherId,
                                                 @RequestBody Teacher teacher) {
        return teacherService.updateTeacherInfo(teacherId, teacher);
    }

    @PutMapping(value = "/teacher/{teacherId}/password")
    public Map<String, String> modifyTeacherPassword(@PathVariable("teacherId") Integer teacherId,
                                                     @RequestBody Teacher teacher) {
        return teacherService.updateTeacherPassword(teacherId, teacher);
    }

    @DeleteMapping(value = "/teacher/{teacherId}")
    public Map<String, String> deleteTeacher(@PathVariable("teacherId") Integer teacherId) {
        return teacherService.deleteTeacher(teacherId);
    }

    @PutMapping(value = "/student/{studentId}")
    public Map<String, String> modifyStudent(@PathVariable("studentId") Integer studentId,
                                             @RequestParam("name") String name,
                                             @RequestParam("account") String account,
                                             @RequestParam("email") String email) {
        return studentService.modifyStudentById(studentId, name, account, email);
    }

    @PatchMapping(value = "student/{studentId}/modifyPassword")
    public Map<String, String> modifyStudentPasswordById(@PathVariable("studentId") Integer studentId,
                                                         @RequestParam(value = "password") String password) {
        return studentService.modifyStudentPasswordById(studentId, password);
    }

    @DeleteMapping(value = "/student/{studentId}")
    public Map<String, String> deleteStudent(@PathVariable("studentId") Integer studentId) {
        return studentService.deleteStudentById(studentId);
    }

    @GetMapping(value = "/teachers/searchByName")
    public List<Teacher> getTeacherByName(@RequestParam("name") String name) {
        return teacherService.getTeacherByName(name);
    }

    @GetMapping(value = "/teachers/searchByAccount")
    public Teacher getTeacherByAccount(@RequestParam("account") String account) {
        return teacherService.getTeacherByAccount(account);
    }

    @GetMapping(value = "/students/searchByName")
    public List<Student> getStudentByName(@RequestParam("name") String name) {
        return studentService.getStudentByName(name);
    }

    @GetMapping(value = "/students/searchByAccount")
    public Student getStudentByAccount(@RequestParam("account") String account) {
        return studentService.getStudentByAccount(account);
    }

}
