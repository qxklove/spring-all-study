# Spring Boot Admin
为SpringBoot应⽤程序提供⼀套管理界面，主要功能是集中展示应⽤程序Actuator相关的内容，变更更通知

### 快速上手
服务端
* de.codecentric:spring-boot-admin-starter-server:2.1.3
* @EnableAdminServer

客户端
* de.codecentric:spring-boot-admin-starter-client:2.1.3
* 配置服务端及Endpoint
    * spring.boot.admin.client.url=http://localhost:8080
    * management.endpoints.web.exposure.include=*

安全控制
* 安全相关依赖：spring-boot-starter-security
* 服务端配置
    * spring.security.user.name
    * spring.security.user.password
* 客户端配置
    * spring.boot.admin.client.username
    * spring.boot.admin.client.password
    * spring.boot.admin.client.instance.metadata.user.name，连接自身actuator
    * spring.boot.admin.client.instance.metadata.user.password
  
