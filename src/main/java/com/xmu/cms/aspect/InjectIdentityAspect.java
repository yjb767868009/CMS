package com.xmu.cms.aspect;

import com.xmu.cms.support.JWTUntil;
import com.xmu.cms.support.UserInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * @author JuboYu on 2018/12/14.
 * @version 1.0
 */
@Aspect
@Configuration
public class InjectIdentityAspect {
    @Around(value = "execution(* com.xmu.cms.controller.*.*(com.xmu.cms.support.UserInfo,..))&&args(info,..)")
    private Object injectUserInfoPointcut(ProceedingJoinPoint point, UserInfo info) throws Throwable {
        UserInfo userInfo = JWTUntil.getToken();
        Object[] args = point.getArgs();
        args[0] = userInfo;
        return point.proceed(args);
    }
}
