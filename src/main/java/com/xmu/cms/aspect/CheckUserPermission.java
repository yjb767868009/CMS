package com.xmu.cms.aspect;

import java.lang.annotation.*;

/**
 * @author JuboYu on 2018/12/2.
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckUserPermission {
}
