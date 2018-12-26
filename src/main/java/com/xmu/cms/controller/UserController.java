package com.xmu.cms.controller;

import com.xmu.cms.entity.Seminar;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.entity.Team;
import com.xmu.cms.service.*;
import com.xmu.cms.support.MyUser;
import com.xmu.cms.support.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MailService mailService;

    @PostMapping(value = "/login")
    public Map<String, String> userLogIn(@RequestParam(value = "account") String account,
                                         @RequestParam(value = "password") String password) {
        return userService.userLogIn(account, password);
    }

    @GetMapping(value = "/password")
    public Map<String, String> getPassword(@RequestBody MyUser user) {
        return mailService.sendPassword(user);
    }

    @PutMapping(value = "/password")
    public Map<String, String> modifyPassword(UserInfo info,
                                              @RequestBody MyUser user) {
        return userService.modifyPassword(info, user);
    }

    @PutMapping(value = "/email")
    public Map<String, String> modifyEmail(UserInfo info,
                                           @RequestBody MyUser user) {
        return userService.modifyEmail(info, user);
    }

    @Secured({"ROLE_STUDENT", "ROLE_TEACHER"})
    @GetMapping(value = "/email")
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

    @GetMapping(value = "/course/{courseId}/seminars")
    public List<Seminar> getSeminarsByCourseId(@PathVariable("courseId") BigInteger courseId) {
        return seminarService.getSeminarsByCourseId(courseId);
    }

    @GetMapping(value = "round/{roundId}/seminars")
    public List<Seminar> getAllSeminarInRound(@PathVariable(value = "roundId") BigInteger roundId) {
        return seminarService.getAllSeminarInRound(roundId);
    }

    @GetMapping(value = "/seminar/{seminarId}/teams")
    public List<Team> getTeamsInSeminar(@PathVariable("seminarId") BigInteger seminarId) {
        return teamService.getAllTeamsInSeminar(seminarId);
    }

    @GetMapping(value = "/seminar/{seminarId}")
    public Seminar getSeminarById(@PathVariable("seminarId") BigInteger seminarId) {
        return seminarService.getSeminarBySeminarId(seminarId);
    }

    @GetMapping(value = "/information")
    public Map<String, String> getMyInfo(UserInfo info) {
        return userService.getMyInfo(info);
    }
}
