package com.xmu.cms.entity;

import java.util.Map;

/**
 * @author JuboYu on 2018/12/2.
 * @version 1.0
 */
public class Message {
    private String message;
    private String activation;

    public Message(String message) {
        this.message = message;
        this.activation="";
    }

    public Message(String message, String activation) {
        this.message = message;
        this.activation = activation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }
}
