package com.qxk.springall.springbootconfig.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author laijianzhen
 * @date 2021/07/08
 */

@Configuration
@ConfigurationProperties(prefix = "qxk.custom")
@PropertySource("classpath:custom.properties")
public class CustomConfigBean {
    private String config1;
    private String config2;

    public String getConfig1() {
        return config1;
    }

    public void setConfig1(String config1) {
        this.config1 = config1;
    }

    public String getConfig2() {
        return config2;
    }

    public void setConfig2(String config2) {
        this.config2 = config2;
    }
}
