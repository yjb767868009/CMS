package com.xmu.cms.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/12/20.
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamApplication {
    private BigInteger teamApplicationId;
    private Team team;
    private Teacher teacher;
    private String reason;
    private Boolean status;

    public TeamApplication(BigInteger teamApplicationId, Team team, Teacher teacher, String reason, Boolean status) {
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
