package com.xmu.cms.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {
    @GetMapping(value = "/testSession")
    public String testSession(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("userType") == null) {
            session.setAttribute("userType", "student");
            return "No user login";
        } else {
            return session.getAttribute("userType").toString();
        }

    }

    @GetMapping(value = "/getSession")
    public String getSession(HttpSession session) {
        return session.getId();
    }

    @GetMapping(value = "/testHolder")
    public String testHolder() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();
        return session.getAttribute("userType").toString();
    }
}
