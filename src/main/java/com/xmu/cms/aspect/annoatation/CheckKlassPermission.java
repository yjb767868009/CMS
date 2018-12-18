package com.xmu.cms.aspect.annoatation;

import java.lang.annotation.*;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckKlassPermission {
}
