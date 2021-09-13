# Spring Cloud Config

## Spring Cloud Config Server
* 引入依赖 spring-cloud-config-server
* @EnableConfigServer
* 支持Git/SVN/Vault/JDBC...

### 使⽤Git作为后端存储
配置
* MultipleJGitEnvironmentProperties类
  * spring.cloud.config.server.git.uri：git地址

配置文件的要素
* {application}，即客户端的spring.application.name
* {profile}，即客户端的spring.profiles.active，多个用逗号分隔
* {label}，配置文件的特定标签，默认master

访问配置内容，HTTP请求
* GET /{application}/{profile}[/{label}]
* GET /{application}-{profile}.yml
* GET /{label}/{application}-{profile}.yml
* GET /{application}-{profile}.properties
* GET /{label}/{application}-{profile}.properties

## Spring Cloud Config Client
* 引入依赖 spring-cloud-starter-config
* bootstrap.properties/yml配置configServer的地址
  * spring.cloud.config.uri=http://localhost:8888，写死的方式
  * spring.cloud.config.discovery.enabled=true，服务发现方式
  * spring.cloud.config.discovery.service-id=spring-cloud-config-server
* spring.cloud.config.fail-fast=true，访问不上快速失败
* 配置刷新：配置项写在一个Bean里面， 加@RefreshScope注解，访问/actuator/refresh endpoint时会做刷新
  
