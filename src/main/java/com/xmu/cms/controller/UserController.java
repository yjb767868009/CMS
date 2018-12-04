package com.xmu.cms.controller;

import com.xmu.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

}
