package com.xmu.cms.service;

import com.xmu.cms.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
@Service
public interface AdminService {

    public String adminLogIn(String account,String password);
}
