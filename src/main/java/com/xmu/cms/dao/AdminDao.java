package com.xmu.cms.dao;

import com.xmu.cms.entity.Admin;
import org.springframework.stereotype.Component;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */

@Component
public interface AdminDao {
    /**
     * 根据管理员账户获取管理员对象
     *
     * @param account 账户
     * @return 管理员
     */
    Admin getAdminByAccount(String account);
}
