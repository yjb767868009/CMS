package com.xmu.cms.entity;

import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
public class Team {
    private Integer teamId;
    private Student leader;
    private List<Student> members;
    private Clbum clbum;
    private String teamName;
    private Boolean valid;

    public Team(Integer teamId, Student leader, List<Student> members, Clbum clbum, String teamName, Boolean valid) {
        this.teamId = teamId;
        this.leader = leader;
        this.members = members;
        this.clbum = clbum;
        this.teamName = teamName;
        this.valid = valid;
    }

    public Team() {
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
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

    public Clbum getClbum() {
        return clbum;
    }

    public void setClbum(Clbum clbum) {
        this.clbum = clbum;
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
