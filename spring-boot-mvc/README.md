# Spring Boot MVC

### 一个请求的大致处理流程
1. 绑定一些 Attribute
    * WebApplicationContext / LocaleResolver / ThemeResolver
2. 处理 Multipart
    * 如果是，则将请求转为 MultipartHttpServletRequest Handler 处理理
3. Handler处理
    * 如果找到对应 Handler，执⾏ Controller 及前后置处理器逻辑
4. 处理返回的 Model ，呈现视图