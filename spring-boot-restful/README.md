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

### 构建更更好的URI
* 使用域及子域对资源进行合理的分组或划分
* 在URI的路径部分使用斜杠分隔符(/)来表示资源之间的层次关系
* 在URI的路径部分使用逗号(,)和分号(;)来表示非层次元素
* 使⽤连字符(-)和下划线(_)来改善长路径中名称的可读性
* 在URI的查询部分使⽤“与”符号(&)来分隔参数
* 在URI中避免出现⽂件扩展名(例如.php，.aspx和.jsp)

|  动作  | 安全/幂等 | 用途 |
| ----- | ------- | --- |
|  GET  |   Y/Y   | 信息获取 |
|  POST  |   N/N   | 该⽅方法⽤途⼴泛，可用于创建、更新或一次性对多个资源进行修改 |
| DELETE |   N/Y   | 删除资源 |
|  PUT  |   N/Y   | 更新或者完全替换一个资源 |
|  HEAD  |   Y/Y   | 获取与GET一样的HTTP头信息，但没有响应体 |
| OPTIONS |   Y/Y   | 获取资源⽀持的HTTP⽅法列表 |
|  TRACE  |   Y/Y   | 让服务器返回其收到的HTTP头 |



