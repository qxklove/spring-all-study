package com.qxk.springall.springbootactuator.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 *自定义健康信息类
 *
 *@author laijianzhen
 *@date 2021/07/18
 */

@Component
public class MyHealthIndicator implements HealthIndicator {

    private static final String PORT = "80";

    @Value("${server.port}")
    private String serverPort;

    @Override
    public Health health() {
        if (!PORT.equals(serverPort)) {
            return Health.down().withDetail("端口不是80", "是" + serverPort).build();
        }
        return Health.up().build();
    }
}
