package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.AdminDao;
import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.entity.Admin;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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
 * @author JuboYu on 2018/12/3.
 * @version 1.0
 */
@Service
public class AdminUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Admin admin = adminDao.getAdminByAccount(username);
        if (null != admin) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new User(admin.getAccount(), passwordEncoder.encode(admin.getPassword()), authorities);
        }
        throw new UsernameNotFoundException("No this account");
    }
}
