package com.xmu.cms.controller;

import com.xmu.cms.support.Greeting;
import com.xmu.cms.support.HelloMessage;
import com.xmu.cms.support.UserInfo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/")
public class TestController {

    @GetMapping(value = "/getUserType")
    public Object getUserType(HttpSession session) {
        return session.getAttribute("userType");
    }

    @GetMapping(value = "/getUser")
    public Object getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.toString());
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }

    @GetMapping(value = "/test/myinfo")
    public UserInfo getUserInfo(UserInfo info) {
        return info;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}
