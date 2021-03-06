package com.xmu.cms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author JuboYu on 2018/12/3.
 * @version 1.0
 */
@ConfigurationProperties(prefix = "xmu.cms")
public class SecurityProperties {
    private LoginType loginType = LoginType.JSON;

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
