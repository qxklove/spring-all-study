# spring Boot 多数据源配置

#### 手动配置两组DataSource及相关内容与SpringBoot协同工作（二选一）
* 配置@Primary类型的Bean
* 排除Spring Boot的自动配置
    * DataSourceAutoConfiguration
    * DataSourceTransactionManagerAutoConfiguration
    * JdbcTemplateAutoConfiguration

