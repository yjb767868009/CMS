package com.xmu.cms.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Aspect
@Component
public class TeacherAspect {

    @Around("@annotation(com.xmu.cms.aspect.CheckTeacherPermission)")
    public Object checkTeacherPermission(ProceedingJoinPoint point) throws Throwable {
        return Permission.checkPermission(point, "teacher");
    }
}
