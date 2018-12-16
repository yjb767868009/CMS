package com.xmu.cms.controller;

import com.xmu.cms.entity.Admin;
import com.xmu.cms.entity.Student;
import com.xmu.cms.service.AdminService;
import com.xmu.cms.service.StudentService;
import com.xmu.cms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.xmu.cms.entity.Teacher;

import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/23.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @PostMapping(value = "/login")
    public Map<String, String> adminLogIn(@RequestBody Admin admin) {
        return adminService.adminLogIn(admin);
    }





}
