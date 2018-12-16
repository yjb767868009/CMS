package com.xmu.cms.aspect;

import com.xmu.cms.support.Token;
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
    @Around(value = "execution(* com.xmu.cms.controller.*.*(Integer,String,..))&&args(userId,userType,..)", argNames = "userId,userType")
    private Object injectIdentityPointcut(ProceedingJoinPoint point, Integer userId, String userType) throws Throwable {
        UserInfo userInfo = Token.getToken();
        Object[] args = point.getArgs();
        args[0] = userInfo.getUserId();
        args[1] = userInfo.getUserType();
        return point.proceed(args);
    }


}
