# Spring Boot Session

⽀持的存储：Redis，MongoDB，JDBC，Hazelcast

### 实现原理
通过定制的HttpServletRequest返回定制的HttpSession
* SessionRepositoryRequestWrapper
* SessionRepositoryFilter
* DelegatingFilterProxy

### 基于Redis的HttpSession
引入依赖：spring-session-data-redis  
基本配置：
* @EnableRedisHttpSession
* 提供RedisConnectionFactory
* 实现AbstractHttpSessionApplicationInitializer
  * 配置DelegatingFilterProxy

### SpringBoot对SpringSession的支持
application.properties 配置
* spring.session.store-type=redis
* spring.session.timeout=
  * server.servlet.session.timeout=
* spring.session.redis.flush-mode=on-save
* spring.session.redis.namespace=spring:session