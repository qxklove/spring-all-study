package com.qxk.springall.springbootconfig.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author laijianzhen
 * @date 2021/07/08
 */

//@Component
@ConfigurationProperties(prefix = "qxk")
public class ConfigBean {

    private String config1;
    private String config2;
    private String config;

    public static String staticConfig;

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

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    //静态变量用set方法来设置配置值，注意没有static修饰
    @Value("${qxk.staticConfig}")
    public void setStaticConfig(String staticConfig) {
        ConfigBean.staticConfig = staticConfig;
    }
}
