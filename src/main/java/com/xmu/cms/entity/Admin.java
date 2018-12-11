package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/23.
 * @version 1.0
 */
public class Admin {
    private Integer adminId;
    private String account;
    private String password;

    public Admin(Integer adminId, String account, String password) {
        this.adminId = adminId;
        this.account = account;
        this.password = password;
    }

    public Admin() {
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
