# Spring Boot Mybatis

## 一些简单配置
* mybatis.mapper-locations = classpath*:mapper/**/\*.xml 指定Mybatis的Mapper文件
* mybatis.type-aliases-package = 类型别名的包名
* mybatis.type-handlers-package = TypeHandler扫描包名
* mybatis.configuration.map-underscore-to-camel-case = true 下划线转驼峰

## Mapper定义与扫描
* @MapperScan配置扫描位置
* @Mapper定义接口
* 映射的定义 -- XML与注解
