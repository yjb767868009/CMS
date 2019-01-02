package com.xmu.cms.entity;

/**
 * @author JuboYu on 2019/1/2.
 * @version 1.0
 */
public class KlassEnrollNumber {
    private Klass klass;
    private Integer enrollNumber;

    public KlassEnrollNumber(Klass klass, Integer enrollNumber) {
        this.klass = klass;
        this.enrollNumber = enrollNumber;
    }

    public KlassEnrollNumber() {
    }

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }

    public Integer getEnrollNumber() {
        return enrollNumber;
    }

    public void setEnrollNumber(Integer enrollNumber) {
        this.enrollNumber = enrollNumber;
    }
}

