# spring Boot 数据源配置

### 如果直接配置相关Bean（也就是不用Spring Boot）
* 数据源配置 ： 
  * DataSource（根据选择的连接池实现确定）
* 事务相关（可选）： 
  * PlatformTransactionManager（DataSourceTransactionManager）
  * TransactionTemplate
* 操作相关（可选）：
  * JdbcTemplate

### Spring Boot 做了哪些配置
* DatSourceAutoConfiguration：配置DataSource
* DatSourceTransactionManagerAutoConfiguration：配置DatSourceTransactionManager
* JdbcTemplateAutoConfiguration：配置JdbcTemplate

符合条件时才配置，如果代码里手动配置过，就不会再自动配置了

### 数据源相关配置
* spring.datasource.url=jdbc:mysql://localhost/test
* spring.datasource.username=
* spring.datasource.password=
* spring.datasource.driver-class-name=com.mysql.jdbc.Driver（可选，SpringBoot会自动根据url来判断需要的驱动）