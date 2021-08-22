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