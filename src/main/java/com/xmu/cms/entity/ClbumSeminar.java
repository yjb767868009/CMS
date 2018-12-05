package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/12/4.
 * @version 1.0
 */
public class ClbumSeminar {
    private Integer clbumSeminarId;
    private Integer clbumId;
    private Integer seminarId;
    private Integer state;

    public ClbumSeminar(Integer clbumSeminarId, Integer clbumId, Integer seminarId, Integer state) {
        this.clbumSeminarId = clbumSeminarId;
        this.clbumId = clbumId;
        this.seminarId = seminarId;
        this.state = state;
    }

    public Integer getClbumSeminarId() {
        return clbumSeminarId;
    }

    public void setClbumSeminarId(Integer clbumSeminarId) {
        this.clbumSeminarId = clbumSeminarId;
    }

    public Integer getClbumId() {
        return clbumId;
    }

    public void setClbumId(Integer clbumId) {
        this.clbumId = clbumId;
    }

    public Integer getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(Integer seminarId) {
        this.seminarId = seminarId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
