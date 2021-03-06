package com.xmu.cms.config;

import com.xmu.cms.dao.AdminDao;
import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.entity.Admin;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.support.UserInfo;
import com.xmu.cms.support.UsernameIsExitedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JuboYu on 2018/12/12.
 * @version 1.0
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private AdminDao adminDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private StudentDao studentDao;

    /**
     * 验证登录信息,若登陆成功,设置 Authentication
     *
     * @param authentication
     * @return 一个完全经过身份验证的对象，包括凭证。
     * 如果AuthenticationProvider无法支持已通过的身份验证对象的身份验证，则可能返回null。
     * 在这种情况下，将会尝试支持下一个身份验证类的验证提供者。
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        Admin admin = adminDao.getAdminByAccount(username);
        if (null != admin) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            if (!password.equals(admin.getPassword())) {
                throw new UsernameIsExitedException("密码错误");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password, authorities);
            UserInfo info = new UserInfo(admin.getAdminId(), admin.getAccount(), "admin", null, null);
            authenticationToken.setDetails(info);
            return authenticationToken;
        }

        Teacher teacher = teacherDao.getTeacherByAccount(username);
        if (null != teacher) {
            authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
            if (!password.equals(teacher.getPassword())) {
                throw new UsernameIsExitedException("密码错误");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(teacher.getAccount(), teacher.getPassword(), authorities);
            authenticationToken.setDetails(new UserInfo(teacher.getTeacherId(), teacher.getAccount(), "teacher", teacher.getName(), teacher.getActivation()));
            return authenticationToken;
        }

        Student student = studentDao.getStudentByAccount(username);
        if (null != student) {
            authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
            if (!password.equals(student.getPassword())) {
                throw new UsernameIsExitedException("密码错误");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(student.getAccount(), student.getPassword(), authorities);
            authenticationToken.setDetails(new UserInfo(student.getStudentId(), student.getAccount(), "student", student.getName(), student.getActivation()));
            return authenticationToken;
        }

        throw new UsernameIsExitedException("无此用户");
    }

    /**
     * 是否可以提供输入类型的认证服务
     * <p>
     * 如果这个AuthenticationProvider支持指定的身份验证对象，那么返回true。
     * 返回true并不能保证身份验证提供者能够对身份验证类的实例进行身份验证。
     * 它只是表明它可以支持对它进行更深入的评估。身份验证提供者仍然可以从身份验证(身份验证)方法返回null，
     * 以表明应该尝试另一个身份验证提供者。在运行时管理器的运行时，可以选择具有执行身份验证的身份验证提供者。
     *
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
