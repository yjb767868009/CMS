package com.xmu.cms.controller;

import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.service.MailService;
import com.xmu.cms.service.SeminarService;
import com.xmu.cms.service.TeamService;
import com.xmu.cms.service.UserService;
import com.xmu.cms.support.MyUser;
import com.xmu.cms.support.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MailService mailService;

    @PostMapping(value = "/user/login")
    public Map<String, String> userLogIn(@RequestParam(value = "account") String account,
                                         @RequestParam(value = "password") String password) {
        return userService.userLogIn(account, password);
    }

    @Secured("ROLE_STUDENT")
    @PutMapping(value = "/student/active")
    public Map<String, String> activateStudent(UserInfo info,
                                               @RequestBody Student student) {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            student.setStudentId(info.getUserId());
            userService.activateStudent(student);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "/teacher/active")
    public Map<String, String> activeTeacher(UserInfo info,
                                             @RequestBody Teacher teacher) {
        Map<String, String> message = new HashMap<String, String>(2);
        teacher.setTeacherId(info.getUserId());
        Integer count = userService.activeTeacher(teacher);
        if (count > 0) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Secured({"ROLE_STUDENT", "ROLE_TEACHER"})
    @GetMapping(value = "/user/password")
    public Map<String, String> getPassword(@RequestBody MyUser user) {
        return mailService.sendPassword(user);
    }

    @Secured({"ROLE_STUDENT", "ROLE_TEACHER"})
    @PutMapping(value = "/user/password")
    public Map<String, String> modifyPassword(UserInfo info,
                                              @RequestBody MyUser user) {
        return userService.modifyPassword(info, user);
    }

    @Secured({"ROLE_STUDENT", "ROLE_TEACHER"})
    @PutMapping(value = "/user/email")
    public Map<String, String> modifyEmail(UserInfo info,
                                           @RequestBody MyUser user) {
        return userService.modifyEmail(info, user);
    }

    @Secured({"ROLE_STUDENT", "ROLE_TEACHER"})
    @GetMapping(value = "/user/email")
    public String getMyEmail(UserInfo info) {
        switch (info.getUserType()) {
            case "teacher":
                Teacher teacher = userService.getTeacherById(info.getUserId());
                return teacher.getEmail();
            case "student":
                Student student = userService.getStudentById(info.getUserId());
                return student.getEmail();
            default:
                return "Error";
        }
    }

    @Secured({"ROLE_STUDENT", "ROLE_TEACHER"})
    @GetMapping(value = "/information")
    public Map<String, String> getMyInfo(UserInfo info) {
        return userService.getMyInfo(info);
    }
}
