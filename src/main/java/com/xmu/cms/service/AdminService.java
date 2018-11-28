package com.xmu.cms.service;

import com.xmu.cms.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */

public interface AdminService {
    String adminLogIn(String account, String password, HttpSession session);
}
