package com.xmu.cms.dao;

import com.xmu.cms.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */

@Mapper
public interface AdminDao {
    /**
     * search password by account
     *
     * @param account admin's account
     * @return String message
     */
    Admin getAdminByAccount(@Param("account") String account);
}
