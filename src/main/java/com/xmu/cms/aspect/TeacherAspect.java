package com.xmu.cms.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@org.aspectj.lang.annotation.Aspect
@Component
public class TeacherAspect extends Aspect {

    @Around("@annotation(com.xmu.cms.aspect.CheckTeacherPermission)")
    public Object checkTeacherPermission(ProceedingJoinPoint point) throws Throwable {
        return checkPermission(point, "teacher");
    }
}
