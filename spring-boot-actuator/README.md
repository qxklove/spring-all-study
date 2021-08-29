# Spring Boot Actuator监控

Spring Boot Actuator可以用来监控和管理Spring Boot应用，比如健康检查、审计、统计和HTTP追踪等。所有的这些特性可以通过JMX或者HTTP endpoints(端点)来获得。  

## 引入Actuator

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>

## 端点及其作用
使用/actuator/{端点}来访问获取描述。例如在默认情况下，health端点映射到/actuator/health。  
/actuator可以看到所有支持的端点。
* /auditevents：显示当前应用程序的审计事件信息，需要一个AuditEventRepository Bean
* /beans：显示一个应用中所有Spring Beans的完整列表
* /caches：显示缓存管理器
* /conditions：显示配置类和自动配置类的状态及它们被应用或未被应用的原因
* /configprops：显示一个所有@ConfigurationProperties（配置）的集合列表
* /env：显示来自Spring的ConfigurableEnvironment（环境变量）的属性
* /flyway：显示已应用的任何Flyway数据库迁移路径
* /health：显示应用的健康信息（当使用一个未认证连接访问时显示一个简单的’status’，使用认证连接访问则显示全部信息详情）
* /httptrace：显示Http跟踪信息（默认显示最后一百个），需要一个HttpTraceRepository bean
* /info：显示任意的应用信息
* /integrationgraph：显示Spring的整合图标
* /loggers：显示和修改应用中loggers的配置
* /liquibase：显示任何Liquibase数据库迁移路径
* /metrics：展示当前应用的metrics信息
* /mappings：显示一个所有@RequestMapping路径的集合列表
* /quartz：显示quartz计划任务的信息
* /scheduledtasks：显示应用程序中的计划任务
* /sessions：允许从Spring Session支持的会话存储中检索和删除用户会话。注意Spring Session不支持响应式Web应用程序
* /shutdown：允许应用以优雅的方式关闭（默认情况下不启用）
* /threaddump：执行一个线程转储
  如果是web应用（Spring MVC, Spring WebFlux, 或者 Jersey），还可以使用以下端点：
* /heapdump：返回一个GZip压缩的hprof堆转储文件
* /jolokia：通过HTTP暴露JMX beans（当Jolokia在类路径上时，不适用于WebFlux）
* /logfile：返回日志文件内容（如果设置了logging.file.name或logging.file.path属性），支持使用HTTP Range头检索日志文件内容的部分信息
* /prometheus：以可以被Prometheus服务器抓取的格式显示metrics信息

## 一些配置（对于HTTP endpoints）

可以配置actuator专用的端口，来控制其访问
> management.server.address =  
> management.server.port =

设置监控访问的应用根路径，默认是/actuator
> management.endpoints.web.base-path=/monitor

重新设置端点的映射值
> management.endpoints.web.path-mapping.<id\> = 路径

默认情况下，除shutdown以外的所有端点均已启用。要配置单个端点的启用，请使用`management.endpoint.<id>.enabled`属性。
> management.endpoint.shutdown.enabled=true #启用shutdown端点

可以通过`management.endpoints.enabled-by-default`来修改全局端口默认配置。
> \#启用info端点并禁用其他端点  
> management.endpoints.enabled-by-default=false  
> management.endpoint.info.enabled=true

可以注意到management.endpoints是配置全局用的，management.endpoint则针对单个端点配置。

禁用的端点将从应用程序上下文中完全删除。如果只想更改端点公开（对外暴露）的技术，请改为使用`include`和`exclude`属性。  
**默认只暴露了health和info端点。**
> management.endpoints.web.exposure.include=["*"] #暴露全部端点  
> management.endpoints.web.exposure.exclude=env,beans #不公开env和beans端点

同一端点ID同时出现在include属性和exclude属性里,exclude属性优先于include属性。



## 几个常用端点
### 1.health端点
health端点是查看Spring Boot应用程序健康状况的端点，如果没有特殊设置，显示的信息就比较少。  
* management.health.defaults.enabled=true|false
* management.health.<id>.enabled=true
* managemet.endpoint.health.show-details：health端点的细节信息是否展示，有如下值：
  * never：细节信息详情永远都不展示
  * when-authorized：细节详情只对授权用户显示
  * always：细节详情显示给所有用户

health端点的内容是通过HealthIndicatorRegistry从实现HealthIndicator接口的bean中收集来的。  
可以实现自己的HealthIndicator，然后自定义检查逻辑并返回对应Health状态，Health中包含状态和详细描述信息。

    @Component
    public class MyHealthIndicator implements HealthIndicator {
        private static final String PORT = "80";
    
        @Value("${server.port}")
        private String serverPort;
        
        @Override
        public Health health() {
            if (!PORT.equals(serverPort)) {
                return Health.down().withDetail("端口不是80", "是" + serverPort).build();
            }
            return Health.up().build();
        }
    }

访问后显示如下：
    
    {
        "status":"DOWN",
        "components":{
            "diskSpace":{
                "status":"UP",
                "details":{
                    "total":494384795648,
                    "free":337807667200,
                    "threshold":10485760,
                    "exists":true
                }
            },
            "my":{
                "status":"DOWN",
                "details":{
                    "端口不是80":"是8080"
                }
            },
            "ping":{
                "status":"UP"
            }
        }
    }

### 2.info端点
可以通过配置`info.*`自定义应用程序信息，例如：
> info.app.encoding=UTF-8  
> info.app.java.source=11  
> info.app.java.target=11

### 3.Metrics端点
通过/actuator/metrics端点可以获取所有的指标名称，要获取具体的指标值，只需要用端点`/actuator/metrics/{metricName}  `
比如：/actuator/metrics/http.server.requests获取到请求的相关指标信息，还可以指定要具体哪个请求的：/actuator/metrics/http.server.requests?tag=uri:/请求路径


## 参考
[官方文档](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)  
<https://blog.csdn.net/u012364631/article/details/94019429>
