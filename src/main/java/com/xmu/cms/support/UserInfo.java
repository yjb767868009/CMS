package com.xmu.cms.support;

/**
 * @author JuboYu on 2018/12/5.
 * @version 1.0
 */
public class UserInfo {
    private Integer userId;
    private String userName;
    private String userType;

    public UserInfo(Integer userId, String userName, String userType) {
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
