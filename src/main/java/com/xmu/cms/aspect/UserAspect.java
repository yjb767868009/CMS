package com.xmu.cms.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author JuboYu on 2018/12/2.
 * @version 1.0
 */
@Aspect
@Component
public class UserAspect {
    @Around("@annotation(com.xmu.cms.aspect.CheckUserPermission)")
    public Object checkTeacherPermission(ProceedingJoinPoint point) throws Throwable {
        return Permission.checkPermission(point, "user");
    }
}
