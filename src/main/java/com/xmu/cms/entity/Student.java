package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/23.
 * @version 1.0
 */
public class Student {

    private Long id;
    private String account;
    private String password;
    private String email;
    private String name;
    private Integer messageInterval;
    private Integer activation;

    public Student(Long id, String account, String password, String email, String name, Integer messageInterval, Integer activation) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.email = email;
        this.name = name;
        this.messageInterval = messageInterval;
        this.activation = activation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getActivation() {
        return activation;
    }

    public void setActivation(Integer activation) {
        this.activation = activation;
    }
}
