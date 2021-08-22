package com.qxk.springall.springbootrestful;

import com.qxk.springall.springbootrestful.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringBootRestfulApplication implements ApplicationRunner {
    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(SpringBootRestfulApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE) //不会再启动tomcat了
                .run(args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return new RestTemplate();
        return builder.build();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080/coffee/{id}")
                .build(1);

        ResponseEntity<Coffee> c = restTemplate.getForEntity(uri, Coffee.class);
        log.info("Response Status: {}, Response Headers: {}", c.getStatusCode(), c.getHeaders().toString());
        log.info("Coffee: {}", c.getBody());

        //设置了header，然后用restTemplate.exchange()方法请求
        RequestEntity<Void> req = RequestEntity.get(uri)
                .accept(MediaType.APPLICATION_XML)
                .build();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);
        log.info("Response Status: {}, Response Headers: {}", resp.getStatusCode(), resp.getHeaders().toString());
        log.info("Coffee: {}", resp.getBody());

        String coffeeUri = "http://localhost:8080/coffee/";
        Coffee request = Coffee.builder()
                .name("Americano")
                .price(Money.of(CurrencyUnit.of("CNY"), 25.00))
                .build();
        Coffee response = restTemplate.postForObject(coffeeUri, request, Coffee.class);
        log.info("New Coffee: {}", response);

        //获取全部coffee
        String s = restTemplate.getForObject(coffeeUri, String.class);
        log.info("String: {}", s);

        //获取全部coffee，返回解析泛型对象
        ParameterizedTypeReference<List<Coffee>> ptr =
                new ParameterizedTypeReference<List<Coffee>>() {};
        ResponseEntity<List<Coffee>> list = restTemplate
                .exchange(coffeeUri, HttpMethod.GET, null, ptr);
        list.getBody().forEach(coffee -> log.info("Coffee: {}", coffee));
    }

}
