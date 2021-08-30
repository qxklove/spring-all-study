 # Spring Boot MVC

### 一个请求的大致处理流程
1. 绑定一些 Attribute
    * WebApplicationContext / LocaleResolver / ThemeResolver
2. 处理 Multipart
    * 如果是，则将请求转为 MultipartHttpServletRequest Handler 处理理
3. Handler处理
    * 如果找到对应 Handler，执⾏ Controller 及前后置处理器逻辑
4. 处理返回的 Model ，呈现视图

## 定义处理方法
### 定义类型转换
实现WebMvcConfigurer  
SpringBoot在WebMvcAutoConfiguration中实现了一个，只要添加自定义的Converter或者添加自定义的Formatter即可，参考MoneyFormatter

### 定义校验
* 通过Validator对绑定结果进行校验，有Hibernate Validator就会用它  
* 要校验的对象加@Valid注解，校验规则在校验对象的类的属性上加注解，如@NotNull  
* BindingResult对象代表了校验结果

SpringBoot 2.3.0版本之后就没有引入validation对应的包，需单独引入

      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-validation</artifactId>
          <version>2.3.3.RELEASE</version>
      </dependency>

### Multipart上传
配置MultipartResolver，SpringBoot⾃动配置了MultipartAutoConfiguration，⽀持类型multipart/form-data，MultipartFile类型

##视图解析机制
### 视图解析的实现基础
ViewResolver 与 View 接⼝
* AbstractCachingViewResolver
* UrlBasedViewResolver
* FreeMarkerViewResolver
* ContentNegotiatingViewResolver，根据可以接受的返回类型来选择合适的响应，比如可以是一个xml,json等
* InternalResourceViewResolver，默认放在解析链最后的一个解析器，用来处理jsp,jstl

### DispatcherServlet 中的视图解析逻辑 
* initStrategies()
   * initViewResolvers() 初始化了对应ViewResolver
* doDispatch()
   * processDispatchResult()
     * 没有返回视图的话，尝试RequestToViewNameTranslator 
     * resolveViewName() 从视图名到具体视图的一个解析，解析出View对象

使用@ResponseBody的情况  
* 在HandlerAdapter.handle()中完成了Response输出
* RequestMappingHandlerAdapter.invokeHandlerMethod()
   * HandlerMethodReturnValueHandlerComposite.handleReturnValue()
   * RequestResponseBodyMethodProcessor.handleReturnValue()

配置MessageConverter，通过WebMvcConfigurer的configureMessageConverters()，SpringBoot⾃动查找HttpMessageConverters进行注册

### SpringBoot对Jackson的支持
* JacksonAutoConfiguration  
  * SpringBoot通过@JsonComponent注册JSON序列化组件
  * Jackson2ObjectMapperBuilderCustomizer，可以实现它来定制json的输出
* JacksonHttpMessageConvertersConfiguration中，增加了jackson-dataformat-xml以支持XML序列化

### SpringBoot中的静态资源配置
核⼼逻辑：WebMvcConfigurer.addResourceHandlers()  
常用配置：
* spring.mvc.static-path-pattern=/**，静态资源的路径模式，默认根路径开始寻找
* spring.resources.static-locations=classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/，静态资源在classpath的位置

### SpringBoot中的缓存配置
常用配置(默认时间单位都是秒)
* ResourceProperties.Cache类里面
    * spring.resources.cache.cachecontrol.max-age=时间
    * spring.resources.cache.cachecontrol.no-cache=true/false
    * spring.resources.cache.cachecontrol.s-max-age=时间，公共缓存时间

### SpringMVC的异常解析
核心接⼝：HandlerExceptionResolver  
实现类：
* SimpleMappingExceptionResolver
* DefaultHandlerExceptionResolver
* ResponseStatusExceptionResolver
* ExceptionHandlerExceptionResolver

定义异常处理方法，方法上加注解：@ExceptionHandler  
添加位置：@Controller/@RestController 或 @ControllerAdvice/@RestControllerAdvice 注解的类的方法上，前者的优先级高于后者

### SpringMVC的拦截器
核⼼接⼝：HandlerInteceptor
* boolean preHandle()，方法执行前
* void postHandle()，方法执行结束后，视图呈现前
* void afterCompletion()，视图呈现后
  
针对@ResponseBody和ResponseEntity的情况：ResponseBodyAdvice  
针对异步请求的接⼝：AsyncHandlerInterceptor.afterConcurrentHandlingStarted()

拦截器的配置⽅式  
常规方法：WebMvcConfigurer.addInterceptors()  
SpringBoot中的配置：创建一个带@Configuration的WebMvcConfigurer配置类，不能带@EnableWebMvc(想彻底自⼰控制MVC配置除外)

补充，关于@EnableWebMvc  
SpringMVC的自动配置在org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration这个类中，
这个类有一个很关键的注解：@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
也就是当容器中没有WebMvcConfigurationSupport这个类时，SpringBoot给SpringMVC自动配置才会生效，
而@EnableWebMvc这个注解上有一个很关键的注解：@Import(DelegatingWebMvcConfiguration.class)
意味着一旦标注EnableWebMvc注解就会导入DelegatingWebMvcConfiguration类，而该类是WebMvcConfigurationSupport的子类。
所以如果标注了@EnableWebMvc，则SpringBoot关于SpringMVC的相关配置会失效。

### Web容器
可选容器列表
* spring-boot-starter-tomcat
* spring-boot-starter-jetty
* spring-boot-starter-undertow
* spring-boot-starter-reactor-netty

修改容器配置
* 端⼝
  * server.port
  * server.address
* 压缩
  * server.compression.enabled
  * server.compression.min-response-size
  * server.compression.mime-types
* Tomcat特定配置
  * server.tomcat.max-connections=10000
  * server.tomcat.max-http-post-size=2MB
  * server.tomcat.max-swallow-size=2MB
  * server.tomcat.max-threads=200
  * server.tomcat.min-spare-threads=10
* 错误处理
  * server.error.path=/error
  * server.error.include-exception=false
  * server.error.include-stacktrace=never
  * server.error.whitelabel.enabled=true
* 其他
  * server.use-forward-headers
  * server.servlet.session.timeout

编程⽅式修改容器配置
* WebServerFactoryCustomizer<T>
  * TomcatServletWebServerFactory
  * JettyServletWebServerFactory
  * UndertowServletWebServerFactory

### 配置HTTPS支持
服务端HTTPS支持   
通过参数进⾏配置
* server.port=8443
* server.ssl.*
  * server.ssl.key-store
  * server.ssl.key-store-type，JKS或者PKCS12
  * server.ssl.key-store-password=secret

⽣成证书文件  
命令：keytool -genkey -alias 别名 -storetype 仓库类型 -keyalg 算法 -keysize ⻓度 -keystore ⽂件名 -validity 有效期天数
例子：keytool -genkey -alias springbucks -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore springbucks.p12 -validity 36
* 仓库类型：JKS、JCEKS、PKCS12等
* 算法：RSA、DSA等
* 长度：如2048

项目启动后命令行输入：curl -k -v https://localhost:8443/coffee/1 看下效果

客户端HTTPS支持(示例见restful项目)
* 配置HttpClient(>=4.4)
  * SSLContextBuilder构造SSLContext
  * setSSLHostnameVerifier(new NoopHostnameVerifier())，不去校验hostname
* 配置RequestFactory
  * HttpComponentsClientHttpRequestFactory
    * setHttpClient()