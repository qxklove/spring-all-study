# Spring Cloud Eureka

Eureka是在AWS上定位服务的REST服务。Eureka包含在Netflix的一个套件里。2.0版本已不再更新，所以不建议使用。

### 在本地启动⼀个简单的Eureka服务
* 引入starter
  * spring-cloud-dependencies
  * spring-cloud-starter-netflix-eureka-starter
* 声明@EnableEurekaServer
* 注意事项
  * 默认端口8761
  * Eureka⾃己不要注册到Eureka了
一般需要做一个集群

### 将服务注册到EurekaServer
* 引入starter
  * spring-cloud-starter-netflix-eureka-client
* 声明@EnableDiscoveryClient或者@EnableEurekaClient，不加的话如果在classpath里发现client依赖的话也会自动注册
* ⼀些配置项
  * eureka.client.service-url.default-zone #Eureka服务的地址
  * eureka.instance.prefer-ip-address #是不是优先使用ip的方式

Bootstrap属性
* 启动引导阶段加载的属性
* bootstrap.properties/.yml
* 可以改配置文件名字：spring.cloud.bootstrap.name=bootstrap
* 常用配置
  * spring.application.name=应⽤名
  * 配置中心相关


## Consul
关键特性
* 服务发现
* 健康检查
* KV存储
* 多数据中⼼支持
* 安全的服务间通信

### 使⽤Consul提供服务发现能⼒
好用的功能
* HTTP API
* DNS(xxx.service.consul) xxx是服务名，可以通过这个域名访问这个服务
* 与Nginx联动，⽐如ngx_http_consul_backend_module

使⽤Consul作为注册中心
* 依赖spring-cloud-starter-consul-discovery
* 简单配置
  * spring.cloud.consul.host=localhost
  * spring.cloud.consul.port=8500
  * spring.cloud.consul.discovery.prefer-ip-address=true