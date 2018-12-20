package com.xmu.cms.entity;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
public class Team {
    private BigInteger teamId;
    private Student leader;
    private List<Student> members;
    private Klass klass;
    private String teamName;
    private Boolean valid;

    public Team(BigInteger teamId, Student leader, List<Student> members, Klass klass, String teamName, Boolean valid) {
        this.teamId = teamId;
        this.leader = leader;
        this.members = members;
        this.klass = klass;
        this.teamName = teamName;
        this.valid = valid;
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
}
