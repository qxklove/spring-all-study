# Spring Boot AutoConfiguration

### SpringBoot自动配置原理
自动配置就是基于添加的JAR依赖⾃动对SpringBoot应用程序进行配置，相关类在`spring-boot-autoconfiguration`JAR包下
* @EnableAutoConfiguration
  * exclude = Class<?>[]
  * AutoConfigurationImportSelector
      * META-INF/spring.factories 
          * org.springframework.boot.autoconfigure.EnableAutoConfiguration

条件注解
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

外化配置加载顺序
1. 开启DevTools时，~/.spring-boot-devtools.properties
2. 测试类上的@TestPropertySource注解
3. @SpringBootTest#properties属性
4. 命令行参数(--server.port=9000)
5. SPRING_APPLICATION_JSON中的属性
6. ServletConfig初始化参数
7. ServletContext初始化参数
8. java:comp/env中的JNDI属性
9. System.getProperties()
10. 操作系统环境变量
11. random.*涉及到的RandomValuePropertySource
12. jar包外部的application-{profile}.properties或.yml
13. jar包内部的application-{profile}.properties或.yml
14. jar包外部的application.properties或.yml
15. jar包内部的application.properties或.yml
16. @Configuration类上的@PropertySource
17. SpringApplication.setDefaultProperties()设置的默认属性

命令行加`--debug`会打印自动配置的判断结果

### 实现⾃己的⾃动配置
1. 编写JavaConfig，@Configuration
2. 添加条件，@Conditional
3. 定位⾃动配置，META-INF/spring.factories

⾃动配置的执行顺序
1. @AutoConfigureBefore
2. @AutoConfigureAfter
3. @AutoConfigureOrder

### 只用Spring来实现自动配置  
解决思路
* 条件判断
  * 通过BeanFactoryPostProcessor 进⾏判断
* 配置加载
  * 编写JavaConfig类
  * 引⼊配置类
    * 通过component-scan
    * 通过XML文件import

Spring的两个扩展点
* BeanPostProcessor，针对Bean实例，在Bean创建后提供定制逻辑回调
* BeanFactoryPostProcessor，针对Bean定义，在容器创建Bean前获取配置元数据，JavaConfig中需要定义为static⽅法

关于Bean的⼀些定制
* Lifecycle Callback
  * InitializingBean / @PostConstruct / init-method
  * DisposableBean / @PreDestroy / destroy-method
* XxxAware接⼝
  * ApplicationContextAware
  * BeanFactoryAware
  * BeanNameAware

一些常⽤用操作
* 判断类是否存在
  * ClassUtils.isPresent()
* 判断Bean是否已定义
  * ListableBeanFactory.containsBeanDefinition()
  * ListableBeanFactory.getBeanNamesForType()
* 注册Bean定义
  * BeanDefinitionRegistry.registerBeanDefinition()
    * GenericBeanDefinition
  * BeanFactory.registerSingleton()

### 定制⾃己的starter依赖
主要内容
* autoconfigure模块，包含自动配置代码
* starter模块，包含指向⾃动配置模块的依赖及其他相关依赖 
* 命名方式：xxx-spring-boot-autoconfigure xxx-spring-boot-starter
* 不要使用spring-boot作为依赖的前缀
* 不要使用spring-boot的配置命名空间