package com.xmu.cms.controller;

import com.xmu.cms.entity.Admin;
import com.xmu.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author JuboYu on 2018/11/23.
 * @version 1.0
 */
@RestController
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/admin/login")
    public Map<String, String> adminLogIn(@RequestBody Admin admin) {
        return userService.adminLogIn(admin);
    }

}
