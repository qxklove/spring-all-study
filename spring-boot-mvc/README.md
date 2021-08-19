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
    
