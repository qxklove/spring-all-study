# Spring Boot Druid
[Druid GitHub地址](https://github.com/alibaba/druid)

### 特点
* 详细的监控
* ExceptionSorter
* SQL防注入
* 内置加密配置
* 众多扩展点，方便定制

### 一些配置
* Filter配置
  * spring.datasource.druid.filters=conn,config,stat,slf4j
* 密码加密
  * spring.datasource.password=
  * spring.datasource.druid.filter.config.enabled=true
  * spring.datasource.druid.connection-properties=config.decrypt=true;config.decrypt.key=${public-key}
* SQL防注入
  * spring.datasource.druid.filter.wall.enabled=true
  * spring.datasource.druid.filter.wall.db-type=h2
  * spring.datasource.druid.filter.wall.config.delete-allow=false
  * spring.datasource.druid.filter.wall.config.drop-table-allow=false


### Druid Filter
* 用于定制连接池操作的各种环节
* 可以继承FilterEventAdapter以方便地实现Filter
* 修改META-INF/druid-filter.properties增加Filter配置



