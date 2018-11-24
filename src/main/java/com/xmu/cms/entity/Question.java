package com.xmu.cms.entity;

/**
 * @author Zhengjing Zhang on 2018/11/24.
 * @version 1.0
 */
public class Question {
    private Long id;
    private boolean selected;
    private double score;
    public Question(){
        selected=false;
        score=0;
    }
    public void setID(Long id){
        this.id=id;
    }
    public Long getID(){
        return this.id;
    }
    public void isSelected(){
        selected=true;
    }
    public void notSelected(){
        selected=false;
    }
    public void setScore(double score){
        this.score=score;
    }
    public double get_score(){
        return this.score;
    }
}