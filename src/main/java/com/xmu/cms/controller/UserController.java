package com.xmu.cms.controller;

import com.xmu.cms.entity.*;
import com.xmu.cms.service.*;
import com.xmu.cms.support.MyUser;
import com.xmu.cms.support.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private RoundService roundService;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private MailService mailService;

    @PostMapping(value = "/login")
    public Map<String, String> userLogIn(@RequestParam(value = "account") String account,
                                         @RequestParam(value = "password") String password) {
        return userService.userLogIn(account, password);
    }

    @GetMapping(value = "/password")
    public Map<String, String> getPassword(@RequestParam(value = "account") String account) {
        return mailService.sendPassword(account);
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

    @GetMapping(value = "/course")
    public List<Course> getCourses() {
        return null;
    }

    @GetMapping(value = "/course/{courseId}")
    public Course getCourse(@PathVariable("courseId") Integer courseId) {
        return courseService.getCourse(courseId);
    }

    @GetMapping(value = "/course/{courseId}/rounds")
    public List<Round> getRounds(@PathVariable("courseId") Integer courseId) {
        return roundService.getRoundsByCourseId(courseId);
    }

    @GetMapping(value = "/course/{courseId}/seminars")
    public List<Seminar> getSeminarsByCourseId(@PathVariable("courseId") Integer courseId) {
        return seminarService.getSeminarsByCourseId(courseId);
    }

    @GetMapping(value = "round/{roundId}/seminars")
    public List<Seminar> getAllSeminarInRound(@PathVariable(value = "roundId") Integer roundId) {
        return roundService.getAllSeminarInRound(roundId);
    }

    @GetMapping(value = "/seminar/{seminarId}/teams")
    public List<Team> getTeamsInSeminar(@PathVariable("seminarId") Integer seminarId) {
        return teamService.getAllTeamsInSeminar(seminarId);
    }

    @GetMapping(value = "/clbumSeminar/{clbumSeminarId}/teams")
    public List<Team> getTeamsInClbumSeminar(@PathVariable("clbumSeminarId") Integer clbumSeminarId) {
        return teamService.getAllTeamsInClbumSeminar(clbumSeminarId);
    }

    @GetMapping(value = "/seminar/{seminarId}")
    public Seminar getSeminarById(@PathVariable("seminarId") Integer seminarId) {
        return seminarService.getSeminarBySeminarId(seminarId);
    }

    @GetMapping(value = "/seminar/{seminarId}/attendances")
    public List<Attendance> getAttendancesInSeminar(@PathVariable("seminarId") Integer seminarId) {
        return attendanceService.getAttendancesInSeminar(seminarId);
    }

    @GetMapping(value = "/clbumSeminar/{clbumSeminarId}/scores")
    public Score getAllScoreInSeminar(@PathVariable("clbumSeminarId") Integer clbumSeminarId) {
        return scoreService.getAllScoresInClbumSeminar(clbumSeminarId);
    }

    @GetMapping(value = "/seminar/{seminarId}/clbum/{clbumId}/presentationFile")
    public Map<String, String> getPresentationFileInClbumSeminar(@PathVariable("seminarId") Integer seminarId,
                                                                 @PathVariable("clbumId") Integer clbumId) {
        return seminarService.getPresentationFileInClbumSeminar(seminarId);
    }

    @GetMapping(value = "/information")
    public Map<String, String> getMyInfo(@RequestParam("userId") Integer userId,
                                         @RequestParam("userType") String userType) {
        return userService.getMyInfo(userId, userType);
    }
}
