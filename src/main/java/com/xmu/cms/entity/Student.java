package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/23.
 * @version 1.0
 */
public class Student {
    private Integer studentId;
    private String account;
    private String password;
    private String email;
    private String name;
    private String phone;
    private Integer messageInterval;
    private Boolean activation;

    public Student(Integer studentId, String account, String password, String email, String name, String phone, Integer messageInterval, Boolean activation) {
        this.studentId = studentId;
        this.account = account;
        this.password = password;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.messageInterval = messageInterval;
        this.activation = activation;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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

    public Integer getMessageInterval() {
        return messageInterval;
    }

    public void setMessageInterval(Integer messageInterval) {
        this.messageInterval = messageInterval;
    }

    public Boolean getActivation() {
        return activation;
    }

    public void setActivation(Boolean activation) {
        this.activation = activation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
