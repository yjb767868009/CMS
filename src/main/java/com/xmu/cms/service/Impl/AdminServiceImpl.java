package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.AdminDao;
import com.xmu.cms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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
    public Map<String, String> adminLogIn(String account, String password, HttpSession session) {
        Map<String, String> message = new HashMap<String, String>(2);
        String findPassword = adminDao.getPasswordByAccount(account);
        if (findPassword == null) {
            message.put("message", "No this account");
        } else {
            if (password.equals(findPassword)) {
                session.setAttribute("userType", "admin");
                session.setAttribute("userId", account);
                message.put("message", "Success");
            } else {
                message.put("message", "Account or Password error");
            }
        }
        return message;
    }
}
