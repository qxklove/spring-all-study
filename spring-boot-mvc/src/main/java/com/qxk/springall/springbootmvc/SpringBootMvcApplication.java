package com.qxk.springall.springbootmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableCaching
@SpringBootApplication
public class SpringBootMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMvcApplication.class, args);
    }

}
