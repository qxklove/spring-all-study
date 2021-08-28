package com.qxk.springall.springbootautoconfigure;

import com.qxk.springall.springboothello.GreetingApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootAutoconfigureApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAutoconfigureApplication.class, args);
    }

    /**
     * 这里自己注入Bean就不会去注入自动配置里的Bean了
     * */
//    @Bean
//    public GreetingApplicationRunner greetingApplicationRunner() {
//        return new GreetingApplicationRunner("curry");
//    }
}
