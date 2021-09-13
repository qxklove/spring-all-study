# Spring Cloud Sleuth

### Google Dapper的⼀些术语
* Span：基本的⼯工作单元
* Trace：由一组Span构成的树形结构
* Annotation：⽤用于及时记录事件
* cs：Client Sent
* sr：Server Received
* ss：Server Sent
* cr：Client Receive

### SpringCloud提供的服务治理理功能 
依赖
* Spring Cloud Sleuth：spring-cloud-starter-sleuth
* Spring Cloud Sleuth with Zipkin：spring-cloud-starter-zipkin

日志输出：[appname,traceId,spanId,exportable]

配置
* spring.zipkin.base-url=http://localhost:9411/，指定url方式找zipkin
* spring.zipkin.discovery-client-enabled=false，服务发现机制找zipkin
* spring.zipkin.sender.type=web/rabbit/kafka
* spring.zipkin.compression.enabled=false，压缩
* spring.sleuth.sampler.probability=0.1，采样比例