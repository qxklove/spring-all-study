package com.qxk.springall.springcloudconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringCloudConfigServerApplication {

    // git上配置文件名称是waiter-service.yml，waiter-service-dev.yml
    // 访问http://localhost:8888/waiter-service.yml，http://localhost:8888/waiter-service/dev
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigServerApplication.class, args);
    }

}
