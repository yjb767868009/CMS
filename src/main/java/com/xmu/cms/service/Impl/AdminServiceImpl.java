package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.AdminDao;
import com.xmu.cms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public String adminLogIn(String account, String password) {
        String findPassword = adminDao.getPasswordByAccount(account);
        if (findPassword == null) {
            return "No this account";
        } else {
            if (password.equals(findPassword)) {
                return "Success";
            } else {
                return "Account or Password error";
            }
        }
    }
}
