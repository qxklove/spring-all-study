# Spring Aop
用打印日志演示

启动类加上注解@EnableAspectJAutoProxy

切面注解@Aspect的类记得要加上能让他成为Bean的注解，如@Component

参考：[PerformanceAspect](https://github.com/qxklove/spring-all-study/blob/master/spring-boot-aop/src/main/java/com/qxk/springall/springbootaop/aspect/PerformanceAspect.java)

打印方法执行时间耗时