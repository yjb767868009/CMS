package com.xmu.cms.controller;

import com.xmu.cms.entity.*;
import com.xmu.cms.service.*;
import com.xmu.cms.support.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Map<String, String> modifyPassword(@RequestParam("userId") Integer userId,
                                              @RequestParam("userType") String userType,
                                              @RequestBody MyUser user) {
        return userService.modifyPassword(userId, userType, user);
    }

    @PutMapping(value = "/email")
    public Map<String, String> modifyEmail(@RequestParam("userId") Integer userId,
                                           @RequestParam("userType") String userType,
                                           @RequestBody MyUser user) {
        return userService.modifyEmail(userId, userType, user);
    }


    @GetMapping(value = "/course/{courseId}/rounds")
    public List<Round> getRounds(@PathVariable("courseId") Integer courseId) {
        return seminarService.getRoundsByCourseId(courseId);
    }

    @GetMapping(value = "/course/{courseId}/seminars")
    public List<Seminar> getSeminarsByCourseId(@PathVariable("courseId") Integer courseId) {
        return seminarService.getSeminarsByCourseId(courseId);
    }

    @GetMapping(value = "round/{roundId}/seminars")
    public List<Seminar> getAllSeminarInRound(@PathVariable(value = "roundId") Integer roundId) {
        return seminarService.getAllSeminarInRound(roundId);
    }

    @GetMapping(value = "/seminar/{seminarId}/teams")
    public List<Team> getTeamsInSeminar(@PathVariable("seminarId") Integer seminarId) {
        return teamService.getAllTeamsInSeminar(seminarId);
    }

    @GetMapping(value = "/klassSeminar/{klassSeminarId}/teams")
    public List<Team> getTeamsInKlassSeminar(@PathVariable("klassSeminarId") Integer klassSeminarId) {
        return teamService.getAllTeamsInKlassSeminar(klassSeminarId);
    }

    @GetMapping(value = "/seminar/{seminarId}")
    public Seminar getSeminarById(@PathVariable("seminarId") Integer seminarId) {
        return seminarService.getSeminarBySeminarId(seminarId);
    }

    @GetMapping(value = "/seminar/{seminarId}/attendances")
    public List<Attendance> getAttendancesInSeminar(@PathVariable("seminarId") Integer seminarId) {
        return seminarService.getAttendancesInSeminar(seminarId);
    }

    @GetMapping(value = "/seminar/{seminarId}/klass/{klassId}/presentationFile")
    public Map<String, String> getPresentationFileInKlassSeminar(@PathVariable("seminarId") Integer seminarId,
                                                                 @PathVariable("klassId") Integer klassId) {
        return seminarService.getPresentationFileInKlassSeminar(seminarId);
    }

    @GetMapping(value = "/information")
    public Map<String, String> getMyInfo(@RequestParam("userId") Integer userId,
                                         @RequestParam("userType") String userType) {
        return userService.getMyInfo(userId, userType);
    }
}
