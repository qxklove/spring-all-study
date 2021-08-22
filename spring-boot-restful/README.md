# SpringBoot RestFul

## SpringBoot中的RestTemplate
SpringBoot中没有⾃动配置RestTemplate，但是提供了RestTemplateBuilder，可以通过RestTemplateBuilder.build()创建RestTemplate。

### 常用方法
* GET请求：getForObject()/getForEntity()
* POST请求：postForObject()/postForEntity()
* PUT请求：put()
* DELETE请求：delete()

### 构造URI
* 构造URI：UriComponentsBuilder
* 构造相对于当前请求的URI：ServletUriComponentsBuilder
* 构造指向Controller的URI：MvcUriComponentsBuilder

### 传递HTTP Header
* RestTemplate.exchange()
* RequestEntity<T>/ResponseEntity<T>调用方法设置header

### 类型转换
* JsonSerializer/JsonDeserializer
* @JsonComponent

### 解析泛型对象
* RestTemplate.exchange()
* ParameterizedTypeReference<T>

## 简单定制RestTemplate
### RestTemplate支持的HTTP库
* 通用接⼝：ClientHttpRequestFactory
  * 默认实现：SimpleClientHttpRequestFactory
  * Apache HttpComponents：HttpComponentsClientHttpRequestFactory，也就是HttpClient
  * Netty：Netty4ClientHttpRequestFactory(Deprecated)，Reactor处理的话更建议用WebClient
  * OkHttp：OkHttp3ClientHttpRequestFactory

定制RestTemplate主要是为了优化底层请求策略
* 连接管理
  * PoolingHttpClientConnectionManager
  * KeepAlive策略
* 超时设置：connectTimeout/readTimeout
* SSL校验：证书检查策略





