package com.xmu.cms.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
public interface UserService {

    String userLogIn(String account, String password, HttpSession session);
}
