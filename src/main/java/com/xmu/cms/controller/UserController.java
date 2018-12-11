package com.xmu.cms.controller;

import com.xmu.cms.entity.*;
import com.xmu.cms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

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
                                         @RequestParam(value = "password") String password,
                                         HttpServletResponse response) {
        return userService.userLogIn(account, password);
    }

    @PostMapping(value = "/setJWT")
    public Map<String, String> setJWT(@RequestParam(value = "account") String account,
                                      @RequestParam(value = "password") String password) {
        return userService.userLogIn(account, password);
    }

    @PostMapping(value = "/activation")
    public String userActivation(@RequestParam(value = "password") String password,
                                 @RequestParam(value = "email") String email) {
        return "Success";
    }

    @PostMapping(value = "/getVerificationCode")
    public Map<String, String> getVerificationCode(@RequestParam(value = "account") String account) {
        return mailService.sendEmailByAccount(account);
    }

    @PostMapping(value = "/checkVerificationCode")
    public Map<String, String> checkVerificationCode(@RequestParam("account") String account,
                                                     @RequestParam("verificationCode") String verificationCode) {
        return null;
    }

    @PostMapping(value = "/resetPassword")
    public String resetPassword(@RequestParam(value = "password") String password) {
        return "Success";
    }

    @PostMapping(value = "/modifyEmail")
    public String modifyEmail(@RequestParam(value = "email") String email) {
        return "Success";
    }

    @PatchMapping(value = "/modifyMessageInterval")
    public String modifyMessageInterval(@RequestParam(value = "messageInterval") Integer messageInterval) {
        return "Success";
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

    @GetMapping(value = "/myInfo")
    public Map<String, String> getMyInfo(HttpServletRequest request) {
        Cookie[] cookies= request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Token")) {
                String token = cookie.getValue();
            }
        }
        return userService.getMyInfo();
    }
}
