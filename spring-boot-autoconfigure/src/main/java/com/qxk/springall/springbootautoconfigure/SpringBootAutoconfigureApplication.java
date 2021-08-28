package com.qxk.springall.springbootautoconfigure;

import com.qxk.springall.springboothello.GreetingApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
//这里扫包是配合手工实现的自动配置写的
//@ComponentScan(value = "com.qxk.springall.springbootautoconfigurebackport")
public class SpringBootAutoconfigureApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAutoconfigureApplication.class, args);
    }

    /**
     * 这里自己注入Bean就不会去注入自动配置里的Bean了
     */
//    @Bean
//    public GreetingApplicationRunner greetingApplicationRunner() {
//        return new GreetingApplicationRunner("curry");
//    }
}
