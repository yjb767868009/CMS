package com.xmu.cms.controller;

import com.xmu.cms.entity.Student;
import com.xmu.cms.service.CourseService;
import com.xmu.cms.service.SeminarService;
import com.xmu.cms.service.UserService;
import com.xmu.cms.support.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private SeminarService seminarService;

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "")
    public List<Student> getAllStudents() {
        return userService.getAllStudents();
    }

    @Secured("ROLE_STUDENT")
    @PutMapping(value = "/active")
    public Map<String, String> activateStudent(UserInfo info,
                                               @RequestBody Student student) {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            userService.activateStudent(info.getUserId(), student);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/{studentId}/information")
    public Map<String, String> modifyStudentInfo(@PathVariable("studentId") BigInteger studentId,
                                                 @RequestBody Student student) {
        student.setStudentId(studentId);
        return userService.modifyStudentInfo(student);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/{studentId}/password")
    public Map<String, String> modifyStudentPasswordById(@PathVariable("studentId") BigInteger studentId,
                                                         @RequestBody Student student) {
        student.setStudentId(studentId);
        return userService.modifyStudentPassword(student);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/{studentId}")
    public Map<String, String> deleteStudent(@PathVariable("studentId") BigInteger studentId) {
        return userService.deleteStudent(studentId);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/searchByName")
    public List<Student> getStudentByName(@RequestParam("name") String name) {
        return userService.getStudentByName(name);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/searchByAccount")
    public Student getStudentByAccount(@RequestParam("account") String account) {
        return userService.getStudentByAccount(account);
    }

}
