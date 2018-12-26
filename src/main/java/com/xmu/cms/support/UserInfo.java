package com.xmu.cms.support;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/12/5.
 * @version 1.0
 */
public class UserInfo {
    private BigInteger userId;
    private String account;
    private String userType;
    private String name;
    private Boolean active;

    public UserInfo(BigInteger userId, String account, String userType, String name, Boolean active) {
        this.userId = userId;
        this.account = account;
        this.userType = userType;
        this.name = name;
        this.active = active;
    }

    public UserInfo(BigInteger userId, String account, String userType, String name) {
        this.userId = userId;
        this.account = account;
        this.userType = userType;
        this.name = name;
    }

    public UserInfo(BigInteger userId, String userType) {
        this.userId = userId;
        this.userType = userType;
    }

    public UserInfo() {
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isEmpty() {
        return this.userType != null && !"".equals(this.userType) && this.userId != null;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
