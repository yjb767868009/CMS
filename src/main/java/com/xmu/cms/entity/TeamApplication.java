package com.xmu.cms.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/12/20.
 * @version 1.0
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TeamApplication {
    private BigInteger teamApplicationId;
    private Team team;
    private Teacher teacher;
    private String reason;
    private Integer status;

    public TeamApplication(BigInteger teamApplicationId, Team team, Teacher teacher, String reason, Integer status) {
        this.teamApplicationId = teamApplicationId;
        this.team = team;
        this.teacher = teacher;
        this.reason = reason;
        this.status = status;
    }

    public TeamApplication() {
    }

    public BigInteger getTeamApplicationId() {
        return teamApplicationId;
    }

    public void setTeamApplicationId(BigInteger teamApplicationId) {
        this.teamApplicationId = teamApplicationId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
