# Spring Cloud Hystrix
https://github.com/Netflix/Hystrix/wiki/Configuration  
Netflix Hystrix实现了断路器模式

Hystrix的处理是在另外的线程里做的

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