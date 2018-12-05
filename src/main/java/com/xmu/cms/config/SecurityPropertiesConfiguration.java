package com.xmu.cms.config;

import com.xmu.cms.support.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author JuboYu on 2018/12/3.
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityPropertiesConfiguration {
}
