package com.xmu.cms.controller;

import com.xmu.cms.entity.ShareSeminar;
import com.xmu.cms.entity.ShareTeam;
import com.xmu.cms.entity.TeamApplication;
import com.xmu.cms.service.CourseService;
import com.xmu.cms.service.MailService;
import com.xmu.cms.support.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/12/23.
 * @version 1.0
 */
@RestController
public class RequestController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private MailService mailService;

    @Secured("ROLE_TEACHER")
    @GetMapping(value = "/request/teamshare")
    public List<ShareTeam> getShareTeam(UserInfo info) {
        return courseService.getShareTeamByTeacherId(info.getUserId());
    }

    @Secured("ROLE_TEACHER")
    @GetMapping(value = "/request/seminarshare")
    public List<ShareSeminar> getShareSeminar(UserInfo info) {
        return courseService.getShareSeminarByTeacherId(info.getUserId());
    }

    @Secured("ROLE_TEACHER")
    @GetMapping(value = "/request/teamvalid")
    public List<TeamApplication> getTeamApplication(UserInfo info) {
        return courseService.getTeamApplicationByTeacherId(info.getUserId());
    }

    @Secured("ROLE_TEACHER")
    @GetMapping(value = "/request/teamshare/{teamShareId}")
    public Map<String, String> updateShareTeam(@PathVariable("teamshareid") BigInteger shareTeamId,
                                               @RequestBody ShareTeam shareTeam) {
        shareTeam.setShareTeamId(shareTeamId);
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            ShareTeam newShareTeam = courseService.updateShareTeam(shareTeam);
            mailService.sendUpdateShareTeam(newShareTeam);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "/request/seminarshare/{seminarshareId}")
    public Map<String, String> updateShareSeminar(UserInfo info,
                                                  @PathVariable("seminarshareId") BigInteger shareSeminarId,
                                                  @RequestBody ShareSeminar shareSeminar) {
        shareSeminar.setShareSeminarId(shareSeminarId);
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            ShareSeminar newShareSeminar = courseService.updateShareSeminar(shareSeminar);
            mailService.sendUpdateShareSeminar(newShareSeminar);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "/request/teamvalid/{teamvalidId}")
    public Map<String, String> updateTeamApplication(UserInfo info,
                                                     @PathVariable("teamvalidId") BigInteger teamApplicationId,
                                                     @RequestBody TeamApplication teamApplication) {
        teamApplication.setTeamApplicationId(teamApplicationId);
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            TeamApplication newTeamApplication = courseService.updateTeamApplication(teamApplication);
            mailService.sendUpdateTeamApplication(newTeamApplication);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

}
