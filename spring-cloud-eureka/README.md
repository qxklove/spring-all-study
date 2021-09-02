# Spring Cloud Eureka

### 在本地启动⼀个简单的Eureka服务
引入starter
spring-cloud-dependencies
spring-cloud-starter-netflix-eureka-starter
声明@EnableEurekaServer
注意事项
默认端口8761
Eureka⾃己不要注册到Eureka了

将服务注册到EurekaServer
starter
spring-cloud-starter-netflix-eureka-client
声明
@EnableDiscoveryClient或者@EnableEurekaClient
⼀些配置项
eureka.client.service-url.default-zone
eureka.instance.prefer-ip-address

Bootstrap属性
启动引导阶段加载的属性
bootstrap.properties/.yml
可以改配置文件名字：spring.cloud.bootstrap.name=bootstrap
常用配置
spring.application.name=应⽤名
配置中心相关