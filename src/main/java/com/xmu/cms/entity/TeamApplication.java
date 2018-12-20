package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/12/20.
 * @version 1.0
 */
public class TeamApplication {
    private Integer teamApplicationId;
    private Team team;
    private Teacher teacher;
    private String reason;
    private Integer status;

    public TeamApplication(Integer teamApplicationId, Team team, Teacher teacher, String reason, Integer status) {
        this.teamApplicationId = teamApplicationId;
        this.team = team;
        this.teacher = teacher;
        this.reason = reason;
        this.status = status;
    }

    public TeamApplication() {
    }

    public Integer getTeamApplicationId() {
        return teamApplicationId;
    }

    public void setTeamApplicationId(Integer teamApplicationId) {
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
