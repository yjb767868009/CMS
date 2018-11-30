package com.xmu.cms.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JuboYu on 2018/11/30.
 * @version 1.0
 */
class Permission {
    static Object checkPermission(ProceedingJoinPoint point, String permissionType) throws Throwable {
        Object result = null;
        List<String> noLogIn = new ArrayList<String>();
        noLogIn.add("NoLogIn");
        result = noLogIn;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();
        Object userTypeSession = session.getAttribute("userType");
        Object userIdSession = session.getAttribute("userId");
        if (userTypeSession != null && userIdSession != null) {
            String userType = userTypeSession.toString();
            if (userType.equals(permissionType)) {
                result = point.proceed();
            }
        }
        return result;
    }
}
