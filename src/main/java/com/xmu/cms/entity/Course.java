package com.xmu.cms.entity;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
public class Course {
    private Integer courseId;
    private String name;
    private String introduction;
    private Float presentationProportion;
    private Float reportProportion;
    private Float questionProportion;

    public Course(Integer courseId, String name, String introduction, Float presentationProportion, Float reportProportion, Float questionProportion) {
        this.courseId = courseId;
        this.name = name;
        this.introduction = introduction;
        this.presentationProportion = presentationProportion;
        this.reportProportion = reportProportion;
        this.questionProportion = questionProportion;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Float getPresentationProportion() {
        return presentationProportion;
    }

    public void setPresentationProportion(Float presentationProportion) {
        this.presentationProportion = presentationProportion;
    }

    public Float getReportProportion() {
        return reportProportion;
    }

    public void setReportProportion(Float reportProportion) {
        this.reportProportion = reportProportion;
    }

    public Float getQuestionProportion() {
        return questionProportion;
    }

    public void setQuestionProportion(Float questionProportion) {
        this.questionProportion = questionProportion;
    }
}
