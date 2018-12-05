package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.AdminDao;
import com.xmu.cms.entity.Admin;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author JuboYu on 2018/12/4.
 * @version 1.0
 */
public class AdminDaoImpl implements AdminDao {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public Admin getAdminByAccount(String account) {
        SqlSession sqlSession =sqlSessionFactory.openSession();
        Admin admin = sqlSession.selectOne("admin.findAccountByAccount",account);
        sqlSession.close();
        return admin;
    }
}
