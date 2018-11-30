package com.xmu.cms.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JuboYu on 2018/11/28.
 * @version 1.0
 */
@Aspect
@Component
public class AdminAspect {

    @Around("@annotation(com.xmu.cms.aspect.CheckAdminPermission)")
    public Object checkAdminPermission(ProceedingJoinPoint point) throws Throwable {
        System.out.println("Check admin permission");
        return Permission.checkPermission(point, "admin");
    }
}
