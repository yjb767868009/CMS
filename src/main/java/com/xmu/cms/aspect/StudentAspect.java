package com.xmu.cms.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Aspect
@Component
public class StudentAspect {

    @Around("@annotation(com.xmu.cms.aspect.CheckStudentPermission)")
    public Object checkStudentPermission(ProceedingJoinPoint point) throws Throwable {
        return Permission.checkPermission(point, "student");
    }
}
