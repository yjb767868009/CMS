package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.AdminDao;
import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.entity.Admin;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JuboYu on 2018/12/4.
 * @version 1.0
 */
@Service
public class UserUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Teacher teacher = teacherDao.getTeacherByAccount(username);
        if (null != teacher) {
            authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
            return new User(teacher.getAccount(), passwordEncoder.encode(teacher.getPassword()), authorities);
        }
        Student student = studentDao.getStudentByAccount(username);
        if (null != student) {
            authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
            return new User(student.getAccount(), passwordEncoder.encode(student.getPassword()), authorities);
        }
        throw new UsernameNotFoundException("No this account");
    }
}