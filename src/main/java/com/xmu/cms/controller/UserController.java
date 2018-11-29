package com.xmu.cms.controller;

import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public String userLogIn(@RequestParam(value = "account") String account,
                            @RequestParam(value = "password") String password,
                            HttpSession session) {
        return userService.userLogIn(account, password, session);
    }

    @PostMapping(value = "/activation")
    public String userActivation(@RequestParam(value = "password") String password,
                                 @RequestParam(value = "email") String email) {
        return "Success";
    }

    @PostMapping(value = "/findPassword")
    public String findPassword(@RequestParam(value = "email") String email) {
        return "Success";
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

}
