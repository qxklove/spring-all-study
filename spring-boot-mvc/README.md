# Spring Boot MVC

### 一个请求的大致处理流程
1. 绑定一些 Attribute
    * WebApplicationContext / LocaleResolver / ThemeResolver
2. 处理 Multipart
    * 如果是，则将请求转为 MultipartHttpServletRequest Handler 处理理
3. Handler处理
    * 如果找到对应 Handler，执⾏ Controller 及前后置处理器逻辑
4. 处理返回的 Model ，呈现视图

### 定义类型转换
实现WebMvcConfigurer  
SpringBoot在WebMvcAutoConfiguration中实现了一个，只要添加自定义的Converter或者添加自定义的Formatter即可，参考MoneyFormatter

### 定义校验
通过Validator对绑定结果进行校验，有Hibernate Validator就会用它  
要校验的对象加@Valid注解，校验规则在校验对象的类的属性上加注解，如@NotNull
BindingResult对象代表了校验结果

SpringBoot 2.3.0版本之后就没有引入validation对应的包，需单独引入

      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-validation</artifactId>
          <version>2.3.3.RELEASE</version>
      </dependency>

### Multipart上传
配置MultipartResolver，SpringBoot⾃动配置了MultipartAutoConfiguration，⽀持类型multipart/form-data，MultipartFile类型