# Spring Boot AutoConfiguration

### SpringBoot自动配置原理
自动配置就是基于添加的JAR依赖⾃动对SpringBoot应用程序进行配置，相关类在`spring-boot-autoconfiguration`JAR包下
* @EnableAutoConfiguration
  * exclude = Class<?>[]
  * AutoConfigurationImportSelector
      * META-INF/spring.factories 
          * org.springframework.boot.autoconfigure.EnableAutoConfiguration

* 条件注解
  * @Conditional
* 类条件
  * @ConditionalOnClass
  * @ConditionalOnMissingClass
* Bean条件
  * @ConditionalOnBean
  * @ConditionalOnMissingBean
  * @ConditionalOnSingleCandidate
* 属性条件
  * @ConditionalOnProperty
* 资源条件
  * @ConditionalOnResource
* Web应用条件
  * @ConditionalOnWebApplication
  * @ConditionalOnNotWebApplicatio
* 其他条件
  * @ConditionalOnExpression
  * @ConditionalOnJava
  * @ConditionalOnJndi

命令行加`--debug`会打印自动配置的判断结果

### 实现⾃己的⾃动配置
1. 编写JavaConfig，@Configuration
2. 添加条件，@Conditional
3. 定位⾃动配置，META-INF/spring.factories

⾃动配置的执行顺序
1. @AutoConfigureBefore
2. @AutoConfigureAfter
3. @AutoConfigureOrder