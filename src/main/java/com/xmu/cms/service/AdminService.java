package com.xmu.cms.service;

import com.xmu.cms.entity.Admin;

import java.util.Map;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */

public interface AdminService {
    Map<String, String> adminLogIn(Admin admin);
}
