package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.AdminDao;
import com.xmu.cms.entity.Admin;
import com.xmu.cms.service.AdminService;
import com.xmu.cms.support.Token;
import com.xmu.cms.support.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Map<String, String> adminLogIn(Admin admin) {
        Map<String, String> message = new HashMap<String, String>(2);
        Admin findAdmin = adminDao.getAdminByAccount(admin.getAccount());
        if (findAdmin == null) {
            message.put("message", "No this account");
        } else {
            if (findAdmin.getPassword().equals(admin.getPassword())) {
                message.put("message", "Success");
            } else {
                message.put("message", "Account or Password error");
            }
        }
        return message;
    }
}
