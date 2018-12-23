package com.xmu.cms.aspect;

import com.xmu.cms.dao.CourseDao;
import com.xmu.cms.dao.KlassDao;
import com.xmu.cms.dao.TeamDao;
import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Klass;
import com.xmu.cms.entity.Team;
import com.xmu.cms.support.JWTUtils;
import com.xmu.cms.support.UserInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
@Aspect
@Configuration
public class CheckPermissionAspect {
    @Autowired
    private CourseDao courseDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private KlassDao klassDao;

    @Around(value = "@annotation(com.xmu.cms.aspect.annoatation.CheckCoursePermission)&&args(courseId,..)")
    private Object checkCoursePermission(ProceedingJoinPoint point, BigInteger courseId) throws Throwable {
        Object result = null;
        UserInfo userInfo = JWTUtils.getToken();
        Course course = courseDao.getCourse(courseId);
        if (userInfo.getUserType().equals("teacher") && course.getTeacher().getTeacherId().equals(userInfo.getUserId())) {
            result = point.proceed();
        }
        return result;
    }

    @Around(value = "@annotation(com.xmu.cms.aspect.annoatation.CheckKlassPermission)&&args(klassId,..)")
    private Object checkKlassPermission(ProceedingJoinPoint point, BigInteger klassId) throws Throwable {
        Object result = null;
        UserInfo userInfo = JWTUtils.getToken();
        Klass klass = klassDao.getKlass(klassId);
        if (userInfo.getUserType().equals("teacher") && klass.getCourse().getTeacher().getTeacherId().equals(userInfo.getUserId())) {
            result = point.proceed();
        }
        return result;
    }

    @Around(value = "@annotation(com.xmu.cms.aspect.annoatation.CheckTeamPermission)&&args(teamId,..)")
    private Object checkTeamPermission(ProceedingJoinPoint point, BigInteger teamId) throws Throwable {
        Object result = null;
        UserInfo userInfo = JWTUtils.getToken();
        Team team = teamDao.getTeamByTeamId(teamId);
        if (userInfo.getUserType().equals("teacher") && team.getLeader().getStudentId().equals(userInfo.getUserId())) {
            result = point.proceed();
        }
        return result;
    }
}
