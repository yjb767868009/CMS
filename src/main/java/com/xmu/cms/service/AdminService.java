package com.xmu.cms.service;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */

public interface AdminService {
    Map<String, String> adminLogIn(String account, String password);
}
