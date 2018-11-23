package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/23.
 * @version 1.0
 */
public class Student {

    private String id;
    private String account;
    private String password;
    private String email;
    private String name;
    private boolean activation;
    private int messageInterval;

    public Student(String id, String account, String password, String name) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.name = name;
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

    public boolean isActivation() {
        return activation;
    }

    public void setActivation(boolean activation) {
        this.activation = activation;
    }

    public int getMessageInterval() {
        return messageInterval;
    }

    public void setMessageInterval(int messageInterval) {
        this.messageInterval = messageInterval;
    }
}
