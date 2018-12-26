package com.xmu.cms.dao;

import com.xmu.cms.entity.Admin;
import com.xmu.cms.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author JuboYu on 2018/12/4.
 * @version 1.0
 */
@Component
public class AdminDao {

    @Autowired
    private AdminMapper adminMapper;

    public Admin getAdminByAccount(String account) {
        return adminMapper.getAdminByAccount(account);
    }
}
