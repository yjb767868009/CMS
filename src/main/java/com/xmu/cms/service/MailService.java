package com.xmu.cms.service;

import java.util.Map;

/**
 * @author JuboYu on 2018/12/5.
 * @version 1.0
 */
public interface MailService {
    Map<String,String> sendEmailByAccount(String account);

    Map<String, String> sendPassword(String account);
}
