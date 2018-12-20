package com.xmu.cms.support;

import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Teacher;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/12/15.
 * @version 1.0
 */
public class MyUser {
    private BigInteger id;
    private String account;
    private String password;
    private String email;
    private String name;
    private Boolean activation;

    public MyUser(BigInteger id, String account, String password, String email, String name, Boolean activation) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.email = email;
        this.name = name;
        this.activation = activation;
    }

    public MyUser() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
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

    public Boolean getActivation() {
        return activation;
    }

    public void setActivation(Boolean activation) {
        this.activation = activation;
    }

    public Student toStudent() {
        return new Student(id, account, password, email, name, activation);
    }

    public Teacher toTeacher() {
        return new Teacher(id, account, password, email, name, activation, null);
    }
}
