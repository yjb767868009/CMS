package com.xmu.cms.mapper;

import com.xmu.cms.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author JuboYu on 2018/12/10.
 * @version 1.0
 */
@Mapper
public interface AdminMapper {
    /**
     * search password by account
     *
     * @param account admin's account
     * @return String message
     */
    Admin getAdminByAccount(@Param("account") String account);
}
