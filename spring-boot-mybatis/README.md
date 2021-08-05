# Spring Boot MyBatis

## 一些简单配置
* mybatis.mapper-locations = classpath*:mapper/**/\*.xml 指定Mybatis的Mapper文件
* mybatis.type-aliases-package = 类型别名的包名
* mybatis.type-handlers-package = TypeHandler扫描包名
* mybatis.configuration.map-underscore-to-camel-case = true 下划线转驼峰

## Mapper定义与扫描
* @MapperScan配置扫描位置
* @Mapper定义接口
* 映射的定义 -- XML与注解

## 让MyBatis更好用的工具
### [MyBatis Generator](http://mybatis.org/generator/)
根据数据库表生成相关代码 POJO，Mapper接口，SQL Map XML
关于此的示例代码作为子项目运行有问题，可以拿出来作为单独项目运行

本项目中用 `generatorConfig.xml` 配置文件来生成相关代码，主要配置了：
* jdbcConnection
* javaModelGenerator
* sqlMapGenerator
* javaClientGenerator（ANNOTATEDMAPPER/XMLMAPPER/MIXEDMAPPER）
* table

生成时可以用的一些插件：
* FluentBuilderMethodsPlugin：类似lombok的Builder
* ToStringPlugin
* SerializablePlugin
* RowBoundsPlugin：分页用到

使用生成对象
* 简单操作，直接使用生成的xxxMapper的方法
* 复杂查询，使用生成的xxxExample对象

### [MyBatis PageHelper](https://pagehelper.github.io/)

