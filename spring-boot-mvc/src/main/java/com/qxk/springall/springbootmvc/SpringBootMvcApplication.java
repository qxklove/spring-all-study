package com.qxk.springall.springbootmvc;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@EnableJpaRepositories
@EnableCaching
@SpringBootApplication
public class SpringBootMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMvcApplication.class, args);
    }

    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonBuilderCustomizer() {
        //支持缩进，浏览器输命令 curl http://localhost:8080/coffee/1 可查看效果
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.indentOutput(true);
    }
}
