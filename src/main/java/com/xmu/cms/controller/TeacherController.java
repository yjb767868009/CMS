package com.xmu.cms.controller;

import com.xmu.cms.entity.Teacher;
import com.xmu.cms.service.CourseService;
import com.xmu.cms.service.FileService;
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
@RequestMapping(value = "/teacher")
public class TeacherController {
    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private FileService fileService;

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "")
    public List<Teacher> getAllTeachers() {
        return userService.getAllTeachers();
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(value = "")
    public Map<String, String> newTeacher(@RequestBody Teacher teacher) {
        return userService.createTeacher(teacher);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/{teacherId}/information")
    public Map<String, String> modifyTeacherInfo(@PathVariable("teacherId") BigInteger teacherId,
                                                 @RequestBody Teacher teacher) {
        return userService.updateTeacherInfo(teacherId, teacher);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/{teacherId}/password")
    public Map<String, String> modifyTeacherPassword(@PathVariable("teacherId") BigInteger teacherId,
                                                     @RequestBody Teacher teacher) {
        return userService.updateTeacherPassword(teacher);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/{teacherId}")
    public Map<String, String> deleteTeacher(@PathVariable("teacherId") BigInteger teacherId) {
        return userService.deleteTeacher(teacherId);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/searchByName")
    public List<Teacher> getTeacherByName(@RequestParam("name") String name) {
        return userService.getTeacherByName(name);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/searchByAccount")
    public Teacher getTeacherByAccount(@RequestParam("account") String account) {
        return userService.getTeacherByAccount(account);
    }

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "/active")
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

}
