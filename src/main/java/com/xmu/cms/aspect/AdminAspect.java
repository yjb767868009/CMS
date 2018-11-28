package com.xmu.cms.aspect;

import com.xmu.cms.result.JsonResult;
import com.xmu.cms.result.ResultCode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JuboYu on 2018/11/28.
 * @version 1.0
 */
@Aspect
@Component
public class AdminAspect {

    private final static Logger logger = LoggerFactory.getLogger(AdminAspect.class);

    private HttpSession getSession() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        return request.getSession();
    }

    @Pointcut("execution(public * com.xmu.cms.controller.AdminController.*(..))")
    public void checkAdminLog() {
    }

    @Around("@annotation(com.xmu.cms.aspect.CheckPermission)")
    public Object checkPermission(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        List<String> notLogIn = new ArrayList<String>();
        notLogIn.add("NoLogIn");
        HttpSession session = getSession();
        Object userSession = session.getAttribute("userType");
        if (userSession != null) {
            String userType = userSession.toString();
            String adminType = "admin";
            if (userType.equals(adminType)) {
                result = point.proceed();
            } else {
                result = notLogIn;
            }
        } else {
            result = notLogIn;
        }
        return result;
    }
}
