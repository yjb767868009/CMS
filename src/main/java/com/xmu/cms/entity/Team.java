package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
public class Team {
    private Integer teamId;
    private Integer leaderId;
    private Integer clbumId;
    private Integer teamOrder;
    private String teamName;
    private Boolean valid;

    public Team(Integer teamId, Integer leaderId, Integer clbumId, Integer teamOrder, String teamName, Boolean valid) {
        this.teamId = teamId;
        this.leaderId = leaderId;
        this.clbumId = clbumId;
        this.teamOrder = teamOrder;
        this.teamName = teamName;
        this.valid = valid;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public String getTeamName() {
        return teamName;
    }

    public Integer getClbumId() {
        return clbumId;
    }

    public void setClbumId(Integer clbumId) {
        this.clbumId = clbumId;
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

    public Integer getTeamOrder() {
        return teamOrder;
    }

    public void setTeamOrder(Integer teamOrder) {
        this.teamOrder = teamOrder;
    }
}
