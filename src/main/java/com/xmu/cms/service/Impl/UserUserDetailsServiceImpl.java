package com.xmu.cms.service.Impl;

import com.xmu.cms.mapper.StudentMapper;
import com.xmu.cms.mapper.TeacherMapper;
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
    private TeacherMapper teacherDao;

    @Autowired
    private StudentMapper studentMapper;

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
        Student student = studentMapper.getStudentByAccount(username);
        if (null != student) {
            authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
            return new User(student.getAccount(), passwordEncoder.encode(student.getPassword()), authorities);
        }
        throw new UsernameNotFoundException("No this account");
    }
}