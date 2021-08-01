# Spring Boot 事务

## Spring的事务抽象
###一致的事务模型
* JDBC/Hibernate/MyBatis
* DataSource/JTA

### 事务抽象的核心接口
PlatformTransactionManager接口，有如下实现类
* DataSourceTransactionManager
* HibernateTransactionManager
* JtaTransactionManager

它们实现了方法
* void commit(TransactionStatus status) throws TransactionException;
* void rollback(TransactionStatus status) throws TransactionException;
* TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException;

TransactionDefinition 定义如下一些事务相关信息
* Propagation 传播特性
* Isolation 隔离性
* Timeout
* Read-only status

### 编程式事务
TransactionTemplate
* TransactionCallback
* TransactionCallbackWithoutResult

PlatformTransactionManager：可以传入TransactionDefinition进行定义

### 声明式事务
####基于注解的配置方式
开启事务注解的方式
* @EnableTransactionManagement
* \<tx:annotation-driven/>

一些配置
* proxyTargetClass
* mode
* order

在需要的方法或类上加注解 @Transactional
* propagation
* isolation
* timeout
* readOnly
* 怎么判断回滚
