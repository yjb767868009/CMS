package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.AdminDao;
import com.xmu.cms.entity.Admin;
import com.xmu.cms.mapper.AdminMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author JuboYu on 2018/12/4.
 * @version 1.0
 */
@Component
public class AdminDaoImpl implements AdminDao {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin getAdminByAccount(String account) {
        return adminMapper.getAdminByAccount(account);
    }
}
