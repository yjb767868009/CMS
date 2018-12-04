package com.xmu.cms.controller;

import com.xmu.cms.entity.*;
import com.xmu.cms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    private JavaMailSender mailSender;

    @Autowired
    private RoundService roundService;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private AttendanceService attendanceService;

    private ScoreService scoreService;

    @Value("${spring.mail.username}")
    private String sender;

    @PostMapping(value = "/login")
    public Map<String, String> userLogIn(@RequestParam(value = "account") String account,
                                         @RequestParam(value = "password") String password) {
        return userService.userLogIn(account, password);
    }

    @PostMapping(value = "/activation")
    public String userActivation(@RequestParam(value = "password") String password,
                                 @RequestParam(value = "email") String email) {
        return "Success";
    }

    @PostMapping(value = "/findPassword")
    public void findPassword(@RequestParam(value = "email") String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(email);
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");
        mailSender.send(message);
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

    @GetMapping(value = "/seminar/{seminarId}/clbum/{clbumId}/teams")
    public List<Team> getTeamsInClbumSeminar(@PathVariable("seminarId") Integer seminarId,
                                             @PathVariable("clbumId") Integer clbumId) {
        return teamService.getAllTeamsInClbumSeminar(seminarId, clbumId);
    }

    @GetMapping(value = "/")
    public Attendance getNextAttendance(@PathVariable("seminarId") Integer seminarId) {
        return seminarService.getNextAttendance(seminarId);
    }

    @GetMapping(value = "/seminar/{seminarId}")
    public Seminar getSeminarById(@PathVariable("seminarId") Integer seminarId) {
        return seminarService.getSeminarBySeminarId(seminarId);
    }

    @GetMapping(value = "/seminar/{seminarId}/attendances")
    public List<Attendance> getAttendancesInSeminar(@PathVariable("seminarId") Integer seminarId) {
        return attendanceService.getAttendancesInSeminar(seminarId);
    }

    @GetMapping(value = "/seminar/{seminarId}/clbum/{clbumId}/scores")
    public Score getAllScoreInSeminar(@PathVariable("seminarId") Integer seminarId,
                                      @PathVariable("clbumId") Integer clbumId) {
        return scoreService.getAllScoresInClbumSeminar(seminarId, clbumId);
    }
}
