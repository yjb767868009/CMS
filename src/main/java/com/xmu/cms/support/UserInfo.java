package com.xmu.cms.support;

/**
 * @author JuboYu on 2018/12/5.
 * @version 1.0
 */
public class UserInfo {
    private Integer userId;
    private String account;
    private String userType;
    private String name;
    private Boolean active;

    public UserInfo(Integer userId, String account, String userType, String name, Boolean active) {
        this.userId = userId;
        this.account = account;
        this.userType = userType;
        this.name = name;
        this.active = active;
    }

    public UserInfo(Integer userId, String account, String userType, String name) {
        this.userId = userId;
        this.account = account;
        this.userType = userType;
        this.name = name;
    }

    public UserInfo(Integer userId, String userType) {
        this.userId = userId;
        this.userType = userType;
    }

    public UserInfo() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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
        return this.userType != null && !this.userType.equals("") && this.userId != null && !(this.userId <= 0);
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
