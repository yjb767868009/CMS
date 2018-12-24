package com.xmu.cms.support;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
public class HelloMessage {
    private String name;
    private String say;

    public HelloMessage(String name, String say) {
        this.name = name;
        this.say = say;
    }

    public HelloMessage() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSay() {
        return say;
    }

    public void setSay(String say) {
        this.say = say;
    }
}
