# Spring Cloud Hystrix
https://github.com/Netflix/Hystrix/wiki/Configuration  
Netflix Hystrix实现了断路器模式，和Eureka一样也不在维护了。

Hystrix的处理是在另外的线程里做的。

* @HystrixCommand
  * fallbackMethod/commandProperties
    * @HystrixProperty(name="execution.isolation.strategy", value=“SEMAPHORE")

SpringCloud支持
* 引入spring-cloud-starter-netflix-hystrix
* @EnableCircuitBreaker

Feign支持
* feign.hystrix.enabled=true
* @FeignClient
  * fallback/fallbackFactory

### Hystrix Dashboard
SpringCloud为我们提供了HystrixMetricsStream，对应actuator的endpoint是：/actuator/hystrix.stream
* 引入spring-cloud-starter-netflix-hystrix-dashboard
* @EnableHystrixDashboard
* 访问地址：/hystrix

#### 聚合集群熔断信息
SpringCloud为我们提供了NetflixTurbine
* 引入spring-cloud-starter-netflix-turbines
* @EnableTurbine
* 访问地址：/turbine.stream?cluster=集群名