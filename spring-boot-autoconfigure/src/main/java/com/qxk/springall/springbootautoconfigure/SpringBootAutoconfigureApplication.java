package com.qxk.springall.springbootautoconfigure;

import com.qxk.springall.springboothello.GreetingApplicationRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Slf4j
@SpringBootApplication
//这里扫包是配合手工实现的自动配置写的
//@ComponentScan(value = "com.qxk.springall.springbootautoconfigurebackport")
public class SpringBootAutoconfigureApplication implements ApplicationRunner {
    @Value("${config.greeting}")
    private String greeting;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAutoconfigureApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("greeting:{}", greeting);
    }

    /**
     * 这里自己注入Bean就不会去注入自动配置里的Bean了
     */
//    @Bean
//    public GreetingApplicationRunner greetingApplicationRunner() {
//        return new GreetingApplicationRunner("curry");
//    }
}
