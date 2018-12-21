package com.xmu.cms.controller;

import com.xmu.cms.aspect.annoatation.CheckTeamPermission;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Team;
import com.xmu.cms.service.CourseService;
import com.xmu.cms.support.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/12/20.
 * @version 1.0
 */
@RestController
public class TeamController {

    @Autowired
    private CourseService courseService;

    @Secured("ROLE_STUDENT")
    @PostMapping(value = "/course/{courseId}/class/{classId}/team")
    public Team newTeam(UserInfo userInfo, @PathVariable("courseId") BigInteger courseId, @PathVariable("classId") BigInteger classId, @RequestBody Team team) {
        return courseService.newTeam(courseId, classId, userInfo.getUserId(), team);
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/team/{teamId}")
    public Team getTeam(@PathVariable("teamId") BigInteger teamId) {
        return courseService.getTeamByTeamId(teamId);
    }

    @Secured("ROLE_STUDENT")
    @CheckTeamPermission
    @DeleteMapping(value = "/team/{teamId}")
    public Map<String, String> deleteTeam(@PathVariable("teamId") BigInteger teamId) {
        return courseService.deleteTeam(teamId);
    }

    @Secured("ROLE_STUDENT")
    @CheckTeamPermission
    @PutMapping(value = "/team/{teamId}/add")
    public Map<String, String> addTeamMember(@PathVariable("teamId") BigInteger teamId,
                                             @RequestBody List<Student> students) {
        return courseService.teamAddMembers(teamId, students);
    }

    @Secured("ROLE_STUDENT")
    @CheckTeamPermission
    @PutMapping(value = "/team/{teamId}/remove")
    public Map<String, String> removeTeamMember(@PathVariable("teamId") BigInteger teamId,
                                                @RequestBody Student student) {
        return courseService.teamRemoveMember(teamId, student);
    }
}