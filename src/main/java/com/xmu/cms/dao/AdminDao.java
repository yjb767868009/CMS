package com.xmu.cms.dao;

import com.xmu.cms.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */

@Component
public interface AdminDao {
    Admin getAdminByAccount(String account);
}
