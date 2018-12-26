package com.xmu.cms.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Team {
    private BigInteger teamId;
    private Student leader;
    private List<Student> members;
    private Course course;
    private Klass klass;
    private String teamName;
    private Boolean valid;

    public Team(BigInteger teamId, Student leader, List<Student> members, Course course, Klass klass, String teamName, Boolean valid) {
        this.teamId = teamId;
        this.leader = leader;
        this.members = members;
        this.course = course;
        this.klass = klass;
        this.teamName = teamName;
        this.valid = valid;
    }

    public Team(BigInteger teamId) {
        this.teamId = teamId;
    }

    public Team() {
    }

    public BigInteger getTeamId() {
        return teamId;
    }

    public void setTeamId(BigInteger teamId) {
        this.teamId = teamId;
    }

    public Student getLeader() {
        return leader;
    }

    public void setLeader(Student leader) {
        this.leader = leader;
    }

    public List<Student> getMembers() {
        return members;
    }

    public void setMembers(List<Student> members) {
        this.members = members;
    }

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
