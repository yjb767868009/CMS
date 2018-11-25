package com.xmu.cms.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */
public interface AdminDao {
    /**
     * search password by account
     *
     * @param account admin's account
     * @return String message
     */
    String getPasswordByAccount(@Param("account") String account);
}
