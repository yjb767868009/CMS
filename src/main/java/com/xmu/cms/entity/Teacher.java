package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/23.
 * @version 1.0
 */
public class Teacher {
    private Integer teacherId;
    private String account;
    private String password;
    private String email;
    private String name;
    private String phone;
    private String sex;
    private Integer messageInterval;
    private Boolean isActivation;

    public Teacher(Integer teacherId, String account, String password, String email, String name, String phone, String sex, Integer messageInterval, Boolean isActivation) {
        this.teacherId = teacherId;
        this.account = account;
        this.password = password;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.messageInterval = messageInterval;
        this.isActivation = isActivation;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getMessageInterval() {
        return messageInterval;
    }

    public void setMessageInterval(Integer messageInterval) {
        this.messageInterval = messageInterval;
    }

    public Boolean getActivation() {
        return isActivation;
    }

    public void setActivation(Boolean activation) {
        isActivation = activation;
    }
}
