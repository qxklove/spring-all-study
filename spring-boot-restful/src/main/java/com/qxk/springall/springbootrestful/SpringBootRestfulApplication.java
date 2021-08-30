package com.qxk.springall.springbootrestful;

import com.qxk.springall.springbootrestful.model.Coffee;
import com.qxk.springall.springbootrestful.support.CustomConnectionKeepAliveStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.SSLContext;
import java.math.BigDecimal;
import java.net.URI;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootApplication
public class SpringBootRestfulApplication
//        implements ApplicationRunner
{
    @Value("${security.key-store}")
    private Resource keyStore;
    @Value("${security.key-pass}")
    private String keyPass;

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(SpringBootRestfulApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE) //不会再启动tomcat了
                .run(args);
    }

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
////		return new RestTemplate();
//        return builder.build();
//    }

//    @Override
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

//    //配置RequestFactory
//    @Bean
//    public HttpComponentsClientHttpRequestFactory requestFactory() {
//        PoolingHttpClientConnectionManager connectionManager =
//                new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
//        connectionManager.setMaxTotal(200);
//        connectionManager.setDefaultMaxPerRoute(20);
//
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setConnectionManager(connectionManager)
//                .evictIdleConnections(30, TimeUnit.SECONDS)
//                .disableAutomaticRetries()
//                // 有 Keep-Alive 认里面的值，没有的话永久有效
//                //.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
//                // 换成自定义的
//                .setKeepAliveStrategy(new CustomConnectionKeepAliveStrategy())
//                .build();
//
//        HttpComponentsClientHttpRequestFactory requestFactory =
//                new HttpComponentsClientHttpRequestFactory(httpClient);
//
//        return requestFactory;
//    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return new RestTemplate();

        return builder
                .setConnectTimeout(Duration.ofMillis(100))
                .setReadTimeout(Duration.ofMillis(500))
                .requestFactory(this::requestFactory)
                .build();
    }

    //https方式
    @Bean
    public HttpComponentsClientHttpRequestFactory requestFactory() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContextBuilder.create()
                    // 会校验证书
                    .loadTrustMaterial(keyStore.getURL(), keyPass.toCharArray())
                    // 放过所有证书校验
//					.loadTrustMaterial(null, (certificate, authType) -> true)
                    .build();
        } catch(Exception e) {
            log.error("Exception occurred while creating SSLContext.", e);
        }

        CloseableHttpClient httpClient = HttpClients.custom()
                .evictIdleConnections(30, TimeUnit.SECONDS)
                .setMaxConnTotal(200)
                .setMaxConnPerRoute(20)
                .disableAutomaticRetries()
                .setKeepAliveStrategy(new CustomConnectionKeepAliveStrategy())
                .setSSLContext(sslContext)
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient);

        return requestFactory;
    }
}
