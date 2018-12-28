package com.xmu.cms.controller;

import com.xmu.cms.entity.Admin;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/23.
 * @version 1.0
 */
@RestController
public class AdminController {

    @Autowired
    private UserService userService;

    @Secured("ROLE_ADMIN")
    @PostMapping(value = "/admin/login")
    public Map<String, String> adminLogIn(@RequestBody Admin admin) {
        return userService.adminLogIn(admin);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/admin/student")
    public List<Student> getAllStudents() {
        return userService.getAllStudents();
    }

    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/admin/student/{studentId}/information")
    public Map<String, String> modifyStudentInfo(@PathVariable("studentId") BigInteger studentId,
                                                 @RequestBody Student student) {
        student.setStudentId(studentId);
        return userService.modifyStudentInfo(student);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/admin/student/{studentId}/password")
    public Map<String, String> modifyStudentPasswordById(@PathVariable("studentId") BigInteger studentId,
                                                         @RequestBody Student student) {
        student.setStudentId(studentId);
        return userService.modifyStudentPassword(student);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/admin/student/{studentId}")
    public Map<String, String> deleteStudent(@PathVariable("studentId") BigInteger studentId) {
        return userService.deleteStudent(studentId);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/admin/student/searchByName")
    public List<Student> getStudentByName(@RequestParam("name") String name) {
        return userService.getStudentByName(name);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/admin/student/searchByAccount")
    public Student getStudentByAccount(@RequestParam("account") String account) {
        return userService.getStudentByAccount(account);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/admin/teacher")
    public List<Teacher> getAllTeachers() {
        return userService.getAllTeachers();
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(value = "/admin/teacher")
    public Map<String, String> newTeacher(@RequestBody Teacher teacher) {
        return userService.createTeacher(teacher);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/admin/teacher/{teacherId}/information")
    public Map<String, String> modifyTeacherInfo(@PathVariable("teacherId") BigInteger teacherId,
                                                 @RequestBody Teacher teacher) {
        return userService.updateTeacherInfo(teacherId, teacher);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/admin/teacher/{teacherId}/password")
    public Map<String, String> modifyTeacherPassword(@PathVariable("teacherId") BigInteger teacherId,
                                                     @RequestBody Teacher teacher) {
        return userService.updateTeacherPassword(teacher);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/admin/teacher/{teacherId}")
    public Map<String, String> deleteTeacher(@PathVariable("teacherId") BigInteger teacherId) {
        return userService.deleteTeacher(teacherId);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/admin/teacher/searchByName")
    public List<Teacher> getTeacherByName(@RequestParam("name") String name) {
        return userService.getTeacherByName(name);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/admin/teacher/searchByAccount")
    public Teacher getTeacherByAccount(@RequestParam("account") String account) {
        return userService.getTeacherByAccount(account);
    }
}
