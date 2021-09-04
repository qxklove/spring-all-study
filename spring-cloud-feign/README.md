# Spring CLoud Feign
声明式REST Web服务客户端
* 引入依赖spring-cloud-starter-openfeign
* 开启Feign支持：@EnableFeignClients
* 定义Feign接口：@FeignClient
* FeignClientsConfiguration或者通过配置
    * Encoder/Decoder/Logger/Contract/Client...

一些其他配置
* feign.okhttp.enabled=true
* feign.httpclient.enabled=true
* \# 压缩相关
    * feign.compression.response.enabled=true
    * feign.compression.request.enabled=true
    * feign.compression.request.mime-types= text/xml,application/xml,application/json
    * feign.compression.request.min-request-size=2048