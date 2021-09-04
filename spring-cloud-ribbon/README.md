# Spring Cloud Ribbon

## 使⽤Spring Cloud LoadBalancer访问服务
获得服务地址
* EurekaClient.getNextServerFromEureka()，获取下一个注册的实例
* DiscoveryClient.getInstances(String serviceId)，获取指定的服务id的注册的实例，DiscoveryClient是Spring提供的抽象，方便以后换注册中心

Load Balancer Client 是通过配置RestTemplate与WebClient实现
* RestTemplate Bean上加注解@LoadBalanced，@LoadBalanced为RestTemplate做了增强，URL中给了服务名，根据服务名到注册中心获取具体服务实例的列表，调用时选择其中一个示例替换进URL里，获得最终要调用的URL去执行调用。
* 实际是通过ClientHttpRequestInterceptor实现的
  * LoadBalancerInterceptor
  * LoadBalancerClient
    * RibbonLoadBalancerClient