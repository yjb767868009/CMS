package com.xmu.cms.service;

import com.xmu.cms.support.MyUser;

import java.util.Map;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
public interface UserService {

    Map<String, String> userLogIn(String account, String password);

    Map<String, String> getMyInfo(Integer userId, String userType);

    Map<String, String> modifyEmail(Integer userId, String userType, MyUser user);

    Map<String, String> modifyPassword(Integer userId, String userType, MyUser user);
}
