package com.xmu.cms.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Round {
    private BigInteger roundId;
    private Integer order;
    private Course course;
    private Integer presentationScoreType;
    private Integer reportScoreType;
    private Integer questionScoreType;
    private List<Seminar> seminars;
    private List<KlassEnrollNumber> klassEnrollNumbers;

    public Round(BigInteger roundId, Integer order, Course course, Integer presentationScoreType, Integer reportScoreType, Integer questionScoreType, List<Seminar> seminars, List<KlassEnrollNumber> klassEnrollNumbers) {
        this.roundId = roundId;
        this.order = order;
        this.course = course;
        this.presentationScoreType = presentationScoreType;
        this.reportScoreType = reportScoreType;
        this.questionScoreType = questionScoreType;
        this.seminars = seminars;
        this.klassEnrollNumbers = klassEnrollNumbers;
    }

    public Round() {
    }

    public BigInteger getRoundId() {
        return roundId;
    }

    public void setRoundId(BigInteger roundId) {
        this.roundId = roundId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getPresentationScoreType() {
        return presentationScoreType;
    }

    public void setPresentationScoreType(Integer presentationScoreType) {
        this.presentationScoreType = presentationScoreType;
    }

    public Integer getReportScoreType() {
        return reportScoreType;
    }

    public void setReportScoreType(Integer reportScoreType) {
        this.reportScoreType = reportScoreType;
    }

    public Integer getQuestionScoreType() {
        return questionScoreType;
    }

    public void setQuestionScoreType(Integer questionScoreType) {
        this.questionScoreType = questionScoreType;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<KlassEnrollNumber> getKlassEnrollNumbers() {
        return klassEnrollNumbers;
    }

    public void setKlassEnrollNumbers(List<KlassEnrollNumber> klassEnrollNumbers) {
        this.klassEnrollNumbers = klassEnrollNumbers;
    }

    public List<Seminar> getSeminars() {
        return seminars;
    }

    public void setSeminars(List<Seminar> seminars) {
        this.seminars = seminars;
    }
}
