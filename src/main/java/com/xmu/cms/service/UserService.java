package com.xmu.cms.service;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
public interface UserService {

    Map<String, String> userLogIn(String account, String password, HttpSession session);
}
