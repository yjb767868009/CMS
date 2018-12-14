package com.xmu.cms.config;

import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.mapper.StudentMapper;
import com.xmu.cms.mapper.TeacherMapper;
import com.xmu.cms.support.UserInfo;
import com.xmu.cms.support.UsernameIsExitedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JuboYu on 2018/12/14.
 * @version 1.0
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Teacher teacher = teacherMapper.getTeacherByAccount(username);
        if (null != teacher) {
            authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
            if (!password.equals(teacher.getPassword())) {
                throw new UsernameIsExitedException("密码错误");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(teacher.getAccount(), passwordEncoder.encode(teacher.getPassword()), authorities);
            authenticationToken.setDetails(new UserInfo(teacher.getTeacherId(), teacher.getAccount(), "admin"));
            return authenticationToken;
        }
        Student student = studentMapper.getStudentByAccount(username);
        if (null != student) {
            authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
            if (!password.equals(student.getPassword())) {
                throw new UsernameIsExitedException("密码错误");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(student.getAccount(), passwordEncoder.encode(student.getPassword()), authorities);
            authenticationToken.setDetails(new UserInfo(student.getStudentId(), student.getAccount(), "student"));
            return authenticationToken;
        }
        throw new UsernameNotFoundException("无此用户");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
