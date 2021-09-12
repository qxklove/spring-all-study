# Spring Cloud Config

* 引入依赖 spring-cloud-config-server
* @EnableConfigServer
* 支持Git/SVN/Vault/JDBC...

## 使⽤Git作为后端存储
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