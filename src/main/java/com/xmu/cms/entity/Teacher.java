package com.xmu.cms.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/11/23.
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Teacher {
    private BigInteger teacherId;
    private String account;
    private String password;
    private String email;
    private String name;
    private Boolean activation;
    private List<Course> courses;

    public Teacher(BigInteger teacherId, String account, String password, String email, String name, Boolean activation, List<Course> courses) {
        this.teacherId = teacherId;
        this.account = account;
        this.password = password;
        this.email = email;
        this.name = name;
        this.activation = activation;
        this.courses = courses;
    }

    public Teacher(BigInteger teacherId) {
        this.teacherId = teacherId;
    }

    public Teacher() {
    }

    public BigInteger getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(BigInteger teacherId) {
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

    public Boolean getActivation() {
        return activation;
    }

    public void setActivation(Boolean activation) {
        this.activation = activation;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
